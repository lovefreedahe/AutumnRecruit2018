package com.wangrupeng.pattern.adapter;

public class ClassAdaptee extends Adaptee implements Target {
    @Override
    public void playFlac(Object src) {
        //对src做处理
        playMp3(src);
    }
}
