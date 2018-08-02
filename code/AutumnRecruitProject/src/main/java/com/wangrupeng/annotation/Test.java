package com.wangrupeng.annotation;


import java.util.Arrays;
import java.util.List;

@TestAnnotation
public class Test extends TestParent{

    @Deprecated
    public int testFunction() {
        return 0;
    }

    @Override
    public int function() {
        return 1;
    }

    private void testDeprecatedAnnotation() {
        this.testFunction();
    }

    @SuppressWarnings("deprecation")
    private void testSuppressWariningsAnnotation() {
        this.testFunction();
    }

    @SafeVarargs
    static void safeVarargsTest(List<String>... stringLists) {
        Object[] array = stringLists;
        List<Integer> tmpList = Arrays.asList(42);
        array[0] = tmpList; // Semantically  invalid , but compiles without warnings
        String s = stringLists[0].get(0); //ClassCastException at runtime
    }

}
