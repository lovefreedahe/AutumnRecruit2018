package com.wangrupeng.pattern.strategy;

public class SaveToRedis implements ISaveData {
    @Override
    public void save(Object data) {
        System.out.println("Data:" + data + " save to redis.");
    }
}
