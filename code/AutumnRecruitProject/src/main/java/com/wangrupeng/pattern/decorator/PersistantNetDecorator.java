package com.wangrupeng.pattern.decorator;

public class PersistantNetDecorator extends PersistantDecorator {
    public PersistantNetDecorator(IPersistanceUtil iPersistanceUtil) {
        super(iPersistanceUtil);
    }

    @Override
    public void persistentMsg(String msg) {
        iPersistanceUtil.persistentMsg(msg);
        persistantToNet(msg);
    }

    private void persistantToNet(String msg) {
        System.out.println(msg + " save to net");
    }
}
