package com.wangrupeng.test;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentSkipListSet;

public class TopK {
    public int[] topK(int[] array, int k) {
        ConcurrentSkipListSet<Integer> concurrentSkipListSet = new ConcurrentSkipListSet<Integer>();
        for (int i : array) {
            if (concurrentSkipListSet.size() < k) {
                concurrentSkipListSet.add(i);
            } else {
                if (i > concurrentSkipListSet.first()) {
                    concurrentSkipListSet.pollFirst();
                    concurrentSkipListSet.add(i);
                }
            }
        }
        int[] result = new int[concurrentSkipListSet.size()];
        Iterator iterator = concurrentSkipListSet.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            result[i++] = (int)iterator.next();
        }
        return result;
    }




    public static void main(String[] args) {
        TopK topK = new TopK();
        int[] array = {1, 3, 5, 7, 9, 2, 4, 6, 8, 0};
        array = topK.topK(array, 5);
        for (int i:array) {
            System.out.println(i + " ");
        }
    }
}
