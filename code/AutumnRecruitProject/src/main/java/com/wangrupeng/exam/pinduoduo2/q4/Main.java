package com.wangrupeng.exam.pinduoduo2.q4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public void process(char[][] chars, int N, int L, HashMap<String, Integer> map) {
        char[][] chars1 = new char[L][];

        for (int i = 0;i < L;i++) {
            chars1[i] = new char[N];
            for (int j = 0;j < N;j++) {
                chars1[i][j] = chars[j][i];
            }
            Arrays.sort(chars1[i]);
            for (int j = 0;j < N;j++) {
                System.out.print(chars1[i][j] + " ");
            }
            System.out.println();
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0;i < N;i++) {
            for (int j = 0;j < L;j++) {
                builder.append(chars1[j][i]);
            }
            if (!map.containsKey(builder.toString())) {
                System.out.println(builder.toString());
                break;
            } else {
                builder = new StringBuilder();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main m = new Main();
        int N = scanner.nextInt();
        int L = scanner.nextInt();
        String[] words = new String[N];
        char[][] chars = new char[N][];
        HashMap<String, Integer> map = new HashMap<>(N);
        for (int i = 0;i < N;i++) {
            words[i] = scanner.next();
            chars[i] = words[i].toCharArray();
            map.put(words[i], 0);
        }
        m.process(chars, N, L, map);
    }
}
