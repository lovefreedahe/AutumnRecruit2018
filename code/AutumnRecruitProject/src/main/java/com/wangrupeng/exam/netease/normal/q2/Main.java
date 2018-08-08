package com.wangrupeng.exam.netease.normal.q2;

import java.util.Scanner;

public class Main {

    public void process(int n, int k ) {
        if (n < 1 || n > 100000 || k < 0 || k > n - 1) {
            return;
        }
        /*int x,y;
        int count = 0;
        for (x = 1; x <= n/2 ;x++) {
            for (y = n/2 + 1;y <= n; y++) {
                *//*if (x <= y && x >= k) {
                    count++;
                }
                *//*
                if (x % y >= k) {
                    count++;
                }
            }
        }*/
        System.out.println(k * 2 + (n - 2 * k) * k);
    }
    public static void main(String[] args) {
       /* Scanner scanner = new Scanner(System.in);
        Main m = new Main();
        int n;
        int k;
        while (scanner.hasNext()) {
            n = scanner.nextInt();
            k = scanner.nextInt();
            m.process(n, k);
        }*/
        System.out.println(57 % 7);
       int [] array = { 13,25,22,35,54,57,63};
        for (int i = 0;i < array.length;i++) {
            System.out.println(array[i] % 7);
        }
    }
}
