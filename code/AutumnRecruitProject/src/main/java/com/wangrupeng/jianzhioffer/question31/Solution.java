package com.wangrupeng.jianzhioffer.question31;

import java.util.Arrays;

public class Solution {

    public int InversePairs(int [] array) {
        if (array.length == 0) {
            return 0;
        }
        if (array.length == 1) {
            return 1;
        }
        int[] copy = Arrays.copyOf(array, array.length);
        int count = InversePairsCore(array, copy, 0, array.length - 1);
        /*for (int i : copy) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(count);*/
        return count % 1000000007;
    }

    private int InversePairsCore(int[] array, int[] copy, int start, int end) {
        if (start == end) {
            copy[start] = array[start];
            return 0;
        }

        int middle = start + (end - start) / 2;
        int left = InversePairsCore(copy, array, start, middle);
        int right = InversePairsCore(copy, array, middle + 1, end);
        int i = middle;
        int j = end;
        int count = 0;
        int indexCopy = end;
        while (i >= start && j >= middle + 1) {
            if (array[i] > array[j]) {
                copy[indexCopy--] = array[i--];
                count += (j - middle);
            } else {
                copy[indexCopy--] = array[j--];
            }
        }
        for (;i >= start;i--) {
            copy[indexCopy--] = array[i];
        }
        for (;j >= middle + 1;j--) {
            copy[indexCopy--] = array[j];
        }

        return left + right + count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] array = {1,2,3,4,5,6,7,0};
        int count = solution.InversePairs(array);
        System.out.println(count);
    }
}
