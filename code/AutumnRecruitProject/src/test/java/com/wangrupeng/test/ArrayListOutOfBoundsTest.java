package com.wangrupeng.test;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class ArrayListOutOfBoundsTest {
    public static void main(String[] args) {
        /*ArrayList<String> stringArrayList = new ArrayList<>(5);

        for (int i = 0;i < 5; ++i) {
            stringArrayList.add("test" + i);
        }
        stringArrayList.add(5, "test5");
        Iterator iterator = stringArrayList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }*/

        HashSet<Test> hashSet = new HashSet<>();
        Test test = new Test("test");
        System.out.println(test.hashCode());
        hashSet.add(test);
        test.setTest("test1");
        System.out.println(test.hashCode());
        Iterator iterator1 = hashSet.iterator();
        while (iterator1.hasNext()) {
            Test t = (Test)iterator1.next();
            System.out.println(t.test);
        }
    }

    public static class Test {
        String test;
        public Test(String test) {
            this.test = test;
        }
        public void setTest(String t) {
            this.test = t;
        }
    }
}
