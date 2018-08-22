package com.wangrupeng.jianzhioffer.StringPermutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

public class Solution {
    private ArrayList<String> list = new ArrayList<>();
    public ArrayList<String> Permutation(String str) {
        if (str == null) {
            return null;
        }
        if (str.equals("")) {
            return list;
        }
        if (str.length() == 1) {
            list.add(str);
            return list;
        }
        PermutationRecursive(str.toCharArray(), 0);
        return new ArrayList<>(new TreeSet<>(list));
    }

    private void PermutationRecursive(char[] chars, int position) {
        if (position + 1 >= chars.length) {
            list.add(String.valueOf(chars));
            return;
        }
        //list.add(String.copyValueOf(chars));
        for (int i = position;i < chars.length;i++) {
            swap(chars, position, i);
            PermutationRecursive(chars, position + 1);
            swap(chars, position, i);
        }
    }

    private void swap(char[] chars, int a, int b) {
        if (a == b) {
            return;
        }
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
    }

    private char[] copyChars(char[] chars) {
        char[] temp = new char[chars.length];
        for (int i = 0;i < chars.length;i++) {
            temp[i] = chars[i];
        }
        return temp;
    }

    public static void main(String[] args) {
        String test = "aabc";
        Solution solution = new Solution();
        ArrayList<String> list = solution.Permutation(test);
        for (String str : list) {
            System.out.println(str);
        }

    }
}
