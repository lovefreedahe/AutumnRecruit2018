package com.wangrupeng.practice.design_pattern.factory.simple;

import com.wangrupeng.practice.design_pattern.factory.Apple;

public class Mac implements Apple {
    @Override
    public void run() {
        System.out.println("Produce a Mac.");
    }
}
