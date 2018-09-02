package com.wangrupeng.jianzhioffer.question40;

public class Solution {
    public String ReverseSentence(String str) {
        /*if (str.equals(" ")) {
            return str;
        }*/
        String[] strings = str.split(" ");
        if (strings.length == 0) {
            return str;
        }
        String result = "";
        result += strings[strings.length - 1];

        for (int i = strings.length - 2;i >= 0;i--) {
            result += (" " + strings[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String result = solution.ReverseSentence(" ");
        System.out.println(result);
    }
}
