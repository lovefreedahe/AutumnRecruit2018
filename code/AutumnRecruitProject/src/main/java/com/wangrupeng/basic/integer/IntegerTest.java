package com.wangrupeng.basic.integer;

import java.util.HashMap;

public class IntegerTest {
    public static void main(String[] args) {
        int a = 10;
        Integer integer0 = 10;
        System.out.println(a == integer0); //true


        Integer integer1 = 10;
        System.out.println(integer0 == integer1); //true

        //非new生成的Integer0变量指向的是常量池中的对象，new生成的Integer2指向堆中的对象
        Integer integer2 = new Integer(2);
        System.out.println(integer0 == integer2); // false

        //引用不同
        Integer integer3 = new Integer(3);
        System.out.println(integer2 == integer3); //false
        System.out.println(integer2.intValue() == integer3.intValue()); //false

        //自动拆箱会调用valueOf函数，Integer会将-128~127范围的int值进行缓存
        Integer integer4 = 255;
        Integer integer5 = 255;
        System.out.println(integer4 == integer5);
        integer4 = 124;
        integer5 = 124;
        System.out.println(integer4 == integer5);

        //HashTable<Integer, String> hashTable = new HashTable<>();
        HashMap<Integer, String> hashMap = new HashMap<>();
    }
}
