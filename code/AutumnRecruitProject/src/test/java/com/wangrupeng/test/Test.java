package com.wangrupeng.test;

class A {
    static {
        System.out.print("A");
    }
    public A() {
        System.out.print("a");
    }
}
class B extends A {
    static {
        System.out.print("B");
    }
    public B() {
        System.out.print("b");
    }
}

class Hello {
    public static void hello() {
        System.out.println("hello");
    }
}

public class Test {
    public static void main(String[] args) {
        int i = 0;
        while(i < (i ++) + 1);
        System.out.println(i);
    }
}