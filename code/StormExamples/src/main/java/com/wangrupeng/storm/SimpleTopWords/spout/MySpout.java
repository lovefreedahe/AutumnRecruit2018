package com.wangrupeng.storm.SimpleTopWords.spout;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import java.util.Map;
import java.util.Random;

public class MySpout extends BaseRichSpout {

    String[] words = {"aa","bb","cc","dd","ee","ff","gg"};
    Random random = new Random();
    SpoutOutputCollector collector;
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector=collector;
    }

    public void nextTuple() {
        Utils.sleep(500);
        collector.emit(new Values(words[random.nextInt(words.length)]));
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }
}
