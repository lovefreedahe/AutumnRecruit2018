package com.wangrupeng.exam.pinduoduo.real.q1;

import java.util.Scanner;

public class Main {

    public void process(char[] chars) {
        if ((chars.length + 4) %4 != 0) {
            return;
        }
        int length = (chars.length + 4) / 4;
        char[][] rectChar = new char[length][length];
        /*for (int i = 0;i < length;++i) {
            rectChar[0][i] = chars[i];
        }*/
        for (int i = 0;i < chars.length;) {
            if (i < length && i >= 0) {
                System.out.print(chars[i]);
                rectChar[0][i] = chars[i++];
            }
            if (i < length * 2 - 2 && i >= length) {
                for (int j = 1; j < length; j++) {
                    rectChar[j][length - 1] = chars[i++];
                }
            }
            if (i < length * 3 - 3 && i >= length * 2 - 1) {
                for (int j = length - 2; j >= 0; j--) {
                    rectChar[length - 1][j] = chars[i++];
                }
            }

            for (int k = 1;i < length;i++) {
                for (int j = 0;j < length;j++) {
                    System.out.print(rectChar[k][j]);
                }
                System.out.println();
            }
            for (int j = length - 2; j > 0; j--) {
                rectChar[j][0] = chars[i++];
            }
        }


    }

    public void process2(char[] chars) {
        if ((chars.length + 4) %4 != 0) {
            return;
        }
        int charLen = chars.length;
        int length = (charLen + 4) / 4;
        for (int i = 0;i < length;i++) {
            if (i == 0) {
                for (int j = 0;j < length;j++) {
                    System.out.print(chars[j]);
                }
                System.out.println();
            } else if (i > 0 && i < length - 1) {
                System.out.print(chars[charLen - i]);
                for (int j = 1;j < length - 1;j++) {
                    System.out.print(' ');
                }
                System.out.print(chars[length + i - 1]);
                System.out.println();
            } else {
                int k = charLen - length + 1;
                for (int j = 0;j < length ;j++) {
                    System.out.print(chars[k--]);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        char[] chars = str.toCharArray();
        Main m = new Main();
        m.process2(chars);
    }
}
