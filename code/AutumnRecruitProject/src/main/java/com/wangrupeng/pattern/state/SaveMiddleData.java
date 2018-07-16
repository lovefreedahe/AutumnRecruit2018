package com.wangrupeng.pattern.state;

public enum  SaveMiddleData implements ISaveData{
    INSTANCE;

    @Override
    public void save(String data) {
        System.out.println("Save to mysql:" + data);
    }
}
