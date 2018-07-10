# Kafka

## 基本概念
* Kafka是运行在一个集群上，所以它可以拥有一个或多个服务节点；
* Kafka集群将消息存储在特定的文件中，对外表现为Topics；
* 每条消息记录都包含一个key,消息内容以及时间戳；

## 核心接口
* Producer API允许了应用可以向Kafka中的topics发布消息；
* Consumer API允许了应用可以订阅Kafka中的topics,并消费消息；
* Streams API允许应用可以作为消息流的处理者，比如可以从topicA中消费消息，处理的结果发布到topicB中；
* Connector API提供Kafka与现有的应用或系统适配功能，比如与数据库连接器可以捕获表结构的变化；

<div align="center"><img src="../resources/images/kafka/apache-kafka.jpg" width="400"></div>









## 参考
1. [Kafka学习笔记（一） ：为什么需要Kafka？](https://juejin.im/post/5ab6fcd66fb9a028bc2db39d)