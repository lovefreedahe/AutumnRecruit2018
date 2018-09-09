package com.wangrupeng.exam.netease2.q2;

import java.util.Scanner;

/**
 * Created by WangRupeng on 2018/9/8.
 *
 * @author <a href="http://datacoder.top">王汝鹏</a>
 */
public class Main {

    public void process(int n, int k) {
        System.out.print(0 + " ");
        if (n < 3 || n <= k || k <= 1) {
            System.out.println(0);
        }else if (k <= n/2) {
            System.out.println(k - 1);
        } else {
            System.out.println(n - k);
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main m = new Main();
        int t = scanner.nextInt();
        for (int i = 0;i < t;i++) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            m.process(n, k);
        }
    }
}
