package com.wangrupeng.storm.SimpleTopWords.bolt;

import org.apache.storm.topology.base.BaseWindowedBolt;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.windowing.TupleWindow;

import java.util.*;

/**
 * windowBolt，实现窗口操作，并统计指定时间内单位时间间隔内的Top3
 */
public class MyBolt extends BaseWindowedBolt {

    //定义一个HashMap作wordcount用
    HashMap<String, Integer> hashMap = new HashMap<String, Integer>();

    public void execute(TupleWindow inputWindow) {
        //获取窗口内的内容
        List<Tuple> words = inputWindow.get();
        //wordcount
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i).getString(0);
            Integer count = hashMap.get(word);
            if (count == null)
                count = 0;
            count++;
            hashMap.put(word, count);
        }
        //这里将map.entrySet()转换成list
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(hashMap.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            //升序排序
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }

        });
        //输出top3
        System.out.println("Top3:");
        for (int i = 0; i < 3; i++) {
            System.out.println("\t" + list.get(i).getKey() + ":" + list.get(i).getValue());
        }
        System.out.println("--------->");

        /*
         *以下代码用于理解window的某些基础操作
         */
//            List<Tuple> tuples = inputWindow.get();
//            List<Tuple> expired = inputWindow.getExpired();//获取到过期的tuple
//            List<Tuple> tuples = inputWindow.getNew();//获取到和上个窗口相比新加进去的tuple
//            System.out.println("滑动了一下！");
//            System.out.println(tuples.size());
//            System.out.println(expired.size());
//            for (Tuple tuple:tuples){
//
//                System.out.println(tuple.getValue(0));
//        }
    }
}