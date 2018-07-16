package com.wangrupeng.pattern.iterator;

public interface MyIterator<T> {
    boolean hasNext();  //是否还有下一个元素
    T next();           //得到下一个元素
    T remove();
}
