package com.wangrupeng.jianzhioffer.question22;

public class Solution {
    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence == null) {
            return false;
        }
        return verifySequenceOfBST(sequence, 0, sequence.length - 1);
    }

    private boolean verifySequenceOfBST(int[] sequence, int start, int end) {
        if (start > end) {
            return false;
        } else if (start == end) {
            return true;
        }
        int root = sequence[end];
        int i = start;
        for (;i < end;++i) {
            if (sequence[i] > root){
                break;
            }
        }
        int j = i;
        for (;j < end;++j) {
            if (sequence[j] < root) {
                return false;
            }
        }

        boolean left = true, right = true;
        if (i > 0) {
            left = verifySequenceOfBST(sequence,start, i - 1);
        }

        if (i < end) {
            right = verifySequenceOfBST(sequence, i, j - 1);
        }

        return left && right;
    }

    public static void main(String[] args) {
        int[] array = {7,4,6,5};
        Solution solution = new Solution();
        boolean result = solution.VerifySquenceOfBST(array);
        System.out.println(result);
    }
}
