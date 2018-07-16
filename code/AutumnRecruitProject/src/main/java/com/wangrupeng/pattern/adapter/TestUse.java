package com.wangrupeng.pattern.adapter;

public class TestUse {
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        adaptee.playMp3("mp3");
        Target target = new ClassAdaptee();
        target.playFlac("flac");
        target = new ObjectAdapter();
        target.playFlac("flac");
    }
}
