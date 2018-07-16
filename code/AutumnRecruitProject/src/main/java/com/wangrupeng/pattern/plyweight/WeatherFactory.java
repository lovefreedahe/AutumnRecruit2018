package com.wangrupeng.pattern.plyweight;

import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;

import java.util.HashMap;

public class WeatherFactory {
    private HashMap<IWeather, IWeather> weathers;
    public WeatherFactory() {
        weathers = new HashMap<IWeather, IWeather>();
    }
    public IWeather getFlyWeight(String weather, int templature) {
        Weather objectWeather = new Weather(weather, templature);
        IWeather flyweight = weathers.get(objectWeather);
        if (flyweight == null) {
            flyweight = objectWeather;
            weathers.put(objectWeather, flyweight);
        }else {
            objectWeather = null; // to gc
        }
        return flyweight;
    }
    public int getFlyweightSize() {
        return weathers.size();
    }
}
