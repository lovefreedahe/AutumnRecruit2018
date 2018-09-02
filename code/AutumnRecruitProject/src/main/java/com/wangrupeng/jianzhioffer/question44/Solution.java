package com.wangrupeng.jianzhioffer.question44;

public class Solution {
    public int Add(int num1,int num2) {
        int sum;
        do {
            sum = num1 ^ num2;
            num2 = (num1 & num2) << 1;
            num1 = sum;
        } while (num2 != 0);
        return num1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.Add(3,3));
    }
}
