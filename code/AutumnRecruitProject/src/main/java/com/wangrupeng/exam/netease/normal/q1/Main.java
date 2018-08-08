package com.wangrupeng.exam.netease.normal.q1;

import java.util.Scanner;

public class Main {
    public void process(int N, String direction) {
        int countL = 0;
        int countR = 0;
        int rotate = 0;
        char[] directions = {'N', 'E', 'S', 'W'};
        for (int i = 0;i < N;i++) {
            if (direction.charAt(i) == 'L') {
                countL++;
            } else {
                countR++;
            }
        }
        int temp = (countR - countL) % 4;
        if (temp < 0) {
            temp += 4;
        }
        System.out.println(directions[temp]);

        /*if (countL == countR) {
            rotate = 0;
        } else if (countL < countR) {
            rotate = (countR - countL) % 4;
        } else {
            rotate = (countR - countL) %4 + 4;
        }
        System.out.println(directions[rotate]);*/

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main m = new Main();
        int N;
        String direction;
        while (scanner.hasNext()) {
            N = scanner.nextInt();
            direction = scanner.next();
            m.process(N, direction);
        }
    }
}
