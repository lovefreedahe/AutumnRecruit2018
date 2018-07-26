# KafkaSpout
## 代码分析
```java
    @Override
    public void nextTuple() {
        List<PartitionManager> managers = _coordinator.getMyManagedPartitions();
        for (int i = 0; i < managers.size(); i++) {

            try {
                // in case the number of managers decreased
                _currPartitionIndex = _currPartitionIndex % managers.size();
                EmitState state = managers.get(_currPartitionIndex).next(_collector);
                if (state != EmitState.EMITTED_MORE_LEFT) {
                    _currPartitionIndex = (_currPartitionIndex + 1) % managers.size();
                }
                if (state != EmitState.NO_EMITTED) {
                    break;
                }
            } catch (FailedFetchException e) {
                LOG.warn("Fetch failed", e);
                _coordinator.refresh();
            }
        }

        long now = System.currentTimeMillis();
        if ((now - _lastUpdateMs) > _spoutConfig.stateUpdateIntervalMs) {
            commit();
        }
    }
```
EmitState state = managers.get(_currPartitionIndex).next(_collector);
```java
public EmitState next(SpoutOutputCollector collector) {
        if (_waitingToEmit.isEmpty()) {
            fill();
        }
        while (true) {
            MessageAndRealOffset toEmit = _waitingToEmit.pollFirst();
            if (toEmit == null) {
                return EmitState.NO_EMITTED;
            }
            Iterable<List<Object>> tups = KafkaUtils.generateTuples(_spoutConfig, toEmit.msg);
            if ((tups != null) && tups.iterator().hasNext()) {
                for (List<Object> tup : tups) {
                    collector.emit(tup, new KafkaMessageId(_partition, toEmit.offset));
                }
                break;
            } else {
                ack(toEmit.offset);
            }
        }
        if (!_waitingToEmit.isEmpty()) {
            return EmitState.EMITTED_MORE_LEFT;
        } else {
            return EmitState.EMITTED_END;
        }
    }
```
collector.emit(tup, new KafkaMessageId(_partition, toEmit.offset));emit的时候指定了messageId，而这个KafkaMessageId是一个静态内部类，包括分区和偏移量2个属性
```java
static class KafkaMessageId {
        public Partition partition;
        public long offset;

        public KafkaMessageId(Partition partition, long offset) {
            this.partition = partition;
            this.offset = offset;
        }
    }
```


# bolt处理
一般我们写bolt的时候有两种方式，一种使用IRichBolt接口或者它的抽象实现类BaseRichBolt，一种使用IBasicBolt或者它的抽象实现类BaseBasicBolt，这2种是有区别的，主要在于影响ack机制
## IRichBolt
需要实现的接口如下：
```java
void execute(Tuple input);
```
要操作的类为OutputCollector
使用OutputCollector来emit tuple给下个bolt的时候必须要用anchored的方式，接口如下：
```java
    /**
     * Emits a new tuple to the default stream anchored on a single tuple. The
     * emitted values must be immutable.
     *
     * @param anchor the tuple to anchor to
     * @param tuple the new output tuple from this bolt
     * @return the list of task ids that this new tuple was sent to
     */
    public List<Integer> emit(Tuple anchor, List<Object> tuple) {
        return emit(Utils.DEFAULT_STREAM_ID, anchor, tuple);

    /**
     * Emits a new tuple to the default stream anchored on a group of input
     * tuples. The emitted values must be immutable.
     *
     * @param anchors the tuples to anchor to
     * @param tuple the new output tuple from this bolt
     * @return the list of task ids that this new tuple was sent to
     */
    public List<Integer> emit(Collection<Tuple> anchors, List<Object> tuple) {
        return emit(Utils.DEFAULT_STREAM_ID, anchors, tuple);
    }
```
所谓的anchor即为Bolt的execute方法里面的tuple，也即上游发给你的tuple
注意不能使用unanchored 的方式，说明如下：
```java
    /**
     * Emits a new unanchored tuple to the default stream. Beacuse it's
     * unanchored, if a failure happens downstream, this new tuple won't affect
     * whether any spout tuples are considered failed or not. The emitted values
     * must be immutable.
     *
     * @param tuple the new output tuple from this bolt
     * @return the list of task ids that this new tuple was sent to
     */
    public List<Integer> emit(List<Object> tuple) {
        return emit(Utils.DEFAULT_STREAM_ID, tuple);
    }
```
同时在emit后要手动执行collector.ack(tuple);方法

## 使用IbasicBolt
```java
    /**
     * Process the input tuple and optionally emit new tuples based on the input tuple.
     * 
     * All acking is managed for you. Throw a FailedException if you want to fail the tuple.
     */
    void execute(Tuple input, BasicOutputCollector collector);
```
这个execute方法和上述不一样了，他给我们注入了BasicOutputCollector类，我们操作它即可，其实这个类里面有一个上述OutputCollector out属性，并且，自动注入了inputTuple，使用它来emit tuple即可，暴露的emit的方法只有2个：
```java
    public List<Integer> emit(String streamId, List<Object> tuple) {
        return out.emit(streamId, inputTuple, tuple);
    }

    public List<Integer> emit(List<Object> tuple) {
        return emit(Utils.DEFAULT_STREAM_ID, tuple);
    }
```
它实际调用的是OutputCollector的emit方法，并且自动帮我们使用anchor的方式，这里用到了我们熟悉的设计模式中的**代理模式**
大家可能有注意到了，这里并没有显示的调用collector.ack(tuple);方法，这里猜猜也会知道，应该是用到了模板模式，在调用该方法的调用者那里，调用了execute方法后，调用ack方法，查下代码，果然没错，在BasicBoltExecutor类里面，方法如下：
```java
    public void execute(Tuple input) {
        _collector.setContext(input);
        try {
            _bolt.execute(input, _collector);
            _collector.getOutputter().ack(input);
        } catch (FailedException e) {
            if (e instanceof ReportedFailedException) {
                _collector.reportError(e);
            }
            _collector.getOutputter().fail(input);
        }
    }
```
仔细看看，发现它还帮我们处理了异常，只要我们抛出FailedException，它就会自动执行fail方法

# 容错
