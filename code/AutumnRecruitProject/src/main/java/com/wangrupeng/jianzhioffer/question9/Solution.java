package com.wangrupeng.jianzhioffer.question9;

/**
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
 * n<=39
 */
public class Solution {
    //1 1 2 3 5 8
    public int Fibonacci(int n) {
        if(n == 0) {
            return 0;
        }
        if(n == 1) {
            return 1;
        }
        return Fibonacci(n - 1) + Fibonacci(n - 2);

    }

    public int FibonacciEffective(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int tmp1 = 0;
        int tmp2 = 1;
        for (int i = 2;i < n;i++) {
            int tmp3 = tmp2;
            tmp2 = tmp1 + tmp2;
            tmp1 = tmp3;
        }
        return tmp1 + tmp2;
    }

    public int FibonacciArray(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] array = new int[n + 1];
        array[0] = 0;
        array[1] = 1;
        for (int i = 2; i<= n;i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[n];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.Fibonacci(20));
        System.out.println(solution.FibonacciEffective(6));
        System.out.println(solution.FibonacciArray(6));
    }
}
