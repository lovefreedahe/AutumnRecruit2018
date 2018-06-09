package com.wangrupeng.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUtils {
    public static <K,V>HashMap<K,V> newInstance() {
        return new HashMap<K,V>();
    }

    public static void main(String[] args) {
        Map<String, List<String>> map = MapUtils.newInstance();
    }
}
