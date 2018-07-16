package com.wangrupeng.pattern.bridge;

public class LocalSave extends AbstractSave {
    public LocalSave(ISaveData saveData) {
        super(saveData);
    }
    @Override
    public void save(Object data) {
        System.out.println("Save to local:");
        saveData.save(data);
    }
}
