package com.wangrupeng.exam.xiecheng.q1;


import java.util.Scanner;

public class Main {
    public int Numberof1(long n) {
        int count = 0;
        while (n != 0) {
            ++count;
            n = (n - 1) & n;
        }
        return count;
    }

    public static void main(String[] args) {
        Main m = new Main();
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        System.out.println(m.Numberof1(n));

    }
}
