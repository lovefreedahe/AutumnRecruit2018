package com.wangrupeng.exam.netease.bigdata.q2;

import java.util.Scanner;

public class Main {
    private int[] range;
    public void process(int n, int[] N) {
        range = null;
        range = new int[n];
        for (int i = 0;i < n;i++) {
            if (i == 0) {
                range[i] = N[0];
            } else {
                range[i] = range[i - 1] + N[i];
            }
            //System.out.print(range[i] + " ");
        }
    }

    public void getRange(int m, int[] M, int n) {
        for (int i = 0;i < m;i++) {
            for (int j = 0;j < n;j++) {
                if (M[i] <= range[j]) {
                    System.out.println(j + 1);
                    break;
                }
            }
        }
    }

    public void getRange2(int m, int n) {
        for (int j = 0;j < n;j++) {
            if (m <= range[j]) {
                System.out.println(j + 1);
                return;
            }
        }
    }

    public void setRange(int[] range) {
        this.range = range;
    }

    public void process2(int i, int value) {
        if (i == 0) {
            range[i] = value;
        } else {
            range[i] = range[i - 1] + value;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            Main ma = new Main();
            int n = scanner.nextInt();
            int[] N = new int[n];
            ma.setRange(N);
            for (int i = 0; i < n; i++) {
                //N[i] = scanner.nextInt();
                ma.process2(i, scanner.nextInt());
            }
            int m = scanner.nextInt();
            int[] M = new int[m];
            for (int i = 0; i < m; i++) {
                //M[i] = scanner.nextInt();
                ma.getRange2(scanner.nextInt(), n);
            }
            //ma.process(n, N);
            //ma.getRange(m, M, n);
        }
    }
}
