package com.wangrupeng.pattern.bridge;

public class SaveToFile implements ISaveData {

    @Override
    public void save(Object data) {
        System.out.println(data + "Save to file");
    }
}
