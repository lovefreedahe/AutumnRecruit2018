package com.wangrupeng.practice.design_pattern.prototype.simple;

public class SimplePrototype implements Prototype{
    private int value = 0;

    public SimplePrototype(){}
    public SimplePrototype(int value) {
        this.value = value;
    }

    @Override
    public Object cloneSelf() {
        SimplePrototype simplePrototype = new SimplePrototype();
        simplePrototype.value = this.value;
        return simplePrototype;
    }
    public int getValue() {
        return value;
    }

    public static void main(String[] args) {
        SimplePrototype simplePrototype = new SimplePrototype(500);
        SimplePrototype simplePrototype1 = (SimplePrototype)simplePrototype.cloneSelf();
        System.out.println(simplePrototype1.getValue());
    }
}
