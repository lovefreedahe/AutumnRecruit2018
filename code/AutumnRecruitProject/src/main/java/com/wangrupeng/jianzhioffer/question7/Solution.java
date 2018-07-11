package com.wangrupeng.jianzhioffer.question7;

import java.util.Stack;

public class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        while(stack2.size() > 0) {
            int tmp = stack2.pop();
            stack1.push(tmp);
        }
        stack1.push(node);
    }

    public int pop() {
        if (stack1.size() == 0 && stack2.size() != 0) {
            return stack2.pop();
        }
        while(stack1.size() > 1) {
            int tmp = stack1.pop();
            stack2.push(tmp);
        }
        return stack1.pop();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.push(1);
        solution.push(2);
        solution.push(3);
        System.out.println(solution.pop());
        System.out.println(solution.pop());
        solution.push(4);
        System.out.println(solution.pop());
        solution.push(5);
        System.out.println(solution.pop());
        System.out.println(solution.pop());
    }
}
