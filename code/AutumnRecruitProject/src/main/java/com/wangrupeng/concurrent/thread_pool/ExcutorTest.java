package com.wangrupeng.concurrent.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by WangRupeng on 2018/8/12.
 *
 * @author <a href="http://datacoder.top">王汝鹏</a>
 */
public class ExcutorTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Executors.newFixedThreadPool(100);
        Executors.newSingleThreadExecutor();
        Executors.newScheduledThreadPool(10);
    }

}
