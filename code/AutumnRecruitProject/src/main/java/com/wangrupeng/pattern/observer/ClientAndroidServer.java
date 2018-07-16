package com.wangrupeng.pattern.observer;

public class ClientAndroidServer implements Client {
    private static String name = "Android Service";
    private WeatherInfo info;
    @Override
    public void getWeather(WeatherInfo info) {
        this.info = info;
        dealMsg();
    }
    private void dealMsg() {
        System.out.println(name + "收到天气：time=" + info.getTime() + "天气=" + info.getWeather());
    }
}
