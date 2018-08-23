package com.wangrupeng.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class ListThreadSafeTest {
    private int NUM = 10000;
    private int THREAD_NUM = 16;

    public void test() throws Exception{
        List<Integer> list = new ArrayList<>(NUM);
        List<Integer> list1 = Collections.synchronizedList(list);
        List<Integer> list2 = new CopyOnWriteArrayList(list);
        List list3 = Arrays.asList(list);

        ExecutorService service = Executors.newFixedThreadPool(THREAD_NUM);
        int addTime = 0;
        int getTime = 0;
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);
        for (int i = 0;i < THREAD_NUM;i++) {
            addTime += service.submit(new AddTask(list2, countDownLatch)).get();
        }
        System.out.println("Collection list add time used " + addTime);
        for (int i = 0;i < THREAD_NUM;i++) {
            getTime += service.submit(new GetTask(list2, countDownLatch)).get();
        }
        System.out.println("Collection list get time used " + getTime);
    }
    public static void main(String[] args) throws Exception{
        ListThreadSafeTest threadSafeTest = new ListThreadSafeTest();
        threadSafeTest.test();
    }

    class AddTask implements Callable<Integer> {
        List<Integer> list;
        CountDownLatch countDownLatch;

        AddTask(List<Integer> list, CountDownLatch countDownLatch) {
            this.list = list;
            this.countDownLatch = countDownLatch;
        }
        @Override
        public Integer call() {
            long start = System.currentTimeMillis();
            for (int i = 0;i < NUM;i++) {
                list.add(i);
            }
            countDownLatch.countDown();
            return (int)(System.currentTimeMillis() - start);
        }
    }

    class GetTask implements Callable<Integer> {
        List<Integer> list;
        CountDownLatch countDownLatch;
        GetTask(List<Integer> list, CountDownLatch countDownLatch) {
            this.list = list;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public Integer call() {
            long start = System.currentTimeMillis();
            for (int i = 0;i < NUM;i++) {
                list.get(i);
            }
            return (int)(System.currentTimeMillis() - start);
        }
    }
}
