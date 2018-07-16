package com.wangrupeng.pattern.proxy;

public class TargetObject implements AbstractObject {
    @Override
    public void method1() {
        System.out.println("Method 1");
    }

    @Override
    public int method2() {
        System.out.println("Method 2");
        return 0;
    }

    @Override
    public void method3() {
        System.out.println("Method 3");
    }
}
