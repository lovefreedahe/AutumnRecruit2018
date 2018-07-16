package com.wangrupeng.pattern.template;

public abstract class AbstractTemplate {
    Object data;
    void dealData() {
        getData();
        calcData();
        printData();
    }
    abstract void getData();
    abstract void calcData();
    void printData() {
        System.out.println(data);
    }
}
