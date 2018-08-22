package com.wangrupeng.jianzhioffer.question20;

import java.util.Stack;

public class Solution {
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if (pushA.length != popA.length) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int pushAstart = 0;
        int popAstart = 0;
        while (popAstart < popA.length) {
            if (pushAstart < pushA.length) {
                if (pushA[pushAstart] != popA[popAstart]) {
                    stack.push(pushA[pushAstart++]);
                } else {
                    pushAstart++;
                    popAstart++;
                }
            }
            if (stack.isEmpty()) {
                continue;
            }

            if (stack.peek() == popA[popAstart]){
                popAstart++;
                stack.pop();
            } else if (pushAstart == pushA.length ) {
                pushAstart--;
                break;
            }
        }

        if (pushAstart == pushA.length) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        int[] A = {1};
        int[] B = {1};
        Solution solution = new Solution();
        boolean result = solution.IsPopOrder(A,B);
        System.out.println(result);
    }

}
