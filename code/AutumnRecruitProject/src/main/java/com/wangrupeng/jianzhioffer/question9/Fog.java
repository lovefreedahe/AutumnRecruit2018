package com.wangrupeng.jianzhioffer.question9;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 */
public class Fog {
    public int JumpFloor(int target) {
        if(target == 0) {
            return 1;
        }
        if(target == 1) {
            return 1;
        }
        return JumpFloor(target - 1) + JumpFloor(target -2);
    }

    public int JumpFloorEffect(int target) {
        if (target == 0) {
            return 1;
        }
        if (target == 1) {
            return 1;
        }

        int tmp1 = 1;
        int tmp2 = 1;
        for (int i = 2; i < target; i++) {
            int tmp3 = tmp1 + tmp2;
            tmp1 = tmp2;
            tmp2 = tmp3;
        }
        return tmp1 + tmp2;
    }
}
