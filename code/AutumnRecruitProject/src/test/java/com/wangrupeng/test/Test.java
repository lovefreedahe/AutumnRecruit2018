package com.wangrupeng.test;
public class Test {
    static int a;
    int b;
    static int c;

    public int aMethod() {
        a++;
        return a;
    }

    public int bMethod() {
        b++;
        return b;
    }

    public static int cMethod() {
        c++;
        return c;
    }

    public static void main(String args[]) {
        String str=null;
        str.concat("abc");
        str.concat("123");
        System.out.println(str);
    }
}