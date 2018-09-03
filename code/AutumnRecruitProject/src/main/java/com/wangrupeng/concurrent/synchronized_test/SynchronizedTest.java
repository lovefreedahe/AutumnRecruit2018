package com.wangrupeng.concurrent.synchronized_test;

import com.wangrupeng.practice.design_pattern.singleton.ThreadSafeSingleton;

public class SynchronizedTest {
    public final Object obj = new Object();
    public static final String Lock = "lock";
    public static final String Lock2 = "lock2";
    //加载当前对象上
    //同一个对象的同一个方法是同步的
    public synchronized void syncMethod() throws InterruptedException{
        for (int i = 0;i < 5;i++) {
            Thread.sleep(1000);
            System.out.println("syncMethod:" + i);
        }
    }

    //加载当前对象上
    //同一个对象的同步方法和同步this是同步的
    public void syncThis() throws InterruptedException{
        synchronized(this) {
            for (int i = 0;i < 5;i++) {
                Thread.sleep(1000);
                System.out.println("syncThis:" + i);
            }
        }
    }

    //类的静态方法和this.Class是同步的
    public synchronized static void syncStaticMethod() throws InterruptedException{
        for (int i = 0;i < 5;i++) {
            Thread.sleep(1000);
            System.out.println("syncStaticMethod:" + i);
        }
    }
    public void syncClass() throws InterruptedException{
        synchronized(this.getClass()) {
            for (int i = 0;i < 5;i++) {
                Thread.sleep(1000);
                System.out.println("syncClass:" + i);
            }
        }
    }

    //锁普通变量
    public void syncVariable() throws InterruptedException{
        synchronized(obj) {
            for (int i = 0;i < 5;i++) {
                Thread.sleep(1000);
                System.out.println("syncVariable:" + i);
            }
        }
    }

    //锁静态变量
    //该方法与Lock类的其他对象加的锁是同步的
    //与synchronized(Lock.class)也是同步的
    public void syncStaticVariable() throws InterruptedException{
        synchronized(Lock) {
            for (int i = 0;i < 5;i++) {
                Thread.sleep(1000);
                System.out.println("syncStaticVariable:" + i);
            }
        }
    }

    public void syncStaticVariable2() throws InterruptedException {
        synchronized (Lock2) {
            for (int i = 0;i < 5;i++) {
                Thread.sleep(1000);
                System.out.println("syncStaticVariable2:" + i);
            }
        }
    }

    public static void main(String[] args){
        SynchronizedTest synchronizedTest = new SynchronizedTest();
        SynchronizedStaticVariableTest synchronizedStaticVariableTest = new SynchronizedStaticVariableTest();
        SynchronizedTest synchronizedTest1 = new SynchronizedTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SynchronizedTest.syncStaticMethod();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                   synchronizedTest1.syncClass();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}