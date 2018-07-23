package com.wangrupeng.jianzhioffer.question10;

/**
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 */
public class ReactCover {
    public int reactCover(int target) {
        if (target <= 0) {
            return -1;
        } else if (target == 1) {
            return 1;
        } else if (target == 2) {
            return 2;
        }

        return reactCover(target - 1) + reactCover(target - 2);
    }

    public static void main(String[] args) {
        ReactCover react = new ReactCover();
        System.out.println(react.reactCover(4));
    }
}
