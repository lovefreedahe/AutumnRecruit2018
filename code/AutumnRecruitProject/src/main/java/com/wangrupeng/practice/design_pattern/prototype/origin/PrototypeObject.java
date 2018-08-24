package com.wangrupeng.practice.design_pattern.prototype.origin;

public class PrototypeObject implements Cloneable {
    private int value;
    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
