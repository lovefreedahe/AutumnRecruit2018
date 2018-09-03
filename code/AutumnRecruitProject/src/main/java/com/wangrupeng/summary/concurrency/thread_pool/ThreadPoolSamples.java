package com.wangrupeng.summary.concurrency.thread_pool;

import java.util.concurrent.*;

public class ThreadPoolSamples {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MINUTES, new LinkedBlockingDeque<Runnable>());
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService = Executors.newFixedThreadPool(10);
        executorService = Executors.newSingleThreadExecutor();
        executorService = Executors.newScheduledThreadPool(10);
    }
}
