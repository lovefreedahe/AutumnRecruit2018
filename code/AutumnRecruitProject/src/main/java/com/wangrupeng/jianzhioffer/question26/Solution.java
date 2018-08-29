package com.wangrupeng.jianzhioffer.question26;

public class Solution {
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        if (array.length == 1) {
            return array[0];
        }

        int sum = 0;
        int maxSum = 0;
        for (int i = 0;i < array.length;i++) {
            sum += array[i];

        }
        return 0;
    }

    public static void main(String[] args) {

    }
}
