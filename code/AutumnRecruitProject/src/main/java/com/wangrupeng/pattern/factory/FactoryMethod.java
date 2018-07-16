package com.wangrupeng.pattern.factory;

public class FactoryMethod {
    public static void main(String[] args) {
        IFactory macbook = new MacFactory();
        macbook.produce().run();
        IFactory phone = new PhoneFactory();
        phone.produce().run();
    }
}

interface IFactory {
    Apple produce();
}

class MacFactory implements IFactory {
    @Override
    public Apple produce() {
        return new MacBook();
    }
}

class PhoneFactory implements IFactory {
    @Override
    public Apple produce() {
        return new IPhone();
    }
}
