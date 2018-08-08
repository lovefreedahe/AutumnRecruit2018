package com.wangrupeng.exam.netease.game.q1;

import java.util.Scanner;

public class Main {

    public String process(String str) {
        String[] strings = str.split(":");
        boolean[] judges = isLeagle(strings);
        StringBuilder stringBuilder = new StringBuilder();
        if (judges[0]) {
            stringBuilder.append(strings[0]);
            stringBuilder.append(':');
        } else {
            //stringBuilder.append("20:");
            /*int value = Integer.valueOf(strings[0]);
            if (value < 30) {
                stringBuilder.append("20:");
            } else {*/
                stringBuilder.append('0');
                stringBuilder.append(strings[0].charAt(1));
                stringBuilder.append(':');
            //}
        }
        if (judges[1]) {
            stringBuilder.append(strings[1]);
            stringBuilder.append(':');
        } else {
            stringBuilder.append('0');
            stringBuilder.append(strings[1].charAt(1));
            stringBuilder.append(':');
        }
        if (judges[2]) {
            stringBuilder.append(strings[2]);
        } else {
            stringBuilder.append('0');
            stringBuilder.append(strings[2].charAt(1));
        }
        return stringBuilder.toString();
    }

    public boolean[] isLeagle(String[] strings) {
        boolean[] booleans = {true, true, true};
        int hour = Integer.valueOf(strings[0]);
        if (hour > 23 || hour < 0) {
            booleans[0] = false;
        }
        int minute = Integer.valueOf(strings[1]);
        if (minute > 59 || minute < 0) {
            booleans[1] = false;
        }
        int second = Integer.valueOf(strings[2]);
        if (second > 59 || second < 0) {
            booleans[2] = false;
        }
        return booleans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        String str;
        Main m = new Main();

        for (int i = 0;i < number;i++) {
            str = scanner.next();
            System.out.println(m.process(str));
        }
    }
}
