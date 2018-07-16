package com.wangrupeng.pattern.appearance;

public class Discount {
    int getDiscount(String discountCode) {
        return Math.abs(discountCode.hashCode()) % 3;
    }
}
