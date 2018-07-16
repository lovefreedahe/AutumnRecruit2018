package com.wangrupeng.pattern.midiator;

//具体中介者
public class Midiator {
    PersistentDB persistentDB;//此处可以使用List来存放所有的同事
    PersistentFile persistentFile;
    public Midiator setPersistentDB(PersistentDB persistentDB) {
        this.persistentDB = persistentDB;
        return this;
    }
    public Midiator setPersistentFile(PersistentFile persistentFile) {
        this.persistentFile = persistentFile;
        return this;
    }
    public void notifyOther(IPersistent persistent,Object data){
        if(persistent instanceof PersistentDB)//如果同事都放在List中，此处遍历即可
            persistentFile.getData(data);
        if(persistent instanceof PersistentFile)
            persistentDB.getData(data);
    }
}