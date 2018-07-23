package com.wangrupeng.jianzhioffer.question12;

/**
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 */
public class Solution {
    public double Power(double base, int exponent) {
        if (base == 0 || base == 1) {
            return base;
        }

        return 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        double result = solution.Power(1.0, 23);
        System.out.println(result);
    }
}
