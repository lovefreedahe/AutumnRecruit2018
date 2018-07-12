package com.wangrupeng.jianzhioffer.question9;

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
