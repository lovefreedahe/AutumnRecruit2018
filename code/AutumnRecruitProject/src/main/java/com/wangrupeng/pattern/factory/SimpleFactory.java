package com.wangrupeng.pattern.factory;

public class SimpleFactory {
    public static void main(String[] args) {
        Factory factory = new Factory();
        try {
            Apple apple = factory.produce("macbook");
            apple.run();
            apple = factory.produce("iphone");
            apple.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Factory {
    Apple produce(String product) throws Exception{
        if (product.equals("macbook")) {
            return new MacBook();
        } else if (product.equals("iphone")) {
            return new IPhone();
        }
        throw new Exception("No such product!");
    }
}
