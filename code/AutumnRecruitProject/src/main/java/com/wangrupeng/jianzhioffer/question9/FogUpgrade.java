package com.wangrupeng.jianzhioffer.question9;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 */
public class FogUpgrade {
    public int JumpFloorII(int target) {
        if (target == 0 || target == 1) {
            return 1;
        }
        return 2 * JumpFloorII(target - 1);
    }

    public int JumpFloorIII(int target) {
        if (target == 0 || target == 1) {
            return 1;
        }
        int tmp1 = 1;
        int tmp2 = 1;
        int result = 2;
        for (int i = 2; i < target;i++) {
            result += result;
        }
        return result;
    }

    public static void main(String[] args) {
        FogUpgrade fogUpgrade = new FogUpgrade();
        System.out.println(fogUpgrade.JumpFloorIII(4));
    }
}
