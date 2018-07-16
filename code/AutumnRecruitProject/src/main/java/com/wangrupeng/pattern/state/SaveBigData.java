package com.wangrupeng.pattern.state;

public enum  SaveBigData implements ISaveData{
    INSTANCE;

    @Override
    public void save(String data) {
        System.out.println("Save to file:" + data);
    }
}
