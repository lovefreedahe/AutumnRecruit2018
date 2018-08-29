package com.wangrupeng.concurrent.synchronized_test;

public class SynchronizedStaticVariableTest {
    private static final String Lock = "lock2";
    private SynchronizedTest synchronizedTest = new SynchronizedTest();
    public void syncStaticVariable() throws InterruptedException{
        synchronized (SynchronizedTest.Lock) {
            for (int i = 0;i < 5;i++) {
                Thread.sleep(1000);
                System.out.println(SynchronizedTest.Lock + ":" + i);
            }
        }
    }

    public void syncClassObject() throws InterruptedException {
        for (int i = 0;i < 5;i++) {
            Thread.sleep(1000);
            System.out.println("syncClassObject:" + i);
        }
    }
}
