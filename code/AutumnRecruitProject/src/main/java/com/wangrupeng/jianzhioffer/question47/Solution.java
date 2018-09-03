package com.wangrupeng.jianzhioffer.question47;

public class Solution {
    public int[] multiply(int[] A) {
        int length = A.length;
        int[] B = new int[length];
        int[] temp = new int[length * 2];
        temp[0] = 1;
        temp[temp.length - 1] = 1;
        for (int i = 1;i < A.length ;i++) {
            if (i == 1) {
                temp[i] = A[i - 1];
                temp[temp.length - i - 1] = A[A.length - i];
            } else {
                temp[i] = temp[i - 1] * A[i - 1];
                temp[temp.length - i - 1] = A[A.length - i] * temp[temp.length - i];
            }
        }
        for (int i = 0;i < B.length ;i++) {
            B[i] = temp[i] * temp[length + i];
        }
        return B;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] array = {1,2,3,4,5};
        array = solution.multiply(array);
        for (int i : array){
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
