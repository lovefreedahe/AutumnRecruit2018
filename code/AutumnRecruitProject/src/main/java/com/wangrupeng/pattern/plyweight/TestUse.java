package com.wangrupeng.pattern.plyweight;

public class TestUse {
    public static void main(String[] args) {
        WeatherFactory factory = new WeatherFactory();
        IWeather weather1, weather2, weather3, weather4;
        weather1 = factory.getFlyWeight("Cloudy", 28);
        weather2 = factory.getFlyWeight("Rainy", 25);
        weather3 = factory.getFlyWeight("Cloudy", 27);
        weather4 = factory.getFlyWeight("Cloudy", 28);
        weather1.printWeather();
        weather2.printWeather();
        weather3.printWeather();
        weather4.printWeather();
        System.out.println("Number of objects is " + factory.getFlyweightSize());
    }
}
