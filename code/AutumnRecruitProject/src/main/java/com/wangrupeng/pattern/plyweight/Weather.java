package com.wangrupeng.pattern.plyweight;

public class Weather implements IWeather {
    private String weather;
    private Integer temperature;
    public Weather(String weather, int temperature) {
        this.temperature = temperature;
        this.weather = weather;
    }

    @Override
    public void printWeather() {
        System.out.println("Weather:" + weather);
        System.out.println("Temperature:" + temperature);
    }

    @Override
    public boolean equals(Object obj) {
        Weather weatherTmp = (Weather)obj;
        return weatherTmp.weather.equals(this.weather) && weatherTmp.temperature == this.temperature;
    }

    @Override
    public int hashCode() {
        return (weather.hashCode() + temperature.hashCode())/2;
    }
}
