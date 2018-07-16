package com.wangrupeng.pattern.appearance;

public class ProductPrice {
    int getPrice(String product) {
        return Math.abs(product.hashCode()); //模拟获取商品价格
    }
}
