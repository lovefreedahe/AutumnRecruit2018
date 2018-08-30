package com.wangrupeng.jianzhioffer.question26;

public class Solution {
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0;i < array.length;i++) {
            if (sum <= 0) {
                sum = array[i];
            } else {
                sum += array[i];
            }
            if (sum > maxSum) {
                maxSum = sum;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] array = {6,-3,-2,7,-15,1,2,2};
        int[] array1 = {1, -2, 3, 10, -4, 7, 2, -5};
        Solution solution = new Solution();
        int result = solution.FindGreatestSumOfSubArray(array1);
        System.out.println(result);
    }
}
