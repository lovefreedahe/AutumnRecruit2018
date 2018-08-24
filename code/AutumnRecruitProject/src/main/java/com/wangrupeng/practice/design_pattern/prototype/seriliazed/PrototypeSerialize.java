package com.wangrupeng.practice.design_pattern.prototype.seriliazed;

import java.io.Serializable;

public class PrototypeSerialize implements Serializable {

    private int age = 23;
    private transient int value = 10;
    private int height = 175;
    private String name = "Tony";

    public int getAge() {
        return age;
    }

    public int getValue() {
        return value;
    }

    public int getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return  "Age:" + age + ", height:" + height + ", name:" + name+ ", value:" + value;
    }
}
