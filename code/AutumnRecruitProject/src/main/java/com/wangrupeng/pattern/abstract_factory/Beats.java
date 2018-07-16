package com.wangrupeng.pattern.abstract_factory;

public class Beats implements HeadSet {
    @Override
    public void play() {
        System.out.println("Play with Beats head set.");
    }
}
