# ZooKeeper原理与应用

<!-- TOC -->

- [ZooKeeper原理与应用](#zookeeper原理与应用)
    - [简介](#简介)
    - [基本概念](#基本概念)
        - [集群角色](#集群角色)
    - [会话(Session)](#会话session)
    - [数据节点(ZNode)](#数据节点znode)
        - [版本](#版本)
    - [状态信息](#状态信息)
    - [事务操作](#事务操作)
    - [Watcher](#watcher)
    - [ACL](#acl)
    - [ZAB(ZooKeeper Atomic Broadcast)协议](#zabzookeeper-atomic-broadcast协议)
        - [概览](#概览)
        - [ZAB协议介绍](#zab协议介绍)
        - [leader选举过程](#leader选举过程)
        - [Leader选举算法分析](#leader选举算法分析)
    - [Zookeeper典型应用场景](#zookeeper典型应用场景)
        - [数据发布与订阅（配置中心）](#数据发布与订阅配置中心)
        - [命名服务（Naming Service）](#命名服务naming-service)
        - [分布式协调/通知](#分布式协调通知)
        - [心跳检测](#心跳检测)
        - [工作进度汇报](#工作进度汇报)
        - [Master选举](#master选举)
        - [分布式锁](#分布式锁)
            - [排它锁](#排它锁)
                - [定义锁](#定义锁)
                - [获得锁](#获得锁)
                - [释放锁](#释放锁)
            - [共享锁](#共享锁)
    - [Zookeeper在Kafka中的应用](#zookeeper在kafka中的应用)
        - [Controller选举](#controller选举)
    - [ZooKeeper在HBase中的应用](#zookeeper在hbase中的应用)
        - [HMaster选举与主备切换](#hmaster选举与主备切换)
        - [系统容错](#系统容错)
        - [RootRegion管理](#rootregion管理)
        - [Region管理](#region管理)
        - [分布式SplitWAL任务管理](#分布式splitwal任务管理)
- [Zookeeper 常见面试题](#zookeeper-常见面试题)
- [最新zk面试题](#最新zk面试题)
- [参考](#参考)

<!-- /TOC -->

## 简介
ZooKeeper是一个开源的分布式协调服务，由雅虎创建，是Google Chubby的开源实现。ZooKeeper的设计目标是将那些复杂且容易出错的分布式一致性服务封装起来，构成一个高效可靠的原语集，并以一系列简单易用的接口提供给用户使用。
ZooKeeper是一个典型的分布式数据一致性的解决方案。分布式应用程序可以基于它实现诸如数据发布/订阅、负载均衡、命名服务、分布式协调/通知、集群管理、Master选举、分布式锁和分布式队列等功能。ZooKeeper可以保证如下分布式一致性特性。
* 顺序一致性
    从同一个客户端发起的事务请求，最终将会严格按照其发起顺序被应用到ZooKeeper中。

* 原子性
    所有事务请求的结果在集群中所有机器上的应用情况是一致的，也就是说要么整个集群所有集群都成功应用了某一个事务，要么都没有应用，一定不会出现集群中部分机器应用了该事务，而另外一部分没有应用的情况。

* 单一视图
    无论客户端连接的是哪个ZooKeeper服务器，其看到的服务端数据模型都是一致的。

* 可靠性    
    一旦服务端成功地应用了一个事务，并完成对客户端的响应，那么该事务所引起的服务端状态变更将会被一直保留下来，除非有另一个事务又对其进行了变更。

* 实时性
    通常人们看到实时性的第一反应是，一旦一个事务被成功应用，那么客户端能够立即从服务端上读取到这个事务变更后的最新数据状态。这里需要注意的是，ZooKeeper仅仅保证在一定的时间段内，客户端最终一定能够从服务端上读取到最新的数据状态。

## 基本概念
本节将介绍ZooKeeper的几个核心概念。这些概念贯穿于之后对ZooKeeper更深入的讲解，因此有必要预先了解这些概念。

### 集群角色
* Leader
负责进行投票的发起和决议，更新系统状态

* Learner
    * Follower
    接受客户端请求并向客户端返回结果，在选举过程中参与投票
    * Observer
    可以接受客户端请求，将写请求转发给leader，但observer不参加投票过程，只同步leader的状态，observer的目的是为了扩展系统，提高读取速度。

一个ZooKeeper集群同一时刻只会有一个Leader，其他都是Follower或Observer。
ooKeeper配置很简单，每个节点的配置文件(zoo.cfg)都是一样的，只有myid文件不一样。myid的值必须是zoo.cfg中server.{数值}的{数值}部分。
在装有ZooKeeper的机器的终端执行
```shell
zookeeper-server status 
```
可以看当前节点的ZooKeeper是什么角色（Leader or Follower）。
ZooKeeper默认只有Leader和Follower两种角色，没有Observer角色。
为了使用Observer模式，在任何想变成Observer的节点的配置文件中加入：
```shell
peerType=observer
```
并在所有server的配置文件中，配置成observer模式的server的那行配置追加:observer，例如：
```shell
server.1:localhost:2888:3888:observer
```

ZooKeeper集群的所有机器通过一个Leader选举过程来选定一台被称为 __『Leader』__ 的机器，Leader服务器为客户端 __提供读和写服务__ 。
Follower和Observer都能提供读服务， __不能提供写服务__。两者唯一的区别在于，Observer机器不参与Leader选举过程，也不参与写操作的『过半写成功』策略，因此Observer可以在不影响写性能的情况下提升集群的读性能。

## 会话(Session)
Session是指客户端会话，在讲解客户端会话之前，我们先来了解下客户端连接。在ZooKeeper中，一个客户端连接是指客户端和ZooKeeper服务器之间的TCP长连接。ZooKeeper对外的服务端口默认是2181，客户端启动时，首先会与服务器建立一个TCP连接，从第一次连接建立开始，客户端会话的生命周期也开始了，通过这个连接，客户端能够通过心跳检测和服务器保持有效的会话，也能够向ZooKeeper服务器发送请求并接受响应，同时还能通过该连接接收来自服务器的Watch事件通知。Session的SessionTimeout值用来设置一个客户端会话的超时时间。**服务器会确定客户端是否正常连接(客户端会定时向服务器发送heart_beat),服务器重置下SESSION_TIMEOUT时间。** 当由于服务器压力太大、网络故障或是客户端主动断开连接等各种原因导致客户端连接断开时，只要在SessionTimeout规定的时间内能够重新连接上集群中任意一台服务器，那么之前创建的会话仍然有效。

## 数据节点(ZNode)
在谈到分布式的时候，一般『节点』指的是组成集群的每一台机器。而ZooKeeper中的数据节点是指数据模型中的数据单元，称为ZNode。ZooKeeper将所有数据存储在内存中，数据模型是一棵树（ZNode Tree），由斜杠（/）进行分割的路径，就是一个ZNode，如/hbase/master,其中hbase和master都是ZNode。每个ZNode上都会保存自己的数据内容，同时会保存一系列属性信息。

> 注：
这里的ZNode可以理解成既是Unix里的文件，又是Unix里的目录。因为每个ZNode不仅本身可以写数据（相当于Unix里的文件），还可以有下一级文件或目录（相当于Unix里的目录）。

在ZooKeeper中，ZNode可以分为持久节点和临时节点两类。

* 持久节点
所谓持久节点是指一旦这个ZNode被创建了，除非主动进行ZNode的移除操作，否则这个ZNode将一直保存在ZooKeeper上。

* 临时节点
临时节点的生命周期跟客户端会话绑定，一旦客户端会话失效，那么这个客户端创建的所有临时节点都会被移除。

另外，ZooKeeper还允许用户为每个节点添加一个特殊的属性：SEQUENTIAL。一旦节点被标记上这个属性，那么在这个节点被创建的时候，ZooKeeper就会自动在其节点后面追加上一个整型数字，这个整型数字是一个由父节点维护的自增数字。

### 版本
ZooKeeper的每个ZNode上都会存储数据，对应于每个ZNode，ZooKeeper都会为其维护一个叫作Stat的数据结构，Stat中记录了这个ZNode的三个数据版本，分别是version（当前ZNode的版本）、cversion（当前ZNode子节点的版本）和aversion（当前ZNode的ACL版本）。

## 状态信息
每个ZNode除了存储数据内容之外，还存储了ZNode本身的一些状态信息。用 get 命令可以同时获得某个ZNode的内容和状态信息。如下：
```shell
[zk: localhost:2181(CONNECTED) 23] get /yarn-leader-election/appcluster-yarn/ActiveBreadCrumb

appcluster-yarnrm1
cZxid = 0x1b00133dc0    //Created ZXID,表示该ZNode被创建时的事务ID
ctime = Tue Jan 03 15:44:42 CST 2017    //Created Time,表示该ZNode被创建的时间
mZxid = 0x1d00000063    //Modified ZXID，表示该ZNode最后一次被更新时的事务ID
mtime = Fri Jan 06 08:44:25 CST 2017    //Modified Time，表示该节点最后一次被更新的时间
pZxid = 0x1b00133dc0    //表示该节点的子节点列表最后一次被修改时的事务ID。注意，只有子节点列表变更了才会变更pZxid，子节点内容变更不会影响pZxid。
cversion = 0    //子节点的版本号
dataVersion = 11    //数据节点的版本号
aclVersion = 0    //ACL版本号
ephemeralOwner = 0x0    //创建该节点的会话的seddionID。如果该节点是持久节点，那么这个属性值为0。
dataLength = 22    //数据内容的长度
numChildren = 0    //子节点的个数
```
在ZooKeeper中，version属性是用来实现乐观锁机制中的『写入校验』的（保证分布式数据原子性操作）。

## 事务操作
在ZooKeeper中，能改变ZooKeeper服务器状态的操作称为事务操作。一般包括数据节点创建与删除、数据内容更新和客户端会话创建与失效等操作。对应每一个事务请求，ZooKeeper都会为其分配一个全局唯一的事务ID，用ZXID表示，通常是一个64位的数字。每一个ZXID对应一次更新操作，从这些ZXID中可以间接地识别出ZooKeeper处理这些事务操作请求的全局顺序。

## Watcher
Watcher（事件监听器），是ZooKeeper中一个很重要的特性。ZooKeeper允许用户在指定节点上注册一些Watcher，并且在一些特定事件触发的时候，ZooKeeper服务端会将事件通知到感兴趣的客户端上去。该机制是ZooKeeper实现分布式协调服务的重要特性。

## ACL
ZooKeeper采用ACL（Access Control Lists）策略来进行权限控制。ZooKeeper定义了如下5种权限。

* CREATE: 创建子节点的权限。
* READ: 获取节点数据和子节点列表的权限。
* WRITE：更新节点数据的权限。
* DELETE: 删除子节点的权限。
* ADMIN: 设置节点ACL的权限

## ZAB(ZooKeeper Atomic Broadcast)协议
### 概览
ZooKeeper是Google Chubby的开源实现，而Chubby是Paxos的工程实现，所以很多人以为ZooKeeper也是Paxos算法的工程实现。事实上，ZooKeeper并没有完全采用Paxos算法，而是使用了一种称为**ZooKeeper Atomic Broadcast**（ZAB，ZooKeeper原子广播协议）的协议作为其数据一致性的核心算法。

ZAB协议并不像Paxos算法和Raft协议一样，是通用的分布式一致性算法，它是一种特别为ZooKeeper设计的崩溃可恢复的原子广播算法。

基于ZAB协议，ZooKeeper实现了一种主备模式（Leader、Follower）的系统架构来保持集群中各副本之间数据的一致性。

具体的，ZooKeeper使用了一个单一的主进程（Leader）来接收并处理客户端的所有事务请求，并采用ZAB的原子广播协议，将服务器数据的状态变更以事务Proposal的形式广播到所有的副本进程上去（Follower）。ZAB协议的这个主备模型架构保证了同一时刻集群中只能有一个主进程来广播服务器的状态变更，因此能够很好地处理客户端大量的并发请求。另一方面，考虑到分布式环境中，顺序执行的一些状态变更其前后会存在一定的依赖关系，有些状态变更必须依赖于比它早生成的那些状态变更，例如变更C需要依赖变更A和变更B。这样的依赖关系也对ZAB协议提出了一个要求：ZAB协议必须能够保证一个全局的变更序列被顺序应用。也就是说，ZAB协议需要保证如果一个状态变更已经被处理了，那么所有依赖的状态变更都应该已经被提前处理掉了。最后，考虑到主进程在任何时候都有可能出现崩溃退出或重启现象，因此，ZAB协议还需要做到在当前主进程出现上述异常情况的时候，依然能够正常工作。

ZAB协议的核心是定义了对应那些会改变ZooKeeper服务器数据状态的事务请求的处理方式，即：

> 所有事务请求必须由一个全局唯一的服务器来协调处理，这样的服务器被称为Leader服务器，而剩下的其他服务器则成为Follower服务器。Leader服务器负责将一个客户端事务请求转换成一个事务Proposal（提案）并将该Proposal分发给集群中所有的Follower服务器。之后Leader服务器需要等待所有Follower服务器的反馈，一旦超过半数的Follower服务器进行了正确的反馈后，Leader就会再次向所有的Follower服务器分发Commit消息，要求对刚才的Proposal进行提交。

### ZAB协议介绍

ZAB协议包括两种基本的模式，分别是崩溃恢复和消息广播。在整个ZooKeeper集群启动过程中，或是当Leader服务器出现网络中断、崩溃退出与重启等异常情况时，ZAB协议就会进入恢复模式并选举产生新的Leader服务器。当选举产生了新的Leader服务器，同时集群中有过半的机器与该Leader服务器完成了状态同步之后，ZAB协议就会退出恢复模式。其中，状态同步是指数据同步，用来保证集群中存在过半的机器能够和Leader服务器的数据状态保持一致。

崩溃恢复模式包括两个阶段
* Leader选举
* 数据同步

当集群中有过半的Follower服务器完成了和Leader服务器的状态同步，那么整个集群就可以进入消息广播模式了。

### leader选举过程
leader选举有两种情况
* 集群刚启动的时候
* 当前的leader宕机

选举过程：
* 每个server发起一个自己的投票，内容为(myid,ZXID)
* 一台server每收到一个投票，就先和当前选票的ZXID比较，如果收到的投票ZXID比当前的大，则更新选票，如果相等再比较myid
* 统计投票，每次投票后，服务器都会统计投票信息，如果某台server的选票超过集群机器总量的半数，则确定这台server为leader

### Leader选举算法分析
在3.4.0后的Zookeeper的版本只保留了TCP版本的FastLeaderElection选举算法。当一台机器进入Leader选举时，当前集群可能会处于以下两种状态

* 集群中已经存在Leader。
* 集群中不存在Leader。

对于集群中已经存在Leader而言，此种情况一般都是某台机器启动得较晚，在其启动之前，集群已经在正常工作，对这种情况，该机器试图去选举Leader时，会被告知当前服务器的Leader信息，对于该机器而言，仅仅需要和Leader机器建立起连接，并进行状态同步即可。而在集群中不存在Leader情况下则会相对复杂，其步骤如下
1. 第一次投票。
无论哪种导致进行Leader选举，集群的所有机器都处于试图选举出一个Leader的状态，即LOOKING状态，LOOKING机器会向所有其他机器发送消息，该消息称为投票。投票中包含了SID（服务器的唯一标识）和ZXID（事务ID），(SID, ZXID)形式来标识一次投票信息。假定Zookeeper由5台机器组成，SID分别为1、2、3、4、5，ZXID分别为9、9、9、8、8，并且此时SID为2的机器是Leader机器，某一时刻，1、2所在机器出现故障，因此集群开始进行Leader选举。在第一次投票时，每台机器都会将自己作为投票对象，于是SID为3、4、5的机器投票情况分别为(3, 9)，(4, 8)， (5, 8)。
2. 变更投票。
    每台机器发出投票后，也会收到其他机器的投票，每台机器会根据一定规则来处理收到的其他机器的投票，并以此来决定是否需要变更自己的投票，这个规则也是整个Leader选举算法的核心所在，其中术语描述如下

    * vote_sid：接收到的投票中所推举Leader服务器的SID。
    
    * vote_zxid：接收到的投票中所推举Leader服务器的ZXID。

    * self_sid：当前服务器自己的SID。

    * self_zxid：当前服务器自己的ZXID。

    每次对收到的投票的处理，都是对(vote_sid, vote_zxid)和(self_sid, self_zxid)对比的过程。

    * 规则一：如果vote_zxid大于self_zxid，就认可当前收到的投票，并再次将该投票发送出去。

    * 规则二：如果vote_zxid小于self_zxid，那么坚持自己的投票，不做任何变更。

    * 规则三：如果vote_zxid等于self_zxid，那么就对比两者的SID，如果vote_sid大于self_sid，那么就认可当前收到的投票，并再次将该投票发送出去。

    * 规则四：如果vote_zxid等于self_zxid，并且vote_sid小于self_sid，那么坚持自己的投票，不做任何变更。

　　结合上面规则，给出下面的集群变更过程。

<div align="center"><img src="../../resources/images/zookeeper/fast_leader_election.png" ></div>

3. 确定leader
经过第二轮投票后，集群中的每台机器都会再次接收到其他机器的投票，然后开始统计投票，如果一台机器收到了超过半数的相同投票，那么这个投票对应的SID机器即为Leader。此时Server3将成为Leader。

由上面规则可知，通常那台服务器上的数据越新（ZXID会越大），其成为Leader的可能性越大，也就越能够保证数据的恢复。如果ZXID相同，则SID越大机会越大。



## Zookeeper典型应用场景
ZooKeeper是一个高可用的分布式数据管理与协调框架。基于对ZAB算法的实现，该框架能够很好地保证分布式环境中数据的一致性。也是基于这样的特性，使得ZooKeeper成为了解决分布式一致性问题的利器。

### 数据发布与订阅（配置中心）
数据发布与订阅，即所谓的配置中心，顾名思义就是发布者将数据发布到ZooKeeper节点上，供订阅者进行数据订阅，进而达到动态获取数据的目的，实现配置信息的集中式管理和动态更新。

在我们平常的应用系统开发中，经常会碰到这样的需求：系统中需要使用一些通用的配置信息，例如机器列表信息、数据库配置信息等。这些全局配置信息通常具备以下3个特性。

* 数据量通常比较小。
* 数据内容在运行时动态变化。
* 集群中各机器共享，配置一致。

对于这样的全局配置信息就可以发布到ZooKeeper上，让客户端（集群的机器）去订阅该消息。

发布/订阅系统一般有两种设计模式，分别是推（Push）和拉（Pull）模式。
* 推：服务端主动将数据更新发送给所有订阅的客户端。
* 拉：客户端主动发起请求来获取最新数据，通常客户端都采用定时轮询拉取的方式。

ZooKeeper采用的是推拉相结合的方式。如下：

客户端想服务端注册自己需要关注的节点，一旦该节点的数据发生变更，那么服务端就会向相应的客户端发送Watcher事件通知，客户端接收到这个消息通知后，需要主动到服务端获取最新的数据（推拉结合）。

### 命名服务（Naming Service）
命名服务也是分布式系统中比较常见的一类场景。在分布式系统中，通过使用命名服务，客户端应用能够根据指定名字来获取资源或服务的地址，提供者等信息。被命名的实体通常可以是集群中的机器，提供的服务，远程对象等等——这些我们都可以统称他们为名字（Name）。其中较为常见的就是一些分布式服务框架（如RPC、RMI）中的服务地址列表。通过在ZooKeepr里创建顺序节点，能够很容易创建一个全局唯一的路径，这个路径就可以作为一个名字。

ZooKeeper的命名服务即生成全局唯一的ID

### 分布式协调/通知
ZooKeeper中特有Watcher注册与异步通知机制，能够很好的实现分布式环境下不同机器，甚至不同系统之间的通知与协调，从而实现对数据变更的实时处理。使用方法通常是不同的客户端都对ZK上同一个ZNode进行注册，监听ZNode的变化（包括ZNode本身内容及子节点的），如果ZNode发生了变化，那么所有订阅的客户端都能够接收到相应的Watcher通知，并做出相应的处理。

ZK的分布式协调/通知，是一种通用的分布式系统机器间的通信方式。

### 心跳检测
机器间的心跳检测机制是指在分布式环境中，不同机器（或进程）之间需要检测到彼此是否在正常运行，例如A机器需要知道B机器是否正常运行。在传统的开发中，我们通常是通过主机直接是否可以相互PING通来判断，更复杂一点的话，则会通过在机器之间建立长连接，通过TCP连接固有的心跳检测机制来实现上层机器的心跳检测，这些都是非常常见的心跳检测方法。

### 工作进度汇报
在一个常见的任务分发系统中，通常任务被分发到不同的机器上执行后，需要实时地将自己的任务执行进度汇报给分发系统。这个时候就可以通过ZK来实现。在ZK上选择一个节点，每个任务客户端都在这个节点下面创建临时子节点，这样便可以实现两个功能：

* 通过判断临时节点是否存在来确定任务机器是否存活。
* 各个任务机器会实时地将自己的任务执行进度写到这个临时节点上去，以便中心系统能够实时地获取到任务的执行进度。

### Master选举
Master选举可以说是ZooKeeper最典型的应用场景了。比如HDFS中Active NameNode的选举、YARN中Active ResourceManager的选举和HBase中Active HMaster的选举等。

针对Master选举的需求，通常情况下，我们可以选择常见的关系型数据库中的主键特性来实现：希望成为Master的机器都向数据库中插入一条相同主键ID的记录，数据库会帮我们进行主键冲突检查，也就是说，只有一台机器能插入成功——那么，我们就认为向数据库中成功插入数据的客户端机器成为Master。

依靠关系型数据库的主键特性确实能够很好地保证在集群中选举出唯一的一个Master。但是，如果当前选举出的Master挂了，那么该如何处理？谁来告诉我Master挂了呢？显然，关系型数据库无法通知我们这个事件。但是，ZooKeeper可以做到！

利用ZooKeepr的强一致性，能够很好地保证在分布式高并发情况下节点的创建一定能够保证全局唯一性，即ZooKeeper将会保证客户端无法创建一个已经存在的ZNode。也就是说，如果同时有多个客户端请求创建同一个临时节点，那么最终一定只有一个客户端请求能够创建成功。利用这个特性，就能很容易地在分布式环境中进行Master选举了。

成功创建该节点的客户端所在的机器就成为了Master。同时，其他没有成功创建该节点的客户端，都会在该节点上注册一个子节点变更的Watcher，用于监控当前Master机器是否存活，一旦发现当前的Master挂了，那么其他客户端将会重新进行Master选举。

这样就实现了Master的动态选举。

### 分布式锁
分布式锁是控制分布式系统之间同步访问共享资源的一种方式。

分布式锁又分为排他锁和共享锁两种。

#### 排它锁
排他锁（Exclusive Locks，简称X锁），又称为写锁或独占锁。
> 如果事务T1对数据对象O1加上了排他锁，那么在整个加锁期间，只允许事务T1对O1进行读取和更新操作，其他任何事务都不能在对这个数据对象进行任何类型的操作（不能再对该对象加锁），直到T1释放了排他锁。

可以看出，排他锁的核心是如何保证当前只有一个事务获得锁，并且锁被释放后，所有正在等待获取锁的事务都能够被通知到。

##### 定义锁
ZooKeeper上的一个ZNode可以表示一个锁。例如/exclusive_lock/lock节点就可以被定义为一个锁。

##### 获得锁
如上所说，把ZooKeeper上的一个ZNode看作是一个锁，获得锁就通过创建ZNode的方式来实现。所有客户端都去/exclusive_lock节点下创建临时子节点/exclusive_lock/lock。ZooKeeper会保证在所有客户端中，最终只有一个客户端能够创建成功，那么就可以认为该客户端获得了锁。同时，所有没有获取到锁的客户端就需要到/exclusive_lock节点上注册一个子节点变更的Watcher监听，以便实时监听到lock节点的变更情况。

##### 释放锁
因为/exclusive_lock/lock是一个临时节点，因此在以下两种情况下，都有可能释放锁。

* 当前获得锁的客户端机器发生宕机或重启，那么该临时节点就会被删除，释放锁。
* 正常执行完业务逻辑后，客户端就会主动将自己创建的临时节点删除，释放锁。

无论在什么情况下移除了lock节点，ZooKeeper都会通知所有在/exclusive_lock节点上注册了节点变更Watcher监听的客户端。这些客户端在接收到通知后，再次重新发起分布式锁获取，即重复『获取锁』过程。

#### 共享锁
> 共享锁（Shared Locks，简称S锁），又称为读锁。如果事务T1对数据对象O1加上了共享锁，那么T1只能对O1进行读操作，其他事务也能同时对O1加共享锁（不能是排他锁），直到O1上的所有共享锁都释放后O1才能被加排他锁。

总结：可以多个事务同时获得一个对象的共享锁（同时读），有共享锁就不能再加排他锁（因为排他锁是写锁）

## Zookeeper在Kafka中的应用
### Controller选举
* 在刚开始的时候，在zkd上建立一个临时节点/controller，创建成功的broker作为controller,其他broker会监听这个节点
* 如果当前controller挂了，其他broker监听到这个消息，则争相创建此节点，创建成功的broker为新的controller

## ZooKeeper在HBase中的应用

HBase主要用ZooKeeper来实现HMaster选举与主备切换、系统容错、RootRegion管理、Region状态管理和分布式SplitWAL任务管理等。

### HMaster选举与主备切换

1. 创建锁节点
在ZooKeeper上会有一个锁节点，所有的节点在启动的时候，都会去竞争写一个Lock子节点，该节点是临时节点。ZooKeepr能够为我们保证最终只有一个Master能够创建成功。创建成功的那个节点就为Master。

2. 注册Watcher监听
除了Master的其他节点能够通过Watcher机制快速感知到Master的状态

3. 主备切换
当感知到Master出现宕机或重启的异常情况时，其在ZooKeeper上连接的客户端会话会失效，Master的临时ZNode节点会消失。此时其余的节点会重复步骤一。

### 系统容错
当HBase启动时，每个RegionServer都会到ZooKeeper的/hbase/rs节点下创建一个信息节点（下文中，我们称该节点为"rs状态节点"），例如/hbase/rs/[Hostname]，同时，HMaster会对这个节点注册监听。当某个 RegionServer 挂掉的时候，ZooKeeper会因为在一段时间内无法接受其心跳（即 Session 失效），而删除掉该 RegionServer 服务器对应的 rs 状态节点。与此同时，HMaster 则会接收到 ZooKeeper 的 NodeDelete 通知，从而感知到某个节点断开，并立即开始容错工作。

HBase为什么不直接让HMaster来负责RegionServer的监控呢？如果HMaster直接通过心跳机制等来管理RegionServer的状态，随着集群越来越大，HMaster的管理负担会越来越重，另外它自身也有挂掉的可能，因此数据还需要持久化。在这种情况下，ZooKeeper就成了理想的选择。

### RootRegion管理
对应HBase集群来说，数据存储的位置信息是记录在元数据region，也就是RootRegion上的。每次客户端发起新的请求，需要知道数据的位置，就会去查询RootRegion，而RootRegion自身位置则是记录在ZooKeeper上的（默认情况下，是记录在ZooKeeper的/hbase/meta-region-server节点中）。当RootRegion发生变化，比如Region的手工移动、重新负载均衡或RootRegion所在服务器发生了故障等是，就能够通过ZooKeeper来感知到这一变化并做出一系列相应的容灾措施，从而保证客户端总是能够拿到正确的RootRegion信息。

### Region管理
HBase里的Region会经常发生变更，这些变更的原因来自于系统故障、负载均衡、配置修改、Region分裂与合并等。一旦Region发生移动，它就会经历下线（offline）和重新上线（online）的过程。

在下线期间数据是不能被访问的，并且Region的这个状态变化必须让全局知晓，否则可能会出现事务性的异常。对于大的HBase集群来说，Region的数量可能会多达十万级别，甚至更多，这样规模的Region状态管理交给ZooKeeper来做也是一个很好的选择。

### 分布式SplitWAL任务管理
当某台RegionServer服务器挂掉时，由于总有一部分新写入的数据还没有持久化到HFile中，因此在迁移该RegionServer的服务时，一个重要的工作就是从WAL中恢复这部分还在内存中的数据，而这部分工作最关键的一步就是SplitWAL，即HMaster需要遍历该RegionServer服务器的WAL，并按Region切分成小块移动到新的地址下，并进行日志的回放（replay）。

由于单个RegionServer的日志量相对庞大（可能有上千个Region，上GB的日志），而用户又往往希望系统能够快速完成日志的恢复工作。因此一个可行的方案是将这个处理WAL的任务分给多台RegionServer服务器来共同处理，而这就又需要一个持久化组件来辅助HMaster完成任务的分配。当前的做法是，HMaster会在ZooKeeper上创建一个SplitWAL节点（默认情况下，是/hbase/SplitWAL节点），将"哪个RegionServer处理哪个Region"这样的信息以列表的形式存放到该节点上，然后由各个RegionServer服务器自行到该节点上去领取任务并在任务执行成功或失败后再更新该节点的信息，以通知HMaster继续进行后面的步骤。ZooKeeper在这里担负起了分布式集群中相互通知和信息持久化的角色。


# Zookeeper 常见面试题

* zookeeper是什么   
分布式的、开源的分布式应用程序协调服务，原本是Hadoop、HBase的一个重要组件。它为分布式应用提供一致性服务的软件，包括：配置维护、域名服务、分布式同步、组服务等。

* zookeeper是如何保证事务的一致性的   
zookeeper采用了递增的事务Id来标识，所有的proposal都在被提出的时候加上了zxid，zxid实际上是一个64位的数字，高32位是epoch用来标识leader是否发生改变，如果有新的leader产生出来，epoch会自增，低32位用来递增计数。当新产生proposal的时候，会依据数据库的两阶段过程，首先会向其他的server发出事务执行请求，如果超过半数的机器都能执行并且能够成功，那么就会开始执行

* Paxos算法& Zookeeper使用协议
    相同点:
    * 一个Leader协调N个Follower
    * Leader需要等待超过半数的Follower做出正确反馈后才能进行提案
    不同点:
    * ZAB用来构建高可用的分布式数据主备系统（Zookeeper），Paxos是用来构建分布式一致性状态机系统。
    * 

* leader选举过程

* znode类型
    * 持久化目录节点
    创建之后一直存在，除非有删除操作，创建节点的客户端会话失效也不影响此节点。
    * 持久化顺序编号目录节点
    跟持久一样，就是父节点在创建下一级子节点的时候，记录每个子节点创建的先后顺序，会给每个子节点名加上一个数字后缀。
    * 临时节点
    创建客户端会话失效（注意是会话失效，不是连接断了），节点也就没了。不能建子节点。**不能创建子节点。**
    * 临时顺序编号节点

* Zookeeper对节点的watch监听通知是永久的吗？    
zk不保证性能，所以不能是永久的。
client会对某个node建立watcher事件,当这些znode发生变化是，会通知client,然后client会做出相应的策略。
官方声明：一个Watch事件是一个**一次性**的触发器，当被设置了Watch的数据发生了改变的时候，则服务器将这个改变发送给设置了Watch的客户端，以便通知它们。

* zk的配置管理   
程序分布式的部署在不同的机器上，将程序的配置信息放在zk的znode下，当有配置发生改变时，也就是znode发生变化时，可以通过改变zk中某个目录节点的内容，利用water通知给各个客户端 从而更改配置。

* zk命名服务  
命名服务是指通过指定的名字来获取资源或者服务的地址，利用zk创建一个全局的路径，这个路径就可以作为一个名字，指向集群中的集群，提供的服务的地址，或者一个远程的对象等等。

* 一个客户端修改了某个节点的数据，其它客户端能够马上获取到这个最新数据吗?  
如果需要，调用sync()

* 临时节点什么时候被删除？   
连接断了之后，ZK不会马上移除临时数据，只有当**SESSIONEXPIRED**之后，才会把这个会话建立的临时数据移除。因此，用户需要谨慎设置**Session_TimeOut**

# 最新zk面试题
* 客户端对ServerList的轮询机制是什么？
    随机，客户端在初始化( new ZooKeeper(String connectString, int sessionTimeout, Watcher watcher) )的过程中，将所有Server保存在一个List中，然后随机打散，形成一个环。之后从0号位开始一个一个使用。
    两个注意点：
    1. Server地址能够重复配置，这样能够弥补客户端无法设置Server权重的缺陷，但是也会加大风险。（比如: 192.168.1.1:2181,192.168.1.1:2181,192.168.1.2:2181). 
    2. 如果客户端在进行Server切换过程中耗时过长，那么将会收到SESSION_EXPIRED. 这也是上面第1点中的加大风险之处。

* 客户端如何正确处理CONNECTIONLOSS和SESSIONEXPIRED两类异常
    **在ZooKeeper中，服务器和客户端之间维持的是一个长连接**，在 SESSION_TIMEOUT 时间内，**服务器会确定客户端是否正常连接(客户端会定时向服务器发送heart_beat),服务器重置下次SESSION_TIMEOUT时间**。因此，在正常情况下，Session一直有效，并且zk集群所有机器上都保存这个Session信息。在出现问题情况下，客户端与服务器之间连接断了（客户端所连接的那台zk机器挂了，或是其它原因的网络闪断），这个时候客户端会主动在地址列表（初始化的时候传入构造方法的那个参数connectString）中选择新的地址进行连接。
    好了，上面基本就是服务器与客户端之间维持长连接的过程了。在这个过程中，用户可能会看到两类客异常CONNECTIONLOSS(连接断开) 和SESSIONEXPIRED(Session 过期)。
    CONNECTIONLOSS发生在上面红色文字部分，应用在进行操作A时，发生了CONNECTIONLOSS，此时用户不需要关心我的会话是否可用，应用所要做的就是等待客户端帮我们自动连接上新的zk机器，一旦成功连接上新的zk机器后，确认刚刚的操作A是否执行成功了。
    SESSIONEXPIRED发生在上面蓝色文字部分，这个通常是zk客户端与服务器的连接断了，试图连接上新的zk机器，这个过程如果耗时过长，超过 SESSION_TIMEOUT 后还没有成功连接上服务器，那么服务器认为这个session已经结束了（服务器无法确认是因为其它异常原因还是客户端主动结束会话），开始清除和这个会话有关的信息，包括这个会话创建的临时节点和注册的Watcher。在这之后，客户端重新连接上了服务器在，但是很不幸，服务器会告诉客户端SESSIONEXPIRED。此时客户端要做的事情就看应用的复杂情况了，总之，要重新实例zookeeper对象，重新操作所有临时数据（包括临时节点和注册Watcher）。

* 不同客户端对同一个节点能否获取相同的数据
* 一个客户端修改某个节点的数据，其他客户端能够获取到最新的数据
    zk不能确保用户获取最新的数据，如果需要则用户请求数据之前需要调用sync()方法。通常情况下
    1. 对获取数据的最新版本不敏感 
    2. 一个客户端修改了数据，其他客户端不需要立刻获取最新数据)
    可以不关心这一点。
    在其它情况下，最清晰的场景是这样：ZK客户端A对 /my_test 的内容从 v1->v2, 但是ZK客户端B对 /my_test 的内容获取，依然得到的是 v1. 请注意，这个是实际存在的现象，当然延时很短。解决的方法是客户端B先调用 sync(), 再调用 getData().

* zk为什么不提供永久的watcher监听机制？
    * zk不保证性能。当前 **watcher通知是一次性的只保证一次** 。
    * 当发生CONNECTIONLOSS时，如果在SESSIONE TIMEOUT之前重新连接上，之前的watcher还有效。
    * **节点版本的变化也会触发watcher**，不只是数据项。
    * 如果一个节点被删除了，那么这个节点上的所有watcher都会被删除

* 能够收到每次节点变化的通知？
如果节点变化频率很高的话，不能。

* 能为临时节点创建子节点吗？
不能

* 能够拒绝单个ip对zk的操作？
zk不提供这个功能，可以通过iptable来操作

* 在getChildren(String path, boolean watch)这个API中，是注册了对节点子节点的变化，那么子节点的子节点变化能通知吗?
不能

* zookeeper是否支持动态扩容？
3.5.0开始支持
# 参考
* [ZooKeeper原理与应用](https://www.jianshu.com/p/84ad63127cd1)
* [raft对比ZAB](https://blog.csdn.net/weixin_36145588/article/details/78477159?locationNum=10&fps=1)
* [【分布式】Zookeeper的Leader选举](http://www.cnblogs.com/leesf456/p/6107600.html)
* [zk面试专题](https://blog.csdn.net/qq_29842085/article/details/79443128)