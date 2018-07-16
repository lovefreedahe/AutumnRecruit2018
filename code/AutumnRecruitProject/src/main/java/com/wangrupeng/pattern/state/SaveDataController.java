package com.wangrupeng.pattern.state;

public class SaveDataController {
    private ISaveData saveData;

    public void save(String data) {
        if (data.length() < 1 << 2) {
            saveData = SaveSmallData.INSANCE;
        } else if (data.length() < 1 << 4) {
            saveData = SaveMiddleData.INSTANCE;
        } else {
            saveData = SaveBigData.INSTANCE;
        }
        saveData.save(data);
    }
}
