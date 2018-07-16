package com.wangrupeng.pattern.state;

public enum SaveSmallData implements ISaveData {
    INSANCE;
    @Override
    public void save(String data) {
        System.out.println("Save to redis:" + data);
    }
}
