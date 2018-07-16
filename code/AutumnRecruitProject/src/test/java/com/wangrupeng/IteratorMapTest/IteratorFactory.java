package com.wangrupeng.IteratorMapTest;

import java.util.Collection;
import java.util.Map;

public class IteratorFactory<T> implements IIteratorFactory<T> {

    @Override
    public IIterator<T> iteratorCollection(Collection<T> collection) {
        return new IteratorCollection<T>(collection);
    }

    @Override
    public IIterator<T> iteratorMap(Map<T, Object> map) {
        return new IteratorMap<T>(map);
    }
}
