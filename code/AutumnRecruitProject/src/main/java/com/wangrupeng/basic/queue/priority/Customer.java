package com.wangrupeng.basic.queue.priority;

import com.wangrupeng.pattern.adapter.ObjectAdapter;

import java.util.Comparator;

public class Customer implements Comparable{
    private int id;
    private String name;
    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Object object) {
        Customer customer = (Customer)object;
        return this.id - customer.getId();
    }

}
