package com.wangrupeng.pattern.appearance;

public class TestUse {
    public static void main(String[] args) {
        Object info = ProductSalesman.INSTANCE.buySomething("书", "华科", "K123456");
        System.out.println(info);
    }
}
