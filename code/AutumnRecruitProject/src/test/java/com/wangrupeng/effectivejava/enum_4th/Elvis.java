package com.wangrupeng.effectivejava.enum_4th;

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
