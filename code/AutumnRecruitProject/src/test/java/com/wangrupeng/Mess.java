package com.wangrupeng;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;

public class Mess {
    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayBlockingQueue<>(100);
        queue = new LinkedBlockingQueue<>();
        queue = new PriorityQueue<>();
        queue = new ArrayDeque<>();
        queue = new LinkedBlockingDeque<>();
        queue = new DelayQueue();
        queue = new SynchronousQueue<>();
        queue = new ConcurrentLinkedQueue<>();

        ExecutorService service = Executors.newSingleThreadExecutor();
        service = Executors.newFixedThreadPool(10);
        service = Executors.newCachedThreadPool();
        LinkedList<Integer> linkedList = new LinkedList<>();

        int a = 1;
        a = a + 1;
        System.out.println(a);
        switch (a) {
            case 6:
                break;
        }

        if (3 * 0.1 == 0.3) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }

        BigDecimal bigDecimal = BigDecimal.valueOf(0.1d);
        bigDecimal = bigDecimal.multiply(new BigDecimal(3.0d));
        System.out.println(bigDecimal.compareTo(BigDecimal.valueOf(0.3d)));
        List<Integer> list = new ArrayList<>(10);
        for (int i = 0;i < 10;i++) {
            list.add(i);
        }

        List<Integer> list1 = new ArrayList<>();
        list1.addAll(0, list);
        for (int i : list) {
            System.out.print(i + " ");
        }
    }
}
