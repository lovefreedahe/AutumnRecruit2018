package com.wangrupeng.practice.design_pattern.factory.method;

import com.wangrupeng.practice.design_pattern.factory.Apple;

public class MethodFactory {
    public static void main(String[] args) {
        Factory  factory = new Factory();
        Apple phone = factory.producePhone();
        Apple mac = factory.produceMac();
        phone.run();
        mac.run();
    }
}
