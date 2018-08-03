package com.wangrupeng.thread.cas_test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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

    public static void main(String[] args) throws Exception{
        int NUM_OF_THREADS = 100;
        int NUM_OF_INCREMENT = 1000000;
        ExecutorService service = Executors.newFixedThreadPool(NUM_OF_THREADS);
        Counter counter = new StupicCounter();
        //Counter counter = new SynCounter(); //38074ms
        //Counter counter = new LockCounter(); //3991
        //Counter counter = new AtomicCounter(); //1989
        //Counter counter = new CASCounter(); //7881
        long start = System.currentTimeMillis();
        for (int i = 0;i < NUM_OF_THREADS;i++) {
            service.submit(new CounterClient(counter, NUM_OF_INCREMENT));
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("Count result is " + counter.getCounter());
        System.out.println(System.currentTimeMillis() - start);
    }
}
