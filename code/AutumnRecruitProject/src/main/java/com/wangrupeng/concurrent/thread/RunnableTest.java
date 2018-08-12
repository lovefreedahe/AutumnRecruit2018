package com.wangrupeng.concurrent.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by WangRupeng on 2018/8/12.
 *
 * @author <a href="http://datacoder.top">王汝鹏</a>
 */
public class RunnableTest implements Runnable{

    @Override
    public void run() {
        System.out.println(123);
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Executors.newScheduledThreadPool(10);
        for (int i = 0;i < 5;i++) {
            executorService.execute(new RunnableTest());
        }
        executorService.shutdown();
    }
}
