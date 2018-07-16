package com.wangrupeng.pattern.abstract_factory;


public class MacBook implements Apple{
    @Override
    public void run() {
        System.out.println("Produce a macbook.");
    }
}
