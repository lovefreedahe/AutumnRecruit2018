package com.wangrupeng.pattern.abstract_factory;


public class AbstractoryFactory {
    public static void main(String[] args) {
        IFactory bigfactory = new BigFactory();
        IFactory smallfactory = new SmallFactory();
        bigfactory.produceApple().run();
        bigfactory.produceHeadset().play();
        smallfactory.produceApple().run();
        smallfactory.produceHeadset().play();
    }
}

interface IFactory {
    Apple produceApple() ;
    HeadSet produceHeadset();
}

class BigFactory implements IFactory {
    @Override
    public Apple produceApple() {
        return new MacBook();
    }

    @Override
    public HeadSet produceHeadset() {
        return new Sony();
    }
}

class SmallFactory implements IFactory {
    @Override
    public Apple produceApple() {
        return new IPhone();
    }

    @Override
    public HeadSet produceHeadset() {
        return new Beats();
    }
}