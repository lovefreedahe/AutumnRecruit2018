package com.wangrupeng.jianzhioffer.question36;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    public void FindNumsAppearOnce(int[] array,int num1[] , int num2[]) {
        if (array.length == 0) {
            return;
        }
        List<Integer> list1 = new LinkedList<>();
        List<Integer> list2 = new LinkedList<>();
        int xor_result = array[0];
        for (int i =1;i < array.length;i++) {
            xor_result ^= array[i];
        }
        if (xor_result == 0) {
            return;
        }
        int position = findFirstOfBit1(xor_result);
        for (int i = 0;i < array.length;i++) {
            if ((array[i] & position) != 0) {
                list1.add(array[i]);
            } else {
                list2.add(array[i]);
            }
        }
        int result1 = ((LinkedList<Integer>) list1).getFirst();
        for (int i = 1;i < list1.size();i++) {
            result1 ^= list1.get(i);
        }
        int result2 = ((LinkedList<Integer>) list2).getFirst();
        for (int i = 1;i < list2.size();i++) {
            result2 ^= list2.get(i);
        }
        //System.out.println("Result is " + result1 + " " + result2);
        num1[0] = result1;
        num2[0] = result2;
    }

    public int findFirstOfBit1(int xor_result) {
        for (int i = 0;i < 32;i++) {
            if ((xor_result & (1 << i)) != 0) {
                return 1 << i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] array = {2,4,3,6,3,2,5,5};
        int[] num1 = new int[array.length];
        int[] num2 = new int[array.length];
        Solution solution = new Solution();
        solution.FindNumsAppearOnce(array, num1, num2);
    }
}
