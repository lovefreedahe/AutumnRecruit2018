package com.wangrupeng.pattern.Singleton;

public class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance = null;
    private static boolean flag = true;
    private ThreadSafeSingleton() {
        if (!flag) {
            flag = !flag;
        } else {
            throw new RuntimeException("Could not create instance, cause there's already have!");
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
}
