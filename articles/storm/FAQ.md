# FAQ
## 最佳实践
### 针对配置 Storm+Trident, 您可以给我哪些经验呢?
* worker 的数量是机器数量的倍数; 并行度是 worker 数量的倍数; kafka partitions 的数量是 spout 并行度的倍数
* 每个机器上的每个 topology 使用一个 worker
* Start with fewer, larger aggregators, one per machine with workers on it
* 使用 isolation scheduler（隔离调度器）
* 每个 worker 使用一个 acker -- 0.9 版本默认是这样的, 但是更早的版本没有这样.
* 启用 GC 日志记录; 在正常情况下, 你应该看到很少的 major GC.
* set the trident batch millis to about 50% of your typical end-to-end latency.
* Start with a max spout pending that is for sure too small -- one for trident, or the number of executors for storm -- and increase it until you stop seeing changes in the flow. You'll probably end up with something near 2*(throughput in recs/sec)*(end-to-end latency) (2x the Little's law capacity).

### 如何避免 worker 总是出现莫名其妙的故障的问题
* 您是否有对 log directory（日志目录）的写入权限
*您扩大过你的 heap 大小吗?
* 是否所有的 workers 都安装了正确的 libraries（函数库）?
*是否 zookeeper 的 hostname（主机名）仍然设置为 localhost 了?
* 您提供了一个正确, 唯一的 hostname（主机名） -- 它可以解析回机器上 -- 对于每个 worker, 并且将它们放入 storm conf 文件中?
* 您是否双向开启了 firewall/securitygroup 的权限 a) 所有的 workers, b) storm master, c) zookeeper? 另外, 从 workers 到您的 topology 访问的任何 kafka/kestrel/database/etc ? 使用 netcat 来检测下对应的 ports（端口）并且确定一下.

### Help! 我不能看到:
* my logs 默认情况下, 日志为 $STORM_HOME/logs. 检查您是否具有该目录的写入权限. 他们配置在 log4j2/{cluster, worker}.xml 文件中.
*final JVM settings 在 childopts 中添加 -XX+PrintFlagsFinal 命令行选项（请看配置文件）
* final Java system properties 添加 Properties props = System.getProperties(); props.list(System.out); 靠近您构建 topology（拓扑）的地方.

### 我应该使用多少个 Workers?
worker 的数量是由 supervisors 来确定的 -- 每个 supervisor 将监督一些 JVM slots. 您在 topology（拓扑）上设置的事情是它将尝试声明多少个 worker slots.

每台机器每个 topology（拓扑）使用多个 worker 没有很好的理由。

一个 topology（拓扑）运行在三个 8 核心的节点上, 并行度是 24, 每台机器的每个 bolt 将得到 8 个 executor（执行器）, 即每个核心一个. 与运行三个 worker（每个有 8 个指定的 executor）相比，有 24 个 worker（每个分配一个 executor）的运行有 3 个大的优势.

第一，对同一个 worker 的 executor 进行重新分区（shuffles 或 group-bys）的数据不必放入传输缓冲区. 相反, tuple 直接从发送到接收缓冲区存储. 这是一个很大的优势. 相反，如果目标 executor 在不同 worker 的同一台计算机上, 则必须执行 send - > worker transfer - > local socket - > worker recv - > exec recv buffer. 它不经过打网卡，但并不像 executor 在同一个 worker 那么大.

通常情况下，三个具有非常大的 backing cache（后备缓存）的 aggregator（聚合器）比拥有小的 backing caches（后台缓存）的二十四个 aggregators（聚合器）更好，因为这样减少了数据倾斜的影响，并提高了 LRU 效率.

最后，更少的 workers 降低了控制 flow 的难度.
## Topology（拓扑）
### 一个 Trident topology 可以有多个 Streams 吗?
> Trident topology 可以像带条件路径（if-else）的 workflow（工作流）一样工作吗? 例如. 一个 Spout(S1) 连接到 bolt(b0), 其基于进入 tuple（元组）中的某些值将它们引导到 blolt（B1）或 bolt（B2），而不是两者都有.

一个 Trident 的 "each" 操作返回一个 Stream 对象, 你可以在一个变量中存储它. 然后，您可以在同一个 Stream 上运行多个 each 进行 split 拆分, 例如:
```java
Stream s = topology.each(...).groupBy(...).aggregate(...) 
    Stream branch1 = s.each(..., FilterA) 
    Stream branch2 = s.each(..., FilterB) 
```

你可以使用 join, merge 或 multiReduce 来 join streams.

在写入操作时，您不能向 Trident 的 emit（发射）多个输出流 -- 请参阅 [STORM-68](https://issues.apache.org/jira/browse/STORM-68)

### 当我启动 topology 时, 为什么获得一个 NotSerializableException/IllegalStateException 异常?
在 Storm 的生命周期中，在执行 topology 之前，topology 被实例化，然后序列化为字节格式以存储在 ZooKeeper 中. 在此步骤中，如果 topology 中的 Spout 或 Bolt 具有初始化的不可序列化属性，序列化将会失败. 如果需要一个不序列化的字段，请在将 topology 传递给 worker 之后运行的 blot 或 spout 的 prepare 方法中进行初始化.

## Spouts
### coordinator 是什么, 为什么有几个?
trident-spout 实际运行在 storm bolt 之内. trident topology 的 storm-spout 是 MasterBatchCoordinator -- 它协调了 trident batches，无论您使用什么 spout 都是一样的. 当 MBC 为每个 spout-coordinators 分配一个 seed tuple（种子元组）时，batch 就诞生了. spout-coordinator bolts 知道您特定的 spouts 应该如何配合 -- 所以在 kafka 的场景中, 这有助于找出每个 spout 应该从哪个 partition 和 offset 进行 pull 操作.

### What can I store into the spout's metadata record?
You should only store static data, and as little of it as possible, into the metadata record (note: maybe you can store more interesting things; you shouldn't, though)

### 'emitPartitionBatchNew' 函数多久被调用一次?
由于 MBC 是实际的 spout，所以一个 batch 中的所有 tuple 只是它的 tupletree 的成员. 这意味着 storm 的 "max spout pending" 配置有效地定义了并发 batch trident 运行的次数.

