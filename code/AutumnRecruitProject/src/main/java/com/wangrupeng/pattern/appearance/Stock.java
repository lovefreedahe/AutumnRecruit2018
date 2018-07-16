package com.wangrupeng.pattern.appearance;

import java.util.Random;

public class Stock {
    boolean hasStock(String product) {
        return new Random().nextInt(Math.abs(product.hashCode())) > 0; //模拟是否有库存
    }
}
