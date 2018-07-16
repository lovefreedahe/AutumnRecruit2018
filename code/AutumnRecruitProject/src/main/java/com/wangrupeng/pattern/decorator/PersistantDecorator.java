package com.wangrupeng.pattern.decorator;

public abstract class PersistantDecorator  implements IPersistanceUtil {
    IPersistanceUtil iPersistanceUtil;
    public PersistantDecorator(IPersistanceUtil iPersistanceUtil) {
        this.iPersistanceUtil = iPersistanceUtil;
    }

    @Override
    public void persistentMsg(String msg) {
        iPersistanceUtil.persistentMsg(msg);
    }
}
