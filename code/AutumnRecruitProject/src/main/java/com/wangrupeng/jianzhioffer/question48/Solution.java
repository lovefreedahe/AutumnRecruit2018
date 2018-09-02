package com.wangrupeng.jianzhioffer.question48;

public class Solution {
    public boolean match(char[] str, char[] pattern) {
        if (str.length == 0 && pattern.length == 0) {
            return true;
        }
        if (str.length == 0 && pattern.length == 2 && pattern[1] == '*') {
            return true;
        }
        int i = str.length - 1;
        int j = pattern.length - 1;
        while (i >= 0 && j >=0 ) {
            if (pattern[j] == '*') {
                while (pattern[j] != str[i] && pattern[j] != '.') {
                    j--;
                }
                if (pattern[j+1] == str[i] || pattern[j + 1] == '*' || pattern[j] == '.') {
                    while ((pattern[j] == str[i] || pattern[j] == '.') && i > j) {
                        i--;
                    }
                }
                if (i == j && pattern[j] != str[i] && pattern[j] != '.') {
                    return false;
                }
            } else if (pattern[j] == '.') {
                j--;
                i--;
            } else {
                if (pattern[j] == str[i]) {
                    j--;
                    i--;
                }
            }
        }
        if (i == j) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "aaa";
        String pattern = ".*";
        System.out.println(solution.match(str.toCharArray(), pattern.toCharArray()));
    }
}
