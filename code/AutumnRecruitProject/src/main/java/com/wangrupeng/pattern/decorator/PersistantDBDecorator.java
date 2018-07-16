package com.wangrupeng.pattern.decorator;

public class PersistantDBDecorator extends PersistantDecorator {
    public PersistantDBDecorator(IPersistanceUtil iPersistanceUtil) {
        super(iPersistanceUtil);
    }

    @Override
    public void persistentMsg(String msg) {
        iPersistanceUtil.persistentMsg(msg);
        persitantToDB(msg);
    }

    private void persitantToDB(String msg) {
        System.out.println(msg + " save to DB");
    }
}
