package com.wangrupeng.pattern.decorator;

public class TestUse {
    public static void main(String[] args) {
        final String data = "数据";
        IPersistanceUtil iPersistanceUtil = new PersistantUtil();
        iPersistanceUtil.persistentMsg(data);
        System.out.println("下面装饰数据库持久化：");
        iPersistanceUtil = new PersistantDBDecorator(iPersistanceUtil);
        iPersistanceUtil.persistentMsg(data);
        System.out.println("下面装饰网络存储器持久化：");
        iPersistanceUtil = new PersistantNetDecorator(iPersistanceUtil);
        iPersistanceUtil.persistentMsg(data);
    }
}
