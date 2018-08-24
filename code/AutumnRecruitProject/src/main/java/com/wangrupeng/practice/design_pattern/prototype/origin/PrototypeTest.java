package com.wangrupeng.practice.design_pattern.prototype.origin;

import com.wangrupeng.practice.design_pattern.prototype.simple.Prototype;

public class PrototypeTest {
    public static void main(String[] args) throws CloneNotSupportedException{
        PrototypeObject object = new PrototypeObject();
        object.setValue(500);
        PrototypeObject object1 = (PrototypeObject) object.clone();
        System.out.println(object1.getValue());
    }
}
