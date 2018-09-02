package com.wangrupeng.exam.tencent.moni;

import java.util.Scanner;

public class Main {
    public void process(int K, int A, int X, int B, int Y) {
        int sum = 0;
        for (int i = 1;i <= K/A && i <= X ;i++) {
            int test = i * A + (K - i * A)/B * B;
            if (test != K) {
                continue;
            }
            if ((K - i * A)/B > Y) {
                continue;
            }
            sum += C((K - i*A)/B , Y) * C(i, X);
        }
        System.out.println(sum/1000000007);
    }

    //应用组合数的互补率简化计算量
    public int C(int up,int below){
        int helf=below/2;
        if(up>helf) {
            up=below-up;

        }
        int denominator=A(up,up);//A(6,6)就是求6*5*4*3*2*1,也就是求6的阶乘
        //分子
        int numerator=A(up,below);//分子的排列数
        return numerator/denominator;

    }

    //求排列数
    public int A(int up,int bellow) {
        int result=1;
        for(int i=up;i>0;i--) {
            result*=bellow;
            bellow--;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main m = new Main();
        int K = scanner.nextInt();
        int A = scanner.nextInt();
        int X = scanner.nextInt();
        int B = scanner.nextInt();
        int Y = scanner.nextInt();
        m.process(K, A, X, B, Y);
    }
}
