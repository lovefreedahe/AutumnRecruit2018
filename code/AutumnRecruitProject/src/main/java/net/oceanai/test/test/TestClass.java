package net.oceanai.test.test;

import com.wangrupeng.basic.abstract_test.Test;

public class TestClass implements Test {
    @Override
    public int t() {
        return 1;
    }

    @Override
    public double t(double a) {
        return 0;
    }
}
