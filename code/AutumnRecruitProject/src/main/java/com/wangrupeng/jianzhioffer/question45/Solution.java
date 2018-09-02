package com.wangrupeng.jianzhioffer.question45;

public class Solution {
    public int StrToInt(String str) {
        if (str.equals("")) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int result = 0;
        for (int i = chars.length - 1;i >= 0;i--) {
            if (i == 0) {
                if (chars[i] == '+') {
                    return result;
                } else if (chars[i] == '-') {
                    result = 0 - result;
                } else if (chars[i] >= 48 && chars[i] <= 57){
                    int temp = chars[i] - 48;
                    result += (temp * Math.pow(10, chars.length - 1 - i));
                    return result;
                } else {
                    return 0;
                }
            }
            if (chars[i] >= 48 && chars[i] <= 57) {
                int temp = chars[i] - 48;
                result += (temp * Math.pow(10, chars.length - 1 - i));
            } else {
                return 0;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.StrToInt("123"));

    }
}
