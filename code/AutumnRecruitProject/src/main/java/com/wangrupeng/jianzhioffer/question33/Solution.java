package com.wangrupeng.jianzhioffer.question33;

public class Solution {
    public int GetNumberOfK(int [] array , int k) {
        if (array == null && array.length == 0) {
            return 0;
        }
        int position = GetPositionOfK(array, k, 0, array.length);
        if (position == -1) {
            return 0;
        } else {
            int count = 0;
            for (int i = position;i >=0;i--) {
                if (array[i] == k) {
                    count++;
                }
            }
            for (int i = position + 1;i < array.length;i++) {
                if (array[i] == k) {
                    count++;
                }
            }
            return count;
        }
    }

    private int GetPositionOfK(int[] array, int k, int start, int end) {
        if (start >= end) {
            return -1;
        }
        int middle = start + (end - start)/2;
        if (array[middle] == k) {
            return middle;
        } else {
            int left = GetPositionOfK(array, k, start, middle);
            if ( left != -1) {
                return left;
            }
            int right = GetPositionOfK(array, k, middle + 1, end);
            if (right != -1) {
                return right;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = {1,2,3,3,3,3,4,5};
        Solution solution = new Solution();
        System.out.println(solution.GetNumberOfK(array, 3));
    }
}
