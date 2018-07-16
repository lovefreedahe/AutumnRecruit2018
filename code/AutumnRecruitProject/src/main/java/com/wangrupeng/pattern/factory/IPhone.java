package com.wangrupeng.pattern.factory;

public class IPhone implements Apple{
    @Override
    public void run() {
        System.out.println("Produce an iphone.");
    }
}
