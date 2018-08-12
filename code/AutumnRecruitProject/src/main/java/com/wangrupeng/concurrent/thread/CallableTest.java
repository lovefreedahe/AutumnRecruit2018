package com.wangrupeng.concurrent.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by WangRupeng on 2018/8/12.
 *
 * @author <a href="http://datacoder.top">王汝鹏</a>
 */
public class CallableTest implements Callable<Integer> {

    @Override
    public Integer call() {
        return 1;
    }
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CallableTest callableTest = new CallableTest();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callableTest);
        Thread thread = new Thread(futureTask);
        thread.start();
        Integer i = futureTask.get();
        System.out.println(i);
    }
}
