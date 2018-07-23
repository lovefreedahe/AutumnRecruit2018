package com.wangrupeng.test;

/**
 * Created by WangRupeng on 2018/7/22.
 *
 * @author <a href="http://datacoder.top">王汝鹏</a>
 */
public class TestThread {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("foo");
            }
        };
        Thread thread = new Thread(runnable);
        thread.run();
        System.out.println("bar");
    }

}
