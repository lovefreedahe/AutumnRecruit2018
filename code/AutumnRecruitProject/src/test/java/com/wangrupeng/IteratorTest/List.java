package com.wangrupeng.IteratorTest;

public interface List<T> {
    Iterator<T> iterator();
    boolean add(T t);
}
