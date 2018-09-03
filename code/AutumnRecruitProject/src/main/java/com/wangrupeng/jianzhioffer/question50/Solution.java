package com.wangrupeng.jianzhioffer.question50;

import java.util.ArrayList;

public class Solution {
    private char[] chars = new char[128];
    private char index = 1;
    //Insert one char from stringstream
    public void Insert(char ch) {
        if (chars[ch] == 0) {
            chars[ch] = index++;
        } else if( chars[ch] > 0 && chars[ch] < 128) {
            chars[ch] = 128;
        }
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        int minIndex = 128;
        char first = '#';
        for (int i = 0;i < 128;i++) {
            if (chars[i] != 0 && chars[i] < minIndex) {
                minIndex = chars[i];
                first = (char)i;
            }
        }
        return first;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "google";
        char[] chars = str.toCharArray();
        for (char i : chars) {
            solution.Insert(i);
            System.out.println(solution.FirstAppearingOnce());
        }
    }
}
