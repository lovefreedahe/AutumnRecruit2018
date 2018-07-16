package com.wangrupeng.pattern.bridge;

public class SaveToDB implements ISaveData {
    @Override
    public void save(Object data) {
        System.out.println(data + "Save to DB");
    }
}
