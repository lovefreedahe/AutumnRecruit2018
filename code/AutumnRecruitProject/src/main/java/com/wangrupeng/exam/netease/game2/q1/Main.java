package com.wangrupeng.exam.netease.game2.q1;

import java.util.Scanner;

public class Main {
    public void process(int salary) {
        double tax = 0;
        double need_tax = salary/100.0 - 50;
        if (need_tax <= 0) {
            tax = 0;
        } else if (need_tax<= 30) {
            tax = need_tax * 3;
        } else if (need_tax <= 120) {
            tax = 30 * 3 + (need_tax - 30) * 10;
        } else if (need_tax <= 250) {
            tax = 30 * 3 + (120 - 30) * 10 + (need_tax - 120) * 20;
        } else if (need_tax <= 350) {
            tax = 30 * 3 + (120 - 30) * 10 + (250 - 120) * 20 + (need_tax - 250) * 25;
        } else if (need_tax <= 550) {
            tax = 30 * 3 + (120 - 30) * 10 + (250 - 120) * 20 + (350 - 250) * 25 + (need_tax - 350)*30;
        } else if (need_tax <= 800) {
            tax = 30 * 3 + (120 - 30) * 10 + (250 - 120) * 20 + (350 - 250) * 25 + (550 - 350)*30 + (need_tax - 550) * 35;
        } else {
            tax = 30 * 3 + (120 - 30) * 10 + (250 - 120) * 20 + (350 - 250) * 25 + (550 - 350)*30 + (800 - 550) * 35 + (need_tax - 800) * 45;
        }
        /*int result = (int)tax;
        if ((tax - (int)tax) * 10 >=5 ) {
            result += 1;
        }*/
        //BigDecimal bigDecimal = new BigDecimal(new Double(tax).toString());
        //DecimalFormat decimalFormat = new DecimalFormat("#");
        System.out.println(Math.round(tax));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main m = new Main();
        int T = scanner.nextInt();
        int salary;
        for (int i = 0;i < T;i++) {
            salary = scanner.nextInt();
            m.process(salary);
        }
    }
}
