package com.wangrupeng.exam.beike.q3;

import java.util.Scanner;

public class Main {
    private int minTotal;
    private int maxTotal;
    public void process(int N, int[] array) {
        int count = 0;
        for (int i = 0;i < N - 1;i++) {
            minTotal = array[i];
            maxTotal = array[i];
            for (int j = i + 1;j < N;j++) {
                count += getResult(array, j);
            }
        }
        System.out.println(count);
    }

    public int getResult(int[] array, int position) {
            if (minTotal > array[position]) {
                minTotal = array[position];
            }
            if (maxTotal < array[position]) {
                maxTotal = array[position];
            }

        return maxTotal - minTotal;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main m = new Main();
        int N = scanner.nextInt();
        int[] array = new int[N];
        for (int i = 0;i < N;i++) {
            array[i] = scanner.nextInt();
        }
        m.process(N, array);
    }
}
