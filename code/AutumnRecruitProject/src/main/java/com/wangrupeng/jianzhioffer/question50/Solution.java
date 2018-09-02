package com.wangrupeng.jianzhioffer.question50;

public class Solution {
    private char[] chars = new char[128];
    private char first = 128;
    //Insert one char from stringstream
    public void Insert(char ch) {
        chars[ch]++;
        if (chars[ch] == 1) {
            if (first == 128) {
                first = ch;
            } else if (first == ch){
                first = 128;
            }
        } 
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        if (first == 128) {
            return '#';
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
