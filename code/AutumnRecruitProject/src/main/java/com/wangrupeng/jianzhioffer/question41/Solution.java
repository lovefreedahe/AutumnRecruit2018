package com.wangrupeng.jianzhioffer.question41;

import java.util.Arrays;

public class Solution {
    public boolean isContinuous(int [] numbers) {
        if (numbers == null || numbers.length == 0) {
            return false;
        }
        Arrays.sort(numbers);
        int numZero = 0;
        int missedNumber = 0;
        for (int i = 0;i < numbers.length;i++) {
            if (numbers[i] == 0) {
                numZero++;
            } else if (i < numbers.length - 1 && numbers[i + 1] - numbers[i] > 1) {
                missedNumber += (numbers[i + 1] - numbers[i] - 1);
            } else if (i < numbers.length - 1 && numbers[i + 1] == numbers[i]) {
                return false;
            }
        }
        if (numZero >= missedNumber) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] array = {0,1,3,4,5};
        System.out.println(new Solution().isContinuous(array));
    }
}
