package com.wangrupeng.storm.SimpleTopWords;

import com.wangrupeng.storm.SimpleTopWords.bolt.MyBolt;
import com.wangrupeng.storm.SimpleTopWords.spout.MySpout;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.topology.base.BaseWindowedBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;
import org.apache.storm.windowing.TupleWindow;

import java.util.*;

/**
 * 通过窗口操作来统计50s内每10s的Top-N
 */
public class WindowTopN {
    /**
     * 产生数据的Spout，随机生成指定word并发出
     */
    public static void main(String[] args) {

        //构建拓扑
        TopologyBuilder topologyBuilder = new TopologyBuilder();
        topologyBuilder.setSpout("spout", new MySpout());
        //指定窗口长度以及滑动间隔
        topologyBuilder.setBolt("bolt", new MyBolt().withWindow(BaseWindowedBolt.Duration.seconds(50), BaseWindowedBolt.Duration.seconds(10))).shuffleGrouping("spout");

        /*
         *以下代码简单理解定义窗口时时间和数量的排列组合
         */
//        topologyBuilder.setBolt("bolt", new MyWindowBolt().withTumblingWindow(BaseWindowedBolt.Count.of(10)))
//              .shuffleGrouping("spout");//这里要注意withTumblingWindow（滑动间隔和窗口长度是一样的）和withWindow的区别，如果忘了点进源码看一下（withWindow是一个tuple滑动一次）
//        topologyBuilder.setBolt("bolt",
////                new new MyWindowBolt().withWindow(new BaseWindowedBolt.Duration(10, TimeUnit.SECONDS)))
//                new MyWindowBolt().withWindow(BaseWindowedBolt.Duration.seconds(50), BaseWindowedBolt.Duration.seconds(10)))//时间的两种定义方式
//                .shuffleGrouping("spout");

        LocalCluster localCluster = new LocalCluster();
        Config config = new Config();
        config.put(Config.TOPOLOGY_MESSAGE_TIMEOUT_SECS, 100);//要保证超时时间大于等于窗口长度+滑动间隔长度

        localCluster.submitTopology("a", config, topologyBuilder.createTopology());


    }
}