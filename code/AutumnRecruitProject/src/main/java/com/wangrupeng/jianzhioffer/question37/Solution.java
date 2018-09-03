package com.wangrupeng.jianzhioffer.question37;

import java.util.ArrayList;

public class Solution {
    private int formerSum = 0;
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        int i = 1, j = 2;

        while (i < j) {
            if (getSum(i, j) == sum) {
                arrayLists.add(addList(i, j));
                j++;
                updateSum(j, true);
            } else if (getSum(i, j) < sum) {
                j++;
                updateSum(j, true);
            } else {
                updateSum(i, false);
                i++;
            }
        }
        return arrayLists;
    }

    private int getSum(int start, int end) {
        if (formerSum == 0) {
            int sum = 0;
            while (start <= end) {
                sum += start;
                start++;
            }
            formerSum = sum;
        }
        return formerSum;
    }

    private int updateSum(int value, boolean add) {
        if (add) {
            formerSum += value;
        } else {
            formerSum -= value;
        }
        return formerSum;
    }

    private ArrayList<Integer> addList(int start, int end) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (start <= end) {
            arrayList.add(start++);
        }
        return arrayList;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.FindContinuousSequence(9);
    }
}
