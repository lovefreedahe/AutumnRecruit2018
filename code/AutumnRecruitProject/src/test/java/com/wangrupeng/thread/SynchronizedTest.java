package com.wangrupeng.thread;

public class SynchronizedTest {
    private int count = 0;
    public void test() {
        for (int i= 0;i < 100000;i++) {
            synchronized (this) {
                count++;
            }
        }
    }

    public void print() {
        /*for (int i = 0;i < 1000;i++) {
            System.out.println(count);
        }*/
        System.out.println(count);
    }

    private static class StaticInnerClass {
        private int t = 0;
        public void get() {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SynchronizedTest test = new SynchronizedTest();
        Integer i = new Integer(2);
        i.hashCode();
        String str = "";
        str.hashCode();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                test.test();
                test.print();
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.test();
                test.print();
            }
        });
        thread.start();
        thread1.start();
    }
}
