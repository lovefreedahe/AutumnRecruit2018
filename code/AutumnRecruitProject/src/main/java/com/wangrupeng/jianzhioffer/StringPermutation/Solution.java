package com.wangrupeng.jianzhioffer.StringPermutation;

import java.util.ArrayList;

public class Solution {
    private ArrayList<String> list = new ArrayList<>();
    public ArrayList<String> Permutation(String str) {
        if (str == null) {
            return null;
        }
        if (str.equals("")) {
            return list;
        }
        Permutation(str, 0, str.length() - 1);
        return list;
    }

    private void Permutation(String str, int start, int end) {

    }
}
