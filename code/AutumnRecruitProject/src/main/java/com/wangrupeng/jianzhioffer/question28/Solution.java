package com.wangrupeng.jianzhioffer.question28;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public String PrintMinNumber(int[] numbers) {
        Integer[] integers = new Integer[numbers.length];
        for (int i = 0;i < numbers.length;i++) {
            integers[i] = numbers[i];
        }
        StringBuilder builder = new StringBuilder();
        Arrays.sort(integers, compare());
        for (int i : integers) {
            builder.append(i);
        }
        return builder.toString();
    }

    private Comparator compare() {
        return new Comparator() {
            @Override
            public int compare(Object a, Object b) {
                String str1 = String.valueOf(a);
                String str2 = String.valueOf(b);
                return (str1+str2).compareTo(str2 + str1);
            }
        };
    }

    public static void main(String[] args) {
        int[] array = {3,32,321};
        int[] array1 = {1,2,3,4,5};
        Solution solution = new Solution();
        String result = solution.PrintMinNumber(array1);
        System.out.println(result);
    }

}
