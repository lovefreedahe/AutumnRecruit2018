package com.wangrupeng.practice.design_pattern.factory.method;

import com.wangrupeng.practice.design_pattern.factory.Apple;
import com.wangrupeng.practice.design_pattern.factory.simple.Mac;
import com.wangrupeng.practice.design_pattern.factory.simple.Phone;

public class Factory {
    public Apple producePhone() {
        return new Phone();
    }

    public Apple produceMac() {
        return new Mac();
    }
}
