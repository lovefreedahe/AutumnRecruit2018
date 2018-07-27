package com.wangrupeng.thread;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSafeTest {

    public static void main(String[] args) throws InterruptedException {
        final int threadSize = 1000;
        ThreadUnsafeExample example = new ThreadUnsafeExample();
        final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        ExecutorService executorService = Executors.newCachedThreadPool();
        synchronized (example) {
            for (int i = 0; i < threadSize; i++) {
                executorService.execute(() -> {
                    example.add();
                    countDownLatch.countDown();
                });
            }
            countDownLatch.await();
            executorService.shutdown();
            System.out.println(example.get());
        }
        ConcurrentHashMap<Integer, String>
    }

    static class ThreadUnsafeExample {

        private int cnt = 0;

        public void add() {
            synchronized (this) {
                cnt++;
            }
        }

        public int get() {
            return cnt;
        }
    }
}
