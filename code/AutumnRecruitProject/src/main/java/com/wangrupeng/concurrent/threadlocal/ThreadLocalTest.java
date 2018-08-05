package com.wangrupeng.concurrent.threadlocal;

/**
 * Created by WangRupeng on 2018/8/5.
 *
 * @author <a href="http://datacoder.top">王汝鹏</a>
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                threadLocal.set(100);
                try {
                    Thread.sleep(1000);
                    System.out.println(threadLocal.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                threadLocal.set(200);
            }
        };
        thread1.start();
        thread2.start();
    }

}
