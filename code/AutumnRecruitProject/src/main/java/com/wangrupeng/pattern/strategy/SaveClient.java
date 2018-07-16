package com.wangrupeng.pattern.strategy;

public class SaveClient {
    private ISaveData saveData;
    public SaveClient(ISaveData saveData) {
        this.saveData = saveData;
    }
    public void setSaveData(ISaveData saveData) {
        this.saveData = saveData;
    }
    public void save(Object data) {
        this.saveData.save(data);
    }
}
