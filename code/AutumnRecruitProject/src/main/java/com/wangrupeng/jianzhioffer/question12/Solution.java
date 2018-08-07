package com.wangrupeng.jianzhioffer.question12;

/**
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 */
public class Solution {
    public double Power(double base, int exponent) {
        if (base == 0.0 || base == 1.0) {
            return base;
        }
        boolean negative = false;
        if (exponent < 0) {
            negative = true;
            exponent = -exponent;
        }
        double result = powerUnsignedExponent(base, exponent);
        if (negative) {
            result = 1/result;
        }
        return result;
    }

    private double powerUnsignedExponent(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent == 1) {
            return base;
        }

        double result = powerUnsignedExponent(base, exponent >> 1);
        result *= result;
        if ((exponent & 0x1) == 1) {
            result *= base;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        double result = solution.Power(2, 4);
        System.out.println(result);
    }
}
