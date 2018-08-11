package com.wangrupeng.exam.netease.bigdata.q1;

import java.util.Scanner;

public class Main {
    public void process(int n, int k , int[] a) {
        
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main m = new Main();
        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            int[] a = new int[n];
            //System.out.println(n);
            //System.out.println(k);
            for (int i = 0;i < n;i++) {
                a[i] = scanner.nextInt();
                //System.out.print(a[i] + " ");
            }
            m.process(n, k, a);
        }
    }
}
