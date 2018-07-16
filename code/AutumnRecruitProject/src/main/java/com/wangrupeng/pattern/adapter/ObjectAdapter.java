package com.wangrupeng.pattern.adapter;

public class ObjectAdapter implements Target {
    private Adaptee adaptee;
    public ObjectAdapter() {
        super();
        adaptee = new Adaptee();
    }

    @Override
    public void playFlac(Object src) {
        //对src做处理
        adaptee.playMp3(src);
    }
}
