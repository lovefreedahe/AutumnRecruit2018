# ACK机制
JStorm的acker机制，能够保证消息至少被处理一次（at least once）。也就是说，能够保证不丢消息。这里就详细解析一下acker的实现原理。ack 机制是storm整个技术体系中非常闪亮的一个创新点， JStorm很好的继承了这个机制，并对原生storm的ack机制做了一点点代码优化。

# 应用场景
通过Ack机制，spout发送出去的每一条消息，都可以确定是被成功处理或失败处理， 从而可以让开发者采取动作。比如在Meta中，成功被处理，即可更新偏移量，当失败时，重复发送数据。

因此，通过Ack机制，很容易做到保证所有数据均被处理，一条都不漏。

另外需要注意的，当spout触发fail动作时，不会自动重发失败的tuple，需要spout自己重新获取数据，手动重新再发送一次

ack机制即， spout发送的每一条消息，
* 在规定的时间内，spout收到Acker的ack响应，即认为该tuple被后续bolt成功处理
* 在规定的时间内，spout没有收到Acker的ack响应，就出发fail动作，认为该tuple处理失败
* 收到Acker发送的fail响应tuple，也认为失败，触发fail动作

**另外Ack机制还常用于限流：为了避免spout发送数据太快，而bolt处理太慢，常常设置pending数，当spout有等于或超过pending数的tuple没有收到ack或fail响应时，跳过执行nextTuple,从而限制spout发送数据。**

通过conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, pending);设置spout pending数。
<div align="center"><img src="../../../resources/images/storm/acker_1.png"></div>

# Ack机制原理
## 异或
```java
A xor A = 0
A xor B xor B xor A = 0
```

## 判断消息被成功处理
acker的算法非常巧妙，它利用了数学上的异或操作来实现对整个tuple tree的判断。在一个topology中的一条消息形成的tuple tree中，所有的消息，都会有一个MessageId，它内部其实就是一个map：
```java
Map<Long, Long> _anchorsToIds;
```
存储的是anchor和anchor value。而anchor其实就是root_id，它在spout中生成，并且一路透传到所有的bolt中，属于同一个tuple tree中的消息都会有相同的root_id，它可以唯一标识spout发出来的这条消息（以及从下游bolt根据这个tuple衍生发出的消息）。

下面是一个tuple的ack流程：

* spout发送消息时，先生成root_id。
* 对每一个目标bolt task，生成<root_id, random()>，即为这个root_id对应一个随机数值，然后随着消息本身发送到下游bolt中。假设有2个bolt，生成的随机数对分别为：<root_id, r1>, <root_id, r2>。
* spout向acker发送ack_init消息，它的MessageId = <root_id, r1 ^ r2>（即所有task产生的随机数列表的异或值）。
* bolt收到spout或上游bolt发送过来的tuple之后，首先它会向acker发送ack消息，MessageId即为收到的值。同时，如果bolt下游还有bolt，则跟步骤2类似，会对每一个bolt，生成随机数对，root_id相同，但是值变为当前值 ^ 新生成的随机数。以此类推。
* acker收到消息后，会对root_id下所有的值做异或操作，如果算出来的值为0，表示整个tuple tree被成功处理；否则就会一直等待，直到超时，则tuple tree处理失败。
* acker通知spout消息处理成功或失败。

我们以一个稍微复杂一点的topology为例，描述一下它的整个过程。 假设我们的topology结构为： spout -> bolt1/bolt2 -> bolt3 即spout同时向bolt1和bolt2发送消息，它们处理完后，都向bolt3发送消息。bolt3没有后续处理节点。

<div align="center"><img src="../../../resources/images/storm/acker_2.png"></div>

1. spout发射一条消息，生成root_id，由于这个值不变，我们就用root_id来标识。 spout -> bolt1的MessageId = <root_id, 1> spout -> bolt2的MessageId = <root_id, 2> spout -> acker的MessageId = <root_id, 1^2>

2. bolt1收到消息后，生成如下消息： bolt1 -> bolt3的MessageId = <root_id, 3> bolt1 -> acker的MessageId = <root_id, 1^3>

3. 同样，bolt2收到消息后，生成如下消息： bolt2 -> bolt3的MessageId = <root_id, 4> bolt2 -> acker的MessageId = <root_id, 2^4>

4. bolt3收到消息后，生成如下消息： bolt3 -> acker的MessageId = <root_id, 3> bolt3 -> acker的MessageId = <root_id, 4>

5. acker中总共收到以下消息： <root_id, 1^2> <root_id, 1^3> <root_id, 2^4> <root_id, 3> <root_id, 4> 所有的值进行异或之后，即为1^2^1^3^2^4^3^4 = 0。



# 如何使用
* 在spout发送数据的时候带上msgid
    ```java
    public List<Integer> emit(List<Object> tuple, Object messageId) {
        return emit(Utils.DEFAULT_STREAM_ID, tuple, messageId);
    }
    ```
* 设置acker数至少大于0
    ```java
    config.setNumAckers(1);
    ```
* 在bolt中完成处理tuple时，执行OutputCollector.ack(tuple),当失败处理时，执行OutputCollector.fail(tuple);推荐使用IBasicBolt自动封装了OutputCollector.ack(tuple),处理失败时，跑出FailedException，则自动执行OutputCollector.fail(tuple)

# 如何关闭
* spout不带msgid
* acker配置中数量设置为0
* 在outputCollector.emit(tuple)时，使用unanchor的方式

# 参考
* [JStorm高级应用](http://www.jstorm.io/ProgrammingGuide_cn/AdvancedUsage/Theory/Acker.html)
* [jstorm进阶-ack机制及KafkaSpout](http://www.voidcn.com/article/p-yxdeotkc-boz.html)