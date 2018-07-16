package com.wangrupeng.test;

import java.util.Stack;

public class StackTest {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("test1");
        stack.push("test2");
        for (String s : stack) {
            System.out.println(s);
        }
    }
}
