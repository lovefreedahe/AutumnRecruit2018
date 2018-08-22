package com.wangrupeng.jianzhioffer.question13;

public class Solution {

    public void reOrderArray(int [] array) {
        int length = array.length;
        int[] uneven = new int[length];
        int[] even = new int[length];
        int countUneven = 0;
        int countEven = 0;
        for (int k : array) {
            if ((k & 0x1) == 1) {
                uneven[countUneven++] = k;
            } else {
                even[countEven++] = k;
            }
        }
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < countUneven) {
            array[k++] = uneven[i++];
        }
        while (j < countEven) {
            array[k++] = even[j++];
        }

    }

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void main(String[] args) {
        int[] array = {2, 4, 6, 8, 1, 3, 5, 7, 9};
        Solution solution = new Solution();
        solution.reOrderArray(array);
        for (int i : array) {
            System.out.print(i + " ");
        }
    }
}
