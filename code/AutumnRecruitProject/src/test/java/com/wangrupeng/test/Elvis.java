package com.wangrupeng.test;

public enum Elvis {
    INSTANCE;
    private String name;
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void leaveTheBuilding() {

    }
}
