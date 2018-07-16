package com.wangrupeng.pattern.plyweight;
import com.wangrupeng.pattern.proxy.Stack;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleFlyWeight {
    public static void main(String[] args) {
        FlyweightFactory factory  = new FlyweightFactory();
        IFlyWeight flyWeight1, flyWeight2, flyWeight3, flyWeight4;
        flyWeight1 = factory.getFlyweight("value1");
        flyWeight2 = factory.getFlyweight("value2");
        flyWeight3 = factory.getFlyweight("value3");
        flyWeight4 = factory.getFlyweight("value4");
        flyWeight1.doSomething();
        flyWeight2.doSomething();
        flyWeight3.doSomething();
        flyWeight4.doSomething();
        System.out.println(factory.size());
    }
}

interface IFlyWeight {
    void doSomething();
}

class FlyWeight implements IFlyWeight {
    private String value;
    public FlyWeight(String value){
        this.value = value;
    }

    @Override
    public void doSomething() {
        System.out.println(value);
    }
}

class FlyweightFactory {
    HashMap<String, IFlyWeight> flyweights = new HashMap<String, IFlyWeight>();
    IFlyWeight getFlyweight(String value) {
        IFlyWeight flyWeight = flyweights.get(value);
        if (flyWeight == null) {
            flyWeight = new FlyWeight(value);
            flyweights.put(value, flyWeight);
        }
        return flyWeight;
    }
    public int size() {
        return flyweights.size();
    }
}
