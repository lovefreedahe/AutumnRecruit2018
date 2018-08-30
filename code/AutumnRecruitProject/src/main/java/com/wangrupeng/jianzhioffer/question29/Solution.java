package com.wangrupeng.jianzhioffer.question29;

public class Solution {
    public int GetUglyNumber_Solution(int index) {
        if (index == 0) {
            return 0;
        }
        int[] uglyNumbers = new int[index];
        uglyNumbers[0] = 1;
        int nextUgly = 1;
        int multiply2 = 0, multiply3 = 0, multiply5 = 0;
        while (nextUgly < index) {
            int uglyNumber = min(uglyNumbers[multiply2] * 2, uglyNumbers[multiply3] * 3, uglyNumbers[multiply5] * 5);
            uglyNumbers[nextUgly] = uglyNumber;
            while (uglyNumbers[multiply2] * 2 <= uglyNumbers[nextUgly]) {
                ++multiply2;
            }
            while (uglyNumbers[multiply3] * 3 <= uglyNumbers[nextUgly]) {
                ++multiply3;
            }
            while (uglyNumbers[multiply5] * 5 <= uglyNumbers[nextUgly]) {
                ++multiply5;
            }
            ++nextUgly;
        }
        return uglyNumbers[index - 1];
    }

    private int min(int a, int b, int c) {
        int min = a < b ? a : b;
        return min < c ? min : c;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int result = solution.GetUglyNumber_Solution(6);
        System.out.println(result);
    }
}
