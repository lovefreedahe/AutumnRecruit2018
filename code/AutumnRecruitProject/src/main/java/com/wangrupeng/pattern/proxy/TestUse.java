package com.wangrupeng.pattern.proxy;

public class TestUse {
    public static void main(String[] args) {
        AbstractObject object = new ProxyObject();
        object.method1();
        object.method2();
        object.method3();
    }
}
