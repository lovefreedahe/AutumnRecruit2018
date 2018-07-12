# Kafka

## 基本概念
* Kafka是运行在一个集群上，所以它可以拥有一个或多个服务节点；
* Kafka集群将消息存储在特定的文件中，对外表现为Topics；
* 每条消息记录都包含一个key,消息内容以及时间戳；

1. Broker:一个单独的Kafka server就是一个Broker,Broker的主要工作就是接收生产者发送来的消息,分配offset,然后将包装过的数据保存到磁盘上;此外,Broker还会接收消费者和其他Broker的请求,根据请求的类型进行相应的处理然后返回响应。多个Broker可以做成一个Cluster(集群)对外提供服务,每个Cluster当中会选出一个Broker来担任Controller,Controller是Kafka集群的指挥中心,其他的Broker则听从Controller指挥实现相应的功能。Controller负责管理分区的状态、管理每个分区的副本状态、监听zookeeper中数据的变化等。Controller也是一主多从的实现,所有的Broker都会监听Controller Leader的状态,当Leader Controller出现了故障的时候就重新选举新的Controller Leader。

作者：FuyunWang
链接：https://juejin.im/post/5a8e7f296fb9a0635a6573e9
来源：掘金
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

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



## 参考
1. [Kafka学习笔记（一） ：为什么需要Kafka？](https://juejin.im/post/5ab6fcd66fb9a028bc2db39d)