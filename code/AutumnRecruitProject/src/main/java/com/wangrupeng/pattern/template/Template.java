package com.wangrupeng.pattern.template;

public class Template extends AbstractTemplate {
    @Override
    void getData() {
        data = "data";
    }

    @Override
    void calcData() {
        data = (String)data + data;
    }
}
