package com.wangrupeng.test;

class C {
    C() {
        System.out.print("C");
    }
}

class A {
    C c = new C();

    A() {
        this("A");
        System.out.print("A");
    }

    A(String s) {
        System.out.print(s);
    }
}

class StaticObjectTest extends A {
    StaticObjectTest() {
        super("B");
        System.out.print("B");
    }

    public static void main(String[] args) {
        System.out.println(10 % 3 * 2);
    }
}

