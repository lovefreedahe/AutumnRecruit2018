package com.wangrupeng.IteratorMapTest;

import java.util.Collection;
import java.util.Map;

public interface IIteratorFactory<T> {
    IIterator<T> iteratorMap(Map<T, Object> map);
    IIterator<T> iteratorCollection(Collection<T> collection);
}
