package com.wangrupeng.basic.final_test;

public class FinalTest {
    public static void main(String[] args) {
        FinalTest Final = new FinalTest();
        Final.finalTest();
    }

    public void finalTest() {
        final int v = 1;
        //v = 2;    //v不能被赋值
        final Test test = new Test();
        test.value = 2;
        Test test1 = new Test();
        //test = test1;     //test不能被改变引用
    }

    private class Test {
        public int value = 0;
    }
}
