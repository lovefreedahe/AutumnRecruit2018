package com.wangrupeng.jianzhioffer.question46;

public class Solution {
    private boolean hasDuplicateNumber = false;
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        if (numbers.length == 0 || length == 0) {
            duplication[0] = -1;
            return false;
        }
        int[] map = new int[length/32 + 1];
        int position = 0;
        for (int i : numbers) {
            if (isExist(map, i)) {
                duplication[position] = i;
                return hasDuplicateNumber;
            }
        }
        return hasDuplicateNumber;
    }

    private boolean isExist(int[] map, int number) {
        int moveBytes = number/32;
        int moveBits = number % 32;
        if ((map[moveBytes] & (1 << moveBits)) != 0) {
            hasDuplicateNumber = true;
            return true;
        } else {
            map[moveBytes] = (map[moveBytes] | (1 << moveBits));
            return false;
        }
    }

    public static void main(String[] args) {
        int[] array = {};
        int[] duplicate = new int[array.length /2 + 1];
        Solution solution = new Solution();
        solution.duplicate(array, array.length, duplicate);
        for (int i : duplicate) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
