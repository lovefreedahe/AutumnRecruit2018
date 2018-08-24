package com.wangrupeng.practice.design_pattern.factory.abstract_factory;

import com.wangrupeng.practice.design_pattern.factory.Apple;
import com.wangrupeng.practice.design_pattern.factory.simple.Phone;

public class SmallFactory implements IFactory{
    @Override
    public Apple produceApple() {
        return new Phone();
    }

    @Override
    public HeadSet produceHeadSet() {
        return new Beats();
    }
}
