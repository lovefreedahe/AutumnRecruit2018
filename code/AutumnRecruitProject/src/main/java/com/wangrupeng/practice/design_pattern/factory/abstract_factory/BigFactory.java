package com.wangrupeng.practice.design_pattern.factory.abstract_factory;

import com.wangrupeng.practice.design_pattern.factory.Apple;
import com.wangrupeng.practice.design_pattern.factory.simple.Mac;

public class BigFactory implements IFactory {
    @Override
    public Apple produceApple() {
        return new Mac();
    }

    @Override
    public HeadSet produceHeadSet() {
        return new Sony();
    }
}
