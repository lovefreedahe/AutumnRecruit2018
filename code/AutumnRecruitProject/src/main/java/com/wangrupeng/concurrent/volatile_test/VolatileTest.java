package com.wangrupeng.concurrent.volatile_test;

public class VolatileTest implements Runnable{

    private  int index;

    public void plus() {
        index++;
    }

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 10000; i++) {
                index++;
            }
        }
        System.out.println("Index=" + index);
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileTest volatileTest = new VolatileTest();
        for (int i = 0;i < 10;i++) {
            new Thread(volatileTest).start();
        }

        /*Thread.sleep(20000);
        System.out.println(volatileTest.index);*/
    }


}
