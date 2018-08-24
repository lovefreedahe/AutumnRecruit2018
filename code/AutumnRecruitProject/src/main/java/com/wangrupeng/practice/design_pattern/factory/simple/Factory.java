package com.wangrupeng.practice.design_pattern.factory.simple;


import com.wangrupeng.practice.design_pattern.factory.Apple;

public class Factory {
    public Apple produce(String name) {
        if (name.equals("phone")) {
            return new Phone();
        } else if (name.equals("mac")) {
            return new Mac();
        }
        return null;
    }
}
