package com.wangrupeng.jianzhioffer.question2;

import java.lang.reflect.Constructor;

public class ReflectionSingletonTest {
    public static void main(String[] args) {
        com.wangrupeng.jianzhioffer.question_2.BillPughSingleton instanceOne = com.wangrupeng.jianzhioffer.question_2.BillPughSingleton.getInstance();
        com.wangrupeng.jianzhioffer.question_2.BillPughSingleton instanceTwo = null;
        try {
            Constructor[] constructors = com.wangrupeng.jianzhioffer.question_2.BillPughSingleton.class.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                constructor.setAccessible(true);
                instanceTwo = (com.wangrupeng.jianzhioffer.question_2.BillPughSingleton)constructor.newInstance();
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(instanceOne.hashCode());
        System.out.println(instanceTwo.hashCode());
    }
}
