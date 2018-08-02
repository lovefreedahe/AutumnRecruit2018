package com.wangrupeng.pattern.proxy.dynamic;

public class RealSubject implements Subject {
    @Override
    public void hello() {
        System.out.println("Hello");
    }
}
