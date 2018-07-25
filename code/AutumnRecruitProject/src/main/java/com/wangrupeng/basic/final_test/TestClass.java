package com.wangrupeng.basic.final_test;

import com.wangrupeng.basic.abstract_test.Test;

public class TestClass implements Test {
    /*@Override
    public int t() {
        return 0;
    }*/

    @Override
    public double t(double a) {
        return 0;
    }

    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        System.out.println(testClass.t());
    }
}
