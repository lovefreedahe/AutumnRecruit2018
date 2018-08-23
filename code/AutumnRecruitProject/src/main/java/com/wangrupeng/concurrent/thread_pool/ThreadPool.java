package com.wangrupeng.concurrent.thread_pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by WangRupeng on 2018/8/12.
 *
 * @author <a href="http://datacoder.top">王汝鹏</a>
 */
public class ThreadPool {
    private BlockingQueue<Runnable> blockingQueue;
    private List<PoolThread> threadList = new ArrayList<>();
    private boolean isStopped = false;
    public ThreadPool(int number) {
        blockingQueue = new ArrayBlockingQueue<>(number);
        for (int i = 0;i < number;i++) {
            threadList.add(new PoolThread(blockingQueue));
        }
        threadList.forEach(poolThread -> poolThread.start());
    }

    public synchronized void execute(Runnable runnable) {
        if (this.isStopped) {
            throw new IllegalStateException("ThreadPool is stopped!");
        }
        try {
            this.blockingQueue.put(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void stop() {
        this.isStopped = true;
        threadList.forEach(poolThread -> poolThread.stopThread());
    }

}
