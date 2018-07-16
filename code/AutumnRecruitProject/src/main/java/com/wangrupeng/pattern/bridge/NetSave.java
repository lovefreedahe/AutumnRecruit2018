package com.wangrupeng.pattern.bridge;

public class NetSave extends AbstractSave {

    public NetSave(ISaveData saveData) {
        super(saveData);
    }
    @Override
    public void save(Object data) {
        System.out.println("Save to net:");
        saveData.save(data);
    }
}
