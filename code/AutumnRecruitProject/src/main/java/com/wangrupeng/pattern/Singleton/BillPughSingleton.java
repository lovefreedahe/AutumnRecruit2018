package com.wangrupeng.pattern.Singleton;

import java.io.Serializable;

public class BillPughSingleton implements Serializable {
    private static final long serialVersionUID = -7604766932017737115L;
    private static boolean flag = true;
    //防止反射破坏单例模式
    private BillPughSingleton() {
        if (!flag) {
            flag = !flag;
        } else {
            throw new RuntimeException("Could not create another instance!");
        }
    }
    private static class InstanceManager {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return InstanceManager.INSTANCE;
    }

    //防止序列化破坏单例模式
    protected Object readResolve() {
        return getInstance();
    }
}
