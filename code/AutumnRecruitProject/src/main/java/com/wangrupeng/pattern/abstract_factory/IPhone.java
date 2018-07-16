package com.wangrupeng.pattern.abstract_factory;

public class IPhone implements Apple{
    @Override
    public void run() {
        System.out.println("Produce an iphone.");
    }
}
