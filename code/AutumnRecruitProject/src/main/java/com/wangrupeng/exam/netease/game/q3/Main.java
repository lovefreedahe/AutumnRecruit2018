package com.wangrupeng.exam.netease.game.q3;

import java.util.Scanner;

public class Main {

    public void process(String[] strings, String word, int m, int n) {
        int count = 0;
        for (int i = 0;i < m;i++) {
            for (int j = 0;j < n;j++) {
                if (strings[i].charAt(j) == word.charAt(0)) {
                    if (right(strings[i], word, n, j)) {
                        count++;
                    }
                    if (rightDown(strings, word, m, n, i, j)){
                        count++;
                    }
                    if (down(strings, word, m, i, j)) {
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }

    private boolean right(String row, String word, int n, int j) {
        if (j + word.length() <= n) {
            int count = 0;
            for (char c:word.toCharArray()) {
                if (c == row.charAt(j)) {
                    j++;
                    count++;
                }
            }
            if (count == word.length()) {
                return true;
            }
        }
        return false;
    }

    private boolean rightDown(String[] strings, String word, int m, int n, int i, int j) {
        if (i + word.length() <= m && j + word.length() <= n) {
            int count = 0;
            for (char c: word.toCharArray()) {
                if (c == strings[i].charAt(j)) {
                    i++;
                    j++;
                    count++;
                }
            }
            if (count == word.length()) {
                return true;
            }
        }
        return false;
    }

    private boolean down(String[] strings, String word, int m, int i, int j) {
        if (i + word.length() <= m) {
            int count = 0;
            for (char c : word.toCharArray()) {
                if (c == strings[i].charAt(j)) {
                    i++;
                    count++;
                }
            }
            if (count == word.length()) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Main ma = new Main();
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int i = 0;i < T;i++) {
            int m = scanner.nextInt();
            String[] strings = new String[m];
            int n = scanner.nextInt();
            for (int j = 0;j < m;j++) {
                strings[j] = scanner.next();
            }
            String word = scanner.next();
            ma.process(strings,word, m, n);
        }

    }
}
