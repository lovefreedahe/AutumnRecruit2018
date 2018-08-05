package com.wangrupeng.concurrent.threadlocal;

/**
 * Created by WangRupeng on 2018/8/5.
 *
 * @author <a href="http://datacoder.top">王汝鹏</a>
 */
public class ThreadLocalTest {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set(100);
        System.out.println(threadLocal.get());
    }

}
