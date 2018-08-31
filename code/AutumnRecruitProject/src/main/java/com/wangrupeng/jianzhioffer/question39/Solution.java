package com.wangrupeng.jianzhioffer.question39;

public class Solution {
    public String LeftRotateString(String str,int n) {
        if (n == 0 || str == null || n == str.length() || str.equals("")) {
            return str;
        } else if (n > str.length()) {
            n = n % str.length();
        }
        StringBuilder builder = new StringBuilder(str);
        String header = builder.substring(0, n);
        String tail = builder.substring(n, str.length());
        return tail + header;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String result  = solution.LeftRotateString("abcXYZdef", 3);
        System.out.println(result);
    }
}
