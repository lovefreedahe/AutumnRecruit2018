package com.wangrupeng.pattern.appearance;

public class Postage {
    int getPostage(String address) {
        return Math.abs(address.hashCode())%20 + 6; //模拟邮费计算
    }
}
