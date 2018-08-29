package com.wangrupeng.jianzhioffer.question24;

public class Solution {
    public int MoreThanHalfNum_Solution(int [] array) {
        if (array.length == 0) {
            return 0;
        }
        int number = array[0];
        int count = 1;
        for(int i = 1;i < array.length;i++) {
            if (array[i] != number) {
                count--;
            } else {
                count++;
            }
            if (count == 0) {
                number = array[i];
                count = 1;
            }
        }
        if (!checkMoreThanHalf(array, number)) {
            return 0;
        }
        return number;
    }

    private boolean checkMoreThanHalf(int[] array, int number) {
        int count = 0;
        for (int i = 0;i < array.length; i++) {
            if (array[i] == number) {
                count++;
            }
        }
        if (count * 2 > array.length) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] array = {1,2,3,2,2,2,5,4,2};
        Solution solution = new Solution();
        int result = solution.MoreThanHalfNum_Solution(array);
        System.out.println(result);
    }
}
