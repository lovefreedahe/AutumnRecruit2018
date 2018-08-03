package com.wangrupeng.thread.cas_test;

public class SynCounter implements Counter {
    private long counter = 0;
    @Override
    public synchronized void increment() {
        counter++;
    }

    @Override
    public long getCounter() {
        return counter;
    }
}
