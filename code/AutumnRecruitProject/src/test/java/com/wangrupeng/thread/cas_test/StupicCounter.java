package com.wangrupeng.thread.cas_test;

public class StupicCounter implements Counter {
    private long count = 0;
    @Override
    public void increment() {
        count++;
    }

    @Override
    public long getCounter() {
        return count;
    }
}
