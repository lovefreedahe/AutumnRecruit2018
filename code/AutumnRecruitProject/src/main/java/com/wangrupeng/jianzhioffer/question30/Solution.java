package com.wangrupeng.jianzhioffer.question30;

public class Solution {
    public int FirstNotRepeatingChar(String str) {
        if (str.equals("")) {
            return -1;
        }
        Node[] ascii = new Node[256];
        char[] chars = str.toCharArray();
        for (int i = 0;i < chars.length;i++) {
            char c = chars[i];
            if (ascii[c] != null) {
                ascii[c].count++;
            } else {
                ascii[c] = new Node(1, i);
            }
        }
        int result = -1;
        for (int i = 0;i < ascii.length;i++) {
            if (ascii[i] != null && ascii[i].count == 1) {
                if (result == -1) {
                    result = ascii[i].position_origin;
                } else if (result > ascii[i].position_origin) {
                    result  = ascii[i].position_origin;
                }
            }
        }
        return result;
    }

    class Node {
        int count;
        int position_origin;
        Node(int count, int position_origin) {
            this.count = count;
            this.position_origin = position_origin;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int result = solution.FirstNotRepeatingChar("google");
        System.out.println(result);
    }
}
