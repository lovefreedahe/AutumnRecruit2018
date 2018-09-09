package com.wangrupeng.test;

public class Count {
    public static void main(String[] args) {
        int count = 0;
        for (int i = 1;i <= 1000;i++) {
            if (i % 2 == 0 || i % 3 == 0 || i % 5 == 0) {
                count++;
            }
        }
        System.out.println(1000 - count);
    }
}
