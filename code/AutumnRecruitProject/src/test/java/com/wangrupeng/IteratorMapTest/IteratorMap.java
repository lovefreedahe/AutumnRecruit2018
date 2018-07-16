package com.wangrupeng.IteratorMapTest;

import java.util.Iterator;
import java.util.Map;

public class IteratorMap<T> implements IIterator<T> {
    Iterator<Map.Entry<T, Object>> iterator;
    public IteratorMap(Map<T, Object> map) {
        iterator = map.entrySet().iterator();
    }
    @Override
    public Object next() {
        return iterator.next().getValue();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }
}
