package com.wangrupeng;

public class IncreseTest {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (double i = 0.0;i < 1000000000d;i++) {

        }

        System.out.println("i++ time used " + (System.currentTimeMillis() - start));
    }
}
