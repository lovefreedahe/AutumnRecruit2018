package com.wangrupeng.exam.jd.q2;

import java.util.Scanner;

public class Main {
    public void process(int[][] array, int n) {
        int count = 0;
        for (int i = 0;i < n;i++) {
            for (int j = 0;j < n;j++) {
                if (j == i) {
                    continue;
                }
                if (isLower(array[i], array[j])) {
                    count++;
                    break;
                }
            }
        }
        System.out.println(count);
    }

    private boolean isLower(int[] a, int[] b) {
        if (a[0] < b[0] && a[1] < b[1] && a[2] < b[2]) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] array = new int[n][];
        for(int i = 0;i < n;i++) {
            array[i] = new int[3];
            for (int j = 0;j < 3;j++) {
                array[i][j] = scanner.nextInt();
            }
        }
        m.process(array, n);
    }
}
