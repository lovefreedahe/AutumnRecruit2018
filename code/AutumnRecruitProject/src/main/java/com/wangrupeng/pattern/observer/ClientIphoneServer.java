package com.wangrupeng.pattern.observer;

public class ClientIphoneServer implements Client{
    private static String name = "iphone service";
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
