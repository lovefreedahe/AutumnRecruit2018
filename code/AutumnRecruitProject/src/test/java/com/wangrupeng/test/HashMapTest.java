package com.wangrupeng.test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap<Integer, String> hashMap = new HashMap<>();

        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        ConcurrentHashMap<Integer, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put(new Integer(1), "test");
        Hashtable<Integer, String> hashtable = new Hashtable<>();

        hashtable.put(12, "");
        String str = "1231";
        System.out.println(str.hashCode() >>> 16);
        int h = hash(str);
        System.out.println(hash(str));
        ArrayList<Integer> test = new ArrayList<>();
        HashSet<Integer> hashSet = new HashSet<>();
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
