# ZooKeeper原理与应用

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









# 参考
* [ZooKeeper原理与应用](https://www.jianshu.com/p/84ad63127cd1)