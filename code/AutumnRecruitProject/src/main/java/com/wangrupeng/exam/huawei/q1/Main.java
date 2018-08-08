package com.wangrupeng.exam.huawei.q1;

import java.util.Scanner;

public class Main {
    public void process(String str) {
        int length = str.length();
        //char[] chars = str.toCharArray();
        for (int i = 0;i < length;i++) {
            char temp = str.charAt(i);
            if (temp >= 65 && temp <= 90) {
                temp += 32;
                //chars[i] = temp;
            } else if(temp >= 97 && temp <= 122) {
                temp -= 32;
                //chars[i] = temp;
            }
            System.out.print(temp);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main m = new Main();
        String str = scanner.nextLine();
        m.process(str);
    }
}
