package com.wangrupeng.effectivejava.avoid_5th;

public class AutoBoxing {
    public static void main(String[] args) {
        //DO NOT DO THIS
        //Long sum = 0L;
        long sum = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        System.out.println(sum);
    }
}
