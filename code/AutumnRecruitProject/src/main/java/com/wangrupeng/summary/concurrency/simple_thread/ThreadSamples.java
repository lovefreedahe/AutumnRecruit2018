package com.wangrupeng.summary.concurrency.simple_thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class ThreadSample extends Thread {

    @Override
    public void run() {
        System.out.println("Thread example.");
    }
}

class RunnableSample implements Runnable {

    @Override
    public void run() {
        System.out.println("Runnable example.");
    }
}

class CallableSample implements Callable {
    @Override
    public Integer call() {
        System.out.println("Callable example.");
        return 0;
    }
}

public class ThreadSamples {

    public static void main(String[] args) {
        ThreadSample threadSample = new ThreadSample();
        threadSample.start();
        Thread runnableThread = new Thread(new RunnableSample());
        runnableThread.start();
        FutureTask<Integer> futureTask = new FutureTask<>(new CallableSample());
        futureTask.run();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
