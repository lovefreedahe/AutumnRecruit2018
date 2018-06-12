package com.wangrupeng.test;

import com.wangrupeng.effectivejava.enum_4th.Elvis;
import com.wangrupeng.utils.MapUtils;

import java.util.List;
import java.util.Map;

public class StaticFactoryTest {
    public static void main(String[] args) {
        Map<String, List<Integer>> map = MapUtils.newInstance();
        //EnumSet
        Elvis elvis = Elvis.INSTANCE;
        System.out.println(Elvis.INSTANCE);
        elvis.setName("Wang");
        System.out.println(elvis.getName());
        System.out.println(Elvis.INSTANCE.ordinal());
    }
}
