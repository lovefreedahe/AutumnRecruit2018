package com.wangrupeng.thread.cas_test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CounterClient implements Runnable {
    private Counter counter;
    private int num;

    public CounterClient(Counter c, int num){
        this.counter = c;
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 0;i < num; ++i) {
            counter.increment();
        }
    }

    public static void main(String[] args) {
        int NUM_OF_THREADS = 1000;
        int NUM_OF_INCREMENT = 100000;
        ExecutorService service = Executors.newFixedThreadPool(NUM_OF_THREADS);
        Counter counter = new StupicCounter();

    }
}
