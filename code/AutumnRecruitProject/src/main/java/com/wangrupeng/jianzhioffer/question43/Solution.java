package com.wangrupeng.jianzhioffer.question43;

public class Solution {
    public int Sum_Solution(int n) {
        try {
            int i = 1/n;
            return n + Sum_Solution(n - 1);
        } catch (Exception e) {
            return 0;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.Sum_Solution(2018));
    }
}
