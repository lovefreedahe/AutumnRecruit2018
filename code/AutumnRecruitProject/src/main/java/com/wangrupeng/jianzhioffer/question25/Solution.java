package com.wangrupeng.jianzhioffer.question25;

import java.util.ArrayList;
import java.util.TreeSet;

public class Solution {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (input.length < k || k == 0) {
            return arrayList;
        }
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0;i < input.length;i++) {
            if (treeSet.size() < k) {
                treeSet.add(input[i]);
            } else {
                if (treeSet.last() > input[i]) {
                    treeSet.pollLast();
                    treeSet.add(input[i]);
                }
            }
        }

        treeSet.forEach(value -> arrayList.add(value));
        return arrayList;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] array = {4,5,1,6,2,7,3,8};
        ArrayList<Integer> arrayList = solution.GetLeastNumbers_Solution(array, 4);
        arrayList.forEach(value -> System.out.println(value));
    }
}
