package com.wangrupeng.practice.design_pattern.factory.abstract_factory;

import com.wangrupeng.practice.design_pattern.factory.Apple;

public interface IFactory {
    Apple produceApple();
    HeadSet produceHeadSet();
}
