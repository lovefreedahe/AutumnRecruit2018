package com.wangrupeng;

import java.util.concurrent.Executors;

public class ThreadTest extends Thread {

    @Override
    public void run() {
        while (!isInterrupted()) {
            System.out.println("Thread is running");
        }
        System.out.println("Thread is stopped");
    }

    public static void main(String[] args) throws InterruptedException{
        ThreadTest threadTest = new ThreadTest();
        threadTest.start();
        Thread.sleep(1000);
        threadTest.stop();
        Executors.newFixedThreadPool(10);
    }
}
