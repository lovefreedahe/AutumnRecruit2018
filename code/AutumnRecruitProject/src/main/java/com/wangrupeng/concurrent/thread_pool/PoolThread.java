package com.wangrupeng.concurrent.thread_pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

/**
 * Created by WangRupeng on 2018/8/12.
 *
 * @author <a href="http://datacoder.top">王汝鹏</a>
 */
public class PoolThread extends Thread {
    private BlockingQueue<Runnable>  blockingQueue = null;
    private boolean running = false;
    public PoolThread(BlockingQueue<Runnable> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (isRunning()) {
            try {
                Runnable runnable = blockingQueue.take();
                runnable.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void stopThread() {
        running = false;
        this.interrupt();
    }

    public synchronized boolean isRunning() {
        return  running;
    }

}
