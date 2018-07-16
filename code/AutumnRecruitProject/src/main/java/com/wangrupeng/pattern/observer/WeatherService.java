package com.wangrupeng.pattern.observer;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public enum WeatherService implements IWeatherService {
    INSTANCE;
    private LinkedList<WeatherInfo> weatherInfos = new LinkedList<WeatherInfo>();
    private LinkedHashSet<Client> clients = new LinkedHashSet<Client>(); //存放观察者
    @Override
    public void addClient(Client client) {
        clients.add(client);
    }

    @Override
    public boolean deleteClient(Client client) {
        return clients.remove(client);
    }

    @Override
    public void notifyClients() {
        Iterator<Client> iterator = clients.iterator();
        while (iterator.hasNext()) {
            iterator.next().getWeather(weatherInfos.peekFirst());
        }

    }

    @Override
    public void updateWeather(WeatherInfo info) {
        if (weatherInfos.size() > 0) {
            if (weatherInfos.peekFirst().equals(info)) {
                return;
            }
        }
        weatherInfos.push(info);
        if (clients.size() == 0) {
            return;
        }
        notifyClients();
    }
}
