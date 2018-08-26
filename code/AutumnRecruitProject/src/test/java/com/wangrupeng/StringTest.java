package com.wangrupeng;

import java.util.HashMap;
import java.util.Scanner;

public class StringTest {
    public static void main(String[] args) {
        String test = "helloworlddghfghfjh";
        /*StringBuilder builder = new StringBuilder(test);
        test = builder.reverse().toString();*/
        System.out.println(test);
        System.out.println("worldhello");
        HashMap<String, Integer> map = new HashMap<>(3);
        map.put(test, null);
        int hashCode = hash(test);
        System.out.println(hashCode);
        int index = (4 - 1) & hashCode;
        System.out.println(index);
    }

    static final int hash(Object key) {
        int h;
        int temp = key.hashCode() >>> 16;
        System.out.println(temp);
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
