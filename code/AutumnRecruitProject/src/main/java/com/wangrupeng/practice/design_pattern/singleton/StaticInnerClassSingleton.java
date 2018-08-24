package com.wangrupeng.practice.design_pattern.singleton;

import com.wangrupeng.pattern.proxy.Stack;

public class StaticInnerClassSingleton {
    private StaticInnerClassSingleton(){};
    private static class StaticInnerClassSingletonManager {
        private static final StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
    }
    public static StaticInnerClassSingleton getInstance() {
        return StaticInnerClassSingletonManager.instance;
    }
}
