package com.wangrupeng.pattern.midiator;

public class TestUse {
    public static void main(String args[]){
        Object data = "数据";
        PersistentDB persistentDB = new PersistentDB();
        PersistentFile persistentFile = new PersistentFile();
        Midiator midiator = new Midiator();
        midiator.setPersistentDB(persistentDB).setPersistentFile(persistentFile);
        persistentDB.getData(data, midiator);
        persistentFile.getData(data, midiator);
    }
}//输出（省略了换行符）：数据 已保存到数据库数据 已保存到文件数据 已保存到文件数据 已保存到数据库