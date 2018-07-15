# Kafka
<!-- TOC -->

- [基本概念](#基本概念)
- [核心接口](#核心接口)
- [Topics](#topics)
    - [顺序](#顺序)
- [Distribution](#distribution)
- [Geo-Replication](#geo-replication)
- [Producers](#producers)
- [Consumers](#consumers)
- [Guarantees](#guarantees)
- [Kafka as a Messaging System](#kafka-as-a-messaging-system)
    - [消息队列](#消息队列)
    - [发布/订阅](#发布订阅)
- [Kafka as a Storage System](#kafka-as-a-storage-system)
- [Kafka for Stream Processing](#kafka-for-stream-processing)
- [总结](#总结)
- [参考](#参考)

<!-- /TOC -->

## 基本概念
* Kafka是运行在一个集群上，所以它可以拥有一个或多个服务节点；
* Kafka集群将消息存储在特定的文件中，对外表现为Topics；
* 每条消息记录都包含一个key,消息内容以及时间戳；

1. Broker:一个单独的Kafka server就是一个Broker,Broker的主要工作就是接收生产者发送来的消息,分配offset,然后将包装过的数据保存到磁盘上;此外,Broker还会接收消费者和其他Broker的请求,根据请求的类型进行相应的处理然后返回响应。多个Broker可以做成一个Cluster(集群)对外提供服务,每个Cluster当中会选出一个Broker来担任Controller,Controller是Kafka集群的指挥中心,其他的Broker则听从Controller指挥实现相应的功能。Controller负责管理分区的状态、管理每个分区的副本状态、监听zookeeper中数据的变化等。Controller也是一主多从的实现,所有的Broker都会监听Controller Leader的状态,当Leader Controller出现了故障的时候就重新选举新的Controller Leader。

2. 消息:消息是Kafka中最基本的消息单元。消息由一串字节组成,其中主要由key和value构成,key和value都是字节数组。key的主要作用是根据一定的策略,将这个消息路由到指定的分区中,这样就可以保证包含同一个key的消息全部写入同一个分区
3. Topic:Topic是用于存储消息的逻辑概念,Topic可以看做是一个消息的集合。每个Topic可以有多个生产者向其中push消息,也可以有多个消费者向其中pull消息。
4. 分区(partition):每一个Topic都可以划分成多个分区(每一个Topic都至少有一个分区),不同的分区会分配在不同的Broker上以对Kafka进行水平扩展从而增加Kafka的并行处理能力。同一个Topic下的不同分区包含的消息是不同的。每一个消息在被添加到分区的时候,都会被分配一个offset,他是消息在此分区中的唯一编号,此外,Kafka通过offset保证消息在分区中的顺序,offset的顺序性不跨分区,也就是说在Kafka的同一个分区中的消息是有序的,不同分区的消息可能不是有序的。
5. Log:分区在逻辑上对应着一个Log,当生产者将消息写入分区的时候,实际上就是写入到了一个Log中。Log是一个逻辑概念,对应的是一个磁盘上的文件夹。Log由多个Segment组成,每一个Segment又对应着一个日志文件和一个索引文件。
6. 副本:Kafka对消息进行了冗余备份,每一个分区都可以有多个副本,每一个副本中包含的消息是相同的(但不保证同一时刻下完全相同)。副本的类型分为Leader和Follower,当分区只有一个副本的时候,该副本属于Leader,没有
Follower。Kafka的副本具有一定的同步机制,在每个副本集合中,都会选举出一个副本作为Leader副本,Kafka在不同的场景中会采用不同的选举策略。Kafka中所有的读写请求都由选举出的Leader副本处理,其他的都作为Follower副本,Follower副本仅仅是从Leader副本中把数据拉取到本地之后,同步更新到自己的Log中。

7. ISR集合:ISR集合表示的是目前可用(alive)且消息量与Leader相差不多的副本集合,即整个副本集合的子集。ISR集合中副本所在的节点都与ZK保持着连接,此外,副本的最后一条消息的offset与Leader副本的最后一条消息的offset之间的差值不能超出指定的阈值。每一个分区的Leader副本都维护此分区的ISR集合。如上面所述,Leader副本进行了消息的写请求,Follower副本会从Leader上拉取写入的消息,第二个过程中会存在Follower副本中的消息数量少于Leader副本的状态,只要差值少于指定的阈值,那么此时的副本集合就是ISR集合。


## 核心接口
* Producer API允许了应用可以向Kafka中的topics发布消息；
* Consumer API允许了应用可以订阅Kafka中的topics,并消费消息；
* Streams API允许应用可以作为消息流的处理者，比如可以从topicA中消费消息，处理的结果发布到topicB中；
* Connector API提供Kafka与现有的应用或系统适配功能，比如与数据库连接器可以捕获表结构的变化；

<div align="center"><img src="../resources/images/kafka/apache-kafka.jpg" width="400"></div>

## Topics
Topics是一些主题的集合，更通俗的说Topic就像一个消息队列，生产者可以向其写入消息，消费者可以从中读取消息，一个Topic支持多个生产者或消费者同时订阅它，所以其扩展性很好。Topic又可以由一个或多个partition（分区）组成，比如下图：
<div align="center"><img src="../resources/images/kafka/topics.png" width="400"></div>

### 顺序
每个partition中的消息都是有序的，但是partition之间顺序就不能保证了,若topics有多个partition，生产者的消息可以指定或者由系统根据算法分配到指定分区，若你需要所有消息都是有序的，那么你最好只用一个分区。另外partition支持消息位移读取，消息位移有消费者自身管理，比如下图：
<div align="center"><img src="../resources/images/kafka/partition.png" width="400"></div>

由上图可以看出，不同消费者对同一分区的消息读取互不干扰，消费者可以通过设置消息位移（offset）来控制自己想要获取的数据，比如可以从头读取，最新数据读取，重读读取等功能。

关于Topic的分区策略以及与消费者间平衡后续文章会继续深入讲解。

## Distribution
上文说到过，Kafka是一个分布式的消息系统，所以当我们配置了多个Kafka Server节点后，它就拥有分布式的能力，比如容错等，partition会被分布在各个Server节点上，同时它们中间又有一个leader，它会处理所有的读写请求，其他followers会复制leader上的数据信息，一旦当leader因为某些故障而无法提供服务后，就会有一个follower被推举出来成为新的leader来处理这些请求。

## Geo-Replication
异地备份是作为主流分布式系统的基础功能，用于集群中数据的备份和恢复，Kafka利用MirrorMaker来实现这个功能，用户只需简单的进行相应配置即可。

## Producers
Producers作为消息的生产者，可以自己指定将消息发布到订阅Topic中的指定分区，策略可以自己指定，比如语义或者结构类似的消息发布在同一分区，当然也可以由系统循环发布在每一个分区上。

## Consumers
Consumers是一群消费者的集合，可以称之为消费者组，是一种更高层次的的抽象，向Topic订阅消费消息的单位是Consumers，当然它其中也可以只有一个消费者（consumer）。下面是关于consumer的两条原则：
* 假如所有消费者都在同一个消费者组中，那么它们将协同消费订阅Topic的部分消息（根据分区与消费者的数量分配），保存负载平衡；
* 假如所有消费者都在不同的消费者组中，并且订阅了同个Topic，那么它们将可以消费Topic的所有消息；
下面是一个简单的例子，帮助大家理解：
<div align="center"><img src="../resources/images/kafka/consumers.png"></div>

上图中有两个Server节点，有一个Topic被分为四个分区（P0-P4)分别被分配在两个节点上，另外还有两个消费者组（GA，GB），其中GA有两个消费者实例，GB有四个消费者实例。
从图中我们可以看出，首先订阅Topic的单位是消费者组，另外我们发现Topic中的消息根据一定规则将消息推送给具体消费者，主要原则如下：
* 若消费者数小于partition数，且消费者数为一个，那么它就消费所有消息；
* 若消费者数小于partition数，假设消费者数为N，partition数为M，那么每个消费者能消费的分区数为M/N或M/N+1；
* 若消费者数等于partition数，那么每个消费者都会均等分配到一个分区的消息；
* 若消费者数大于partition数，则将会出现部分消费者得不到消息分区，出现空闲的情况；

## Guarantees
kafka作为一个高水准的系统，提供了以下的保证：

* 消息的添加是有序的，生产者越早向订阅的Topic发送的消息，会更早的被添加到Topic中，当然它们可能被分配到不同的分区；
* 消费者在消费Topic分区中的消息时是有序的；
* 对于有N个复制节点的Topic，系统可以最多容忍N-1个节点发生故障，而不丢失任何提交给该Topic的消息；

## Kafka as a Messaging System
说了这么多，前面也讲了消息系统的演变过程，那么Kafka相比其他的消息系统优势具体在哪里？ 传统的消息系统模型主要有两种：消息队列和发布/订阅。
### 消息队列
特性 | 描述
-- | -- 
表现形式 | 一组消费者从消息队列中获取消息，一条消息会被推送给组中的某一个消费者 |
优势 | 水平扩展，可以将消息数据分开处理 |
劣势 | 消息队列不是多用户的，当一条消息记录被一个进程读取后，消息便会丢失 |

### 发布/订阅
特性 | 描述
-- | -- 
表现形式 | 消息会广播发送给所有订阅者 |
优势 | 可以多进程共享消息 |
劣势 | 每个消费者都会获得所有消息，无法通过添加消费进程提高处理效率 |

从上面两个表中可以看出两种传统的消息系统模型的优缺点，所以Kafka在前人的肩膀上进行了优化，吸收他们的优点，主要体现在以下两方面：
* 通过Topic方式来达到消息队列的功能
* 通过消费者组这种方式来达到发布/订阅的功能

## Kafka as a Storage System
存储消息也是消息系统的一大功能，Kafka相对普通的消息队列存储来说，它的表现实在好的太多，首先Kafka支持写入确认，保证消息写入的正确性和连续性，同时Kafka还会对写入磁盘的数据进行复制备份，来实现容错，另外Kafka对磁盘的使用结构是一致的，就说说不管你的服务器目前磁盘存储的消息数据有多少，它添加消息数据的效率是相同的。
Kafka的存储机制很好的支持消费者可以随意控制自身所需要读取的数据，在很多时候你也可以将Kafka作为一个高性能，低延迟的分布式文件系统。

## Kafka for Stream Processing
Kafka作为一个完美主义代表者，光有普通的读写，存储等功能是不够的，它还提供了实时处理消息流的接口。
很多时候原始的数据并不是我们想要的，我们想要的是经过处理后的数据结果，比如通过一天的搜索数据得出当天的搜索热点等，你可以利用Streams API来实现自己想要的功能，比如从输入Topic中获取数据，然后再发布到具体的输出Topic中。
Kafka的流处理可以解决诸如处理无序数据、数据的复杂转换等问题。

## 总结
消息传递、存储、流处理这么功能单一来看确实很普通，但如何把它们完美的结合到一起，就是一种优雅的体现，Kafka做到了这一点。
相比HDFS分布式文件存储系统，虽然它能支持高效存储并且批处理数据，但是它只支持处理过去的历史数据。
相比普通的消息系统来说，虽然能处理现在至未来的数据，但是它并不没有存储历史的数据。
Kafka集众家之所长，使整个系统能兼顾各方面的需求，可以用一个词来说： “完美”！

## 参考
1. [Kafka学习笔记（一） ：为什么需要Kafka？](https://juejin.im/post/5ab6fcd66fb9a028bc2db39d)
2. [初谈kafka](https://juejin.im/post/5a8e7f296fb9a0635a6573e9)