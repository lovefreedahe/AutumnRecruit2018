package com.wangrupeng.pattern.bridge;

public class TestUse {
    public static void main(String[] args) {
        Object data = "数据";
        ISaveData saveDataDB = new SaveToDB();
        ISaveData saveDataFile = new SaveToFile();
        AbstractSave save;
        save = new NetSave(saveDataDB);
        save.save(data);
        save = new NetSave(saveDataFile);
        save.save(data);
        save = new LocalSave(saveDataDB);
        save.save(data);
        save = new LocalSave(saveDataFile);
        save.save(data);
    }
}
