package com.wangrupeng.pattern.decorator;

public class PersistantUtil implements IPersistanceUtil {
    @Override
    public void persistentMsg(String msg) {
        System.out.println(msg + " save to file");
    }
}
