package com.wangrupeng;

import java.lang.reflect.Array;
import java.util.*;
class A {

}

class B extends A {

}
public class Mess {



    public static void main(String[] args) {
        int[] test = {};
        String[] strings = {"1", "2"};
        List<Integer> list = new ArrayList<>();
        A a = new B();
        B b = new B();
        System.out.println(test instanceof int[]);
        System.out.println(strings.getClass().isArray());
        System.out.println(test.getClass().isArray());
        System.out.println(list instanceof ArrayList);
        System.out.println(a instanceof B);
        System.out.println(b instanceof A);
    }
}
