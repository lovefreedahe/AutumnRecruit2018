package com.wangrupeng.practice.design_pattern.factory.simple;


import com.wangrupeng.practice.design_pattern.factory.Apple;

public class SimpleFactory {
    public static void main(String[] args) {
        String productName = "phone";
        Factory factory = new Factory();
        Apple apple = factory.produce(productName);
        if (apple != null) {
            apple.run();
        }
        productName = "mac";
        apple = factory.produce(productName);
        apple.run();
    }
}
