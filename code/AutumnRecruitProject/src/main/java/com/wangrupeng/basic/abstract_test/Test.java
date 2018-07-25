package com.wangrupeng.basic.abstract_test;

public interface Test {
    default int t(){
        return 1;
    };
    double t(double a);
}
