package com.wangrupeng.practice.design_pattern.singleton;

public class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance = null;
    private static boolean flag = false;
    private ThreadSafeSingleton() {
        if (!flag) {
            flag = !flag;
        }else {
            throw new RuntimeException("ThreadSafeSingleton already have a instance!");
        }
    }
    public static ThreadSafeSingleton getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        ThreadSafeSingleton instance = ThreadSafeSingleton.getInstance();
    }
}
