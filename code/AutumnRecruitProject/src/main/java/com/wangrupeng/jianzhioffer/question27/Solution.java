package com.wangrupeng.jianzhioffer.question27;

//解决方法：https://blog.csdn.net/yi_afly/article/details/52012593
public class Solution {
    public int NumberOf1Between1AndN_Solution(int n) {
        if (n < 1) {
            return 0;
        }
        int count = 0;
        int round = n;
        int base = 1;
        while (round > 0) {
            int weight = round % 10;
            round /= 10;
            count += round * base;
            if (weight == 1) {
                count += (n % base + 1);
            } else if (weight > 1) {
                count += base;
            }
            base *= 10;
        }
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int result = solution.NumberOf1Between1AndN_Solution(534);
        System.out.println(result);
    }
}
