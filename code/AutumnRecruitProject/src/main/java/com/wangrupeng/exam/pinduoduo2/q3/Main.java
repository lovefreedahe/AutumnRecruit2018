package com.wangrupeng.exam.pinduoduo2.q3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        System.out.println(process(a,b));
    }

    private static String process(int a, int b) {
        List<Integer> list = new ArrayList<>();
        while (a != 0) {
            a = a % b;
            int it = list.indexOf(a);
            if (it != -1 && it != list.size() - 1) {
                return it + " " + (list.size() - 1 - it);
            }
            list.add(a);
            a *= 10;
        }
        return list.size() - 1 + " " + 0;
    }
}