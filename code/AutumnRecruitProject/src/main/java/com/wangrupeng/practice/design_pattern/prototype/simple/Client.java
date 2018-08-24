package com.wangrupeng.practice.design_pattern.prototype.simple;

public class Client {
    SimplePrototype simplePrototype;
    public Client(SimplePrototype simplePrototype) {
        this.simplePrototype = simplePrototype;
    }

    public Object getPrototype() {
        return simplePrototype.cloneSelf();
    }
}
