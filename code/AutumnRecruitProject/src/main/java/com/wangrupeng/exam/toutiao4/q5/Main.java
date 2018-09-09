package com.wangrupeng.exam.toutiao4.q5;

import java.util.Scanner;

/**
 * Created by WangRupeng on 2018/9/9.
 *
 * @author <a href="http://datacoder.top">王汝鹏</a>
 */
public class Main {
    public void process(int N, int M, int[] array) {
        int storeArray[][] = new int[N][];
        for (int i = 0;i < N;i++) {
            storeArray[i] = new int[N];
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    storeArray[i][j] = 0;
                } else {
                    storeArray[i][j] = -1;
                }
            }
        }
        for (int i = 0; i < M * 2; ) {
            storeArray[array[i++]-1][array[i++]-1] = 0;
        }
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (storeArray[i][j] < 0 && storeArray[i][k] + storeArray[k][j] == 0) {
                        storeArray[i][j] = 0;
                    }
                }
            }
        }
        int target = 0;
        for (int j = 0; j < N; j++) {
            int i;
            for (i = 0; i < N; i++) {
                if (storeArray[i][j] < 0) {
                    break;
                }
            }
            if (i == N) {
                target++;
            }
        }
        System.out.println(target);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main m = new Main();
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int[] array = new int[M * 2];
        for (int i = 0;i < M * 2;i++) {
            array[i] = scanner.nextInt();
        }
        m.process(N, M, array);
    }
}
