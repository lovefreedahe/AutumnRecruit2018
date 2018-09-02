package com.wangrupeng.summary.concurrency.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeTest {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        reentrantLock.unlock();
    }
}
