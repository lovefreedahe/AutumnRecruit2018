package com.wangrupeng.jianzhioffer.question42;

public class Solution {
    public int LastRemaining_Solution(int n, int m) {
        if (n < 1 || m < 1) {
            return -1;
        }
        int last = 0;
        for (int i = 2;i <= n;i++) {
            last = (last + m) % i;
        }
        return last;
    }

    public int LastRemaining_Solution_Recursive(int n, int m) {
        return 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.LastRemaining_Solution(5, 3));
    }
}
