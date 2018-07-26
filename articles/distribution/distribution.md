
<!-- TOC -->

- [CAP](#cap)
    - [一致性](#一致性)
    - [可用性](#可用性)
    - [分区容忍性](#分区容忍性)
    - [权衡](#权衡)
- [BASE](#base)
    - [基本可用](#基本可用)
    - [软状态](#软状态)
    - [最终一致性](#最终一致性)
- [2PC](#2pc)
    - [运行过程](#运行过程)
        - [1、准备阶段](#1准备阶段)
        - [2、提交阶段](#2提交阶段)
    - [存在的问题](#存在的问题)
        - [1、同步阻塞](#1同步阻塞)
        - [2、单点问题](#2单点问题)
        - [3、数据不一致](#3数据不一致)
        - [4、太过保守](#4太过保守)
- [Paxox](#paxox)
    - [执行过程](#执行过程)
        - [第一步](#第一步)
        - [第二步](#第二步)
        - [第三步](#第三步)
        - [第四步](#第四步)
        - [第五步](#第五步)
    - [约束条件](#约束条件)
        - [1、正确性](#1正确性)
        - [2、可终止性](#2可终止性)
- [Raft协议](#raft协议)
    - [单个Candidate的竞选](#单个candidate的竞选)
    - [多个Candidate竞选](#多个candidate竞选)
    - [日志复制](#日志复制)
- [分布式锁](#分布式锁)
    - [共享锁(S锁)](#共享锁s锁)
    - [排它锁(X锁)](#排它锁x锁)
- [参考资料](#参考资料)

<!-- /TOC -->

# CAP
分布式系统不可能同时满足一致性（C：Consistency）、可用性（A：Availability）和分区容忍性（P：Partition Tolerance），最多只能同时满足其中两项。
<div align="center"><img src="../../resources/images/distribution/CAP_1.jpg" width="600"></div>

## 一致性
一致性指的是多个数据副本是否能保持一致的特性。

在一致性的条件下，系统在执行数据更新操作之后能够从一致性状态转移到另一个一致性状态。

对系统的一个数据更新成功之后，如果所有用户都能够读取到最新的值，该系统就被认为具有强一致性。

## 可用性
可用性指分布式系统在面对各种异常时可以提供正常服务的能力，可以用系统可用时间占总时间的比值来衡量，4 个 9 的可用性表示系统 99.99% 的时间是可用的。

在可用性条件下，系统提供的服务一直处于可用的状态，对于用户的每一个操作请求总是能够在有限的时间内返回结果。

## 分区容忍性
网络分区指分布式系统中的节点被划分为多个区域，每个区域内部可以通信，但是区域之间无法通信。

在分区容忍性条件下，分布式系统在遇到任何网络分区故障的时候，仍然需要能对外提供一致性和可用性的服务，除非是整个网络环境都发生了故障。

## 权衡
在分布式系统中，分区容忍性必不可少，因为需要总是假设网络是不可靠的。因此，CAP 理论实际在是要在可用性和一致性之间做权衡。

可用性和一致性往往是冲突的，很难都使它们同时满足。在多个节点之间进行数据同步时，
* 为了保证一致性（CP），就需要让所有节点下线成为不可用的状态，等待同步完成；
* 为了保证可用性（AP），在同步过程中允许读取所有节点的数据，但是数据可能不一致。
<div align="center"><img src="../../resources/images/distribution/CAP_2.jpg" width="600"></div>

# BASE
BASE 是基本可用（Basically Available）、软状态（Soft State）和最终一致性（Eventually Consistent）三个短语的缩写。

BASE 理论是对 CAP 中一致性和可用性权衡的结果，它的理论的核心思想是：即使无法做到强一致性，但每个应用都可以根据自身业务特点，采用适当的方式来使系统达到最终一致性。
<div align="center"><img src="../../resources/images/distribution/BASE_1.png"></div>

## 基本可用
指分布式系统在出现故障的时候，保证核心可用，允许损失部分可用性。

例如，电商在做促销时，为了保证购物系统的稳定性，部分消费者可能会被引导到一个降级的页面。

## 软状态
指允许系统中的数据存在中间状态，并认为该中间状态不会影响系统整体可用性，即允许系统不同节点的数据副本之间进行同步的过程存在延时。

## 最终一致性
最终一致性强调的是系统中所有的数据副本，在经过一段时间的同步后，最终能达到一致的状态。

ACID(关系型数据库采纳的原则) 要求强一致性，通常运用在传统的数据库系统上。而 BASE 要求最终一致性，通过牺牲强一致性来达到可用性，通常运用在大型分布式系统中。

在实际的分布式场景中，不同业务单元和组件对一致性的要求是不同的，因此 ACID 和 BASE 往往会结合在一起使用。
# 2PC
两阶段提交（Two-phase Commit，2PC）

主要用于实现分布式事务，分布式事务指的是事务操作跨越多个节点，并且要求满足事务的 ACID 特性。

通过引入协调者（Coordinator）来调度参与者的行为，，并最终决定这些参与者是否要真正执行事务。

## 运行过程
### 1、准备阶段
协调者询问参与者事务是否执行成功，参与者发回事务执行结果。
<div align="center"><img src="../../resources/images/distribution/2PC_1.jpg"></div>

### 2、提交阶段
如果事务在每个参与者上都执行成功，事务协调者发送通知让参与者提交事务；否则，协调者发送通知让参与者回滚事务。
<div align="center"><img src="../../resources/images/distribution/2PC_2.jpg"></div>
需要注意的是，在准备阶段，参与者执行了事务，但是还未提交。只有在提交阶段接收到协调者发来的通知后，才进行提交或者回滚。

## 存在的问题
### 1、同步阻塞
所有事务参与者在等待其它参与者响应的时候都处于同步阻塞状态，无法进行其它操作。

### 2、单点问题
协调者在 2PC 中起到非常大的作用，发生故障将会造成很大影响，特别是在阶段二发生故障，所有参与者会一直等待状态，无法完成其它操作。

### 3、数据不一致
在阶段二，如果协调者只发送了部分 Commit 消息，此时网络发生异常，那么只有部分参与者接收到 Commit 消息，也就是说只有部分参与者提交了事务，使得系统数据不一致。

### 4、太过保守
任意一个节点失败就会导致整个事务失败，没有完善的容错机制。

# Paxox
用于达成共识性问题，即对多个节点产生的值，该算法能保证只选出唯一一个值。

主要有三类节点：
* 提议者(Proposer):提议一个值
* 接受者(Accepter):对每个提议进行投票
* 告知者(Learner):被告知投票结果,不参与投票过程
<div align="center"><img src="../../resources/images/distribution/Paxos_1.jpg"></div>

## 执行过程
### 第一步
规定一个提议包含两个字段：[n, v]，其中 n 为序号（具有唯一性），v 为提议值。

下图演示了两个 Proposer 和三个 Acceptor 的系统中运行该算法的初始过程，每个 Proposer 都会向所有 Acceptor 发送提议请求。
<div align="center"><img src="../../resources/images/distribution/Paxos_2.png"></div>

### 第二步
当 Acceptor 接收到一个提议请求，包含的提议为 [n1, v1]，并且之前还未接收过提议请求，那么发送一个提议响应，设置当前接收到的提议为 [n1, v1]，并且保证以后不会再接受序号小于 n1 的提议。

如下图，Acceptor X 在收到 [n=2, v=8] 的提议请求时，由于之前没有接收过提议，因此就发送一个 [no previous] 的提议响应，设置当前接收到的提议为 [n=2, v=8]，并且保证以后不会再接受序号小于 2 的提议。其它的 Acceptor 类似。
<div align="center"><img src="../../resources/images/distribution/Paxos_3.jpg"></div>
### 第三步
如果 Acceptor 接收到一个提议请求，包含的提议为 [n2, v2]，并且之前已经接收过提议 [n1, v1]。如果 n1 > n2，那么就丢弃该提议请求；否则，发送提议响应，该提议响应包含之前已经接收过的提议 [n1, v1]，设置当前接收到的提议为 [n2, v2]，并且保证以后不会再接受序号小于 n2 的提议。

如下图，Acceptor Z 收到 Proposer A 发来的 [n=2, v=8] 的提议请求，由于之前已经接收过 [n=4, v=5] 的提议，并且 n > 2，因此就抛弃该提议请求；Acceptor X 收到 Proposer B 发来的 [n=4, v=5] 的提议请求，因为之前接收到的提议为 [n=2, v=8]，并且 2 <= 4，因此就发送 [n=2, v=8] 的提议响应，设置当前接收到的提议为 [n=4, v=5]，并且保证以后不会再接受序号小于 4 的提议。Acceptor Y 类似。
<div align="center"><img src="../../resources/images/distribution/Paxos_4.jpg"></div>

### 第四步
当一个 Proposer 接收到超过一半 Acceptor 的提议响应时，就可以发送接受请求。

Proposer A 接收到两个提议响应之后，就发送 [n=2, v=8] 接受请求。该接受请求会被所有 Acceptor 丢弃，因为此时所有 Acceptor 都保证不接受序号小于 4 的提议。

Proposer B 过后也收到了两个提议响应，因此也开始发送接受请求。需要注意的是，接受请求的 v 需要取它收到的最大 v 值，也就是 8。因此它发送 [n=4, v=8] 的接受请求。
<div align="center"><img src="../../resources/images/distribution/Paxos_5.png"></div>

### 第五步
Acceptor 接收到接受请求时，如果序号大于等于该 Acceptor 承诺的最小序号，那么就发送通知给所有的 Learner。当 Learner 发现有大多数的 Acceptor 接收了某个提议，那么该提议的提议值就被 Paxos 选择出来。
<div align="center"><img src="../../resources/images/distribution/Paxos_5.jpg"></div>

## 约束条件
### 1、正确性
指只有一个提议值会生效。

因为 Paxos 协议要求每个生效的提议被多数 Acceptor 接收，并且 Acceptor 不会接受两个不同的提议，因此可以保证正确性。

### 2、可终止性
指最后总会有一个提议生效。

Paxos 协议能够让 Proposer 发送的提议朝着能被大多数 Acceptor 接受的那个提议靠拢，因此能够保证可终止性。



# Raft协议
Raft是一种分布式一致性协议，Raft和Paxos类似，但是更容易理解和实现。
Raft主要用来竞选主节点。
[Raft动画演示](http://thesecretlivesofdata.com/raft/)
## 单个Candidate的竞选
节点分为三种：Follower、Candidate和Leader。Leader会周期性的发送心跳包给Follower。每个Follower都设置了一个随机的竞选超时时间，一般为150ms~300ms， 如果在这个时间内没有接收到leader的心跳包，就会变成Candidate，进入竞选阶段。
* 下图表示一个分布式系统的最初阶段，此时只有Follower，没有Leader，当所有Node A等待Leader发送心跳包并且时间超时后， 进入竞选阶段。
<div align="center"><img src="../../resources/images/distribution/raft_1.gif" width="600"></div>

* 此时A发送投票请求给其他节点。
<div align="center"><img src="../../resources/images/distribution/raft_2.gif" width="600"></div>

* 其他节点会对请求进行回复，如果超过一半节点回复了，那么该Candidate变成Leader。
<div align="center"><img src="../../resources/images/distribution/raft_3.gif" width="600"></div>

* 之后Leader(Node A)会周期性的发送心跳包给Follower(B && C)， Follower收到心跳包会重新开始计时。
<div align="center"><img src="../../resources/images/distribution/raft_4.gif" width="600"></div>
## 多个Candidate竞选
* 如果有多个Follower成为Candidate， 并且获得的投票数相同，那么就要重新开始投票，例如下图中Candidate B和Candidate D都获得两票，因此需要重新开始投票。
<div align="center"><img src="../../resources/images/distribution/raft_5.gif" width="600"></div>  

* 当重新开始投票的时候，每个节点获得的随机竞选超时时间不同，因此在遇到投票票数相同的概率很低。
<div align="center"><img src="../../resources/images/distribution/raft_6.gif" width="600"></div>

## 日志复制
* 来自客户端的修改都会发送给Leader。注意该修改还没被提交，只是写入日志中。
<div align="center"><img src="../../resources/images/distribution/raft_7.gif" width="600"></div>

* Leader会把修改发送给到所有的Follower
<div align="center"><img src="../../resources/images/distribution/raft_8.gif" width="600"></div>

* Leader会等待大多数的Follower也进行了修改，然后才将修改提交。
<div align="center"><img src="../../resources/images/distribution/raft_9.gif" width="600"></div>

* 此时Leader会通知所有的Follower也提交修改，此时所有节点达成一致。
<div align="center"><img src="../../resources/images/distribution/raft_10.gif" width="600"></div>

# 分布式锁
## 共享锁(S锁)
又称读锁，事务T对数据对象A加S锁之后，该事务T可以读A但是无法修改A，其他事务只能再对数据对象A加S锁,而不能加X锁，知道T释放A上的S锁。这保证了其他事务可以读A，但是在T释放A上的锁之前无法修改A。
## 排它锁(X锁)
又称写锁，事务T对数据对象A加X锁之后，该事物既可以读A又可以修改A，其他事务不可以给A加任和锁，直到T释放A上的X锁。这保证了其他事务在T释放A上的X锁之前，既不能读A又不能修改A。

# 参考资料

* [Raft: Understandable Distributed Consensus](http://thesecretlivesofdata.com/raft/)
