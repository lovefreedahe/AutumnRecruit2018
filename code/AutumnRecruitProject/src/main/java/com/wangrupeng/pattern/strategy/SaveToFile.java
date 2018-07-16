package com.wangrupeng.pattern.strategy;

public class SaveToFile implements ISaveData{
    @Override
    public void save(Object data) {
        System.out.println("Data:" + data + " save to file.");
    }
}
