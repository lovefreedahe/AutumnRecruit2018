package com.wangrupeng.jianzhioffer.question38;

import java.util.ArrayList;

public class Solution {
    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        int i = 0;
        int j = array.length - 1;
        ArrayList<Integer> arrayList = new ArrayList<>(2);
        while (i < j) {
            if (array[i] + array[j] == sum) {
                arrayList.add(array[i]);
                arrayList.add(array[j]);
                break;
            } else if (array[i] + array[j] < sum) {
                i++;
            } else {
                j--;
            }
        }
        return arrayList;
    }

    public static void main(String[] args) {
        int[] array = {1,2,4,7,11,15};
        Solution solution = new Solution();
        ArrayList<Integer> arrayList = solution.FindNumbersWithSum(array, 15);
        arrayList.forEach(value -> System.out.print(value + " "));
    }
}
