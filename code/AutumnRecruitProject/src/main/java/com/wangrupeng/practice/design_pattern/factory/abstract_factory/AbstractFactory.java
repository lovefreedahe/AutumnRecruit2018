package com.wangrupeng.practice.design_pattern.factory.abstract_factory;

import com.wangrupeng.practice.design_pattern.factory.Apple;
import com.wangrupeng.practice.design_pattern.factory.method.Factory;

public class AbstractFactory {
    public static void main(String[] args) {
        IFactory factory = new SmallFactory();
        Apple apple = factory.produceApple();
        HeadSet headSet = factory.produceHeadSet();
        apple.run();
        headSet.run();
    }
}
