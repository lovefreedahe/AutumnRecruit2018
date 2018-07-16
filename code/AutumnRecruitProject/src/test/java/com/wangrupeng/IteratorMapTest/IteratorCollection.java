package com.wangrupeng.IteratorMapTest;

import java.util.Collection;
import java.util.Iterator;

public class IteratorCollection<T> implements IIterator<T> {
    Iterator<T> iterator;
    public IteratorCollection(Collection<T> collection) {
        iterator = collection.iterator();
    }
    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public T next() {
        return iterator.next();
    }


}
