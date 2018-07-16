package com.wangrupeng.IteratorTest;

public class ArrayList<T> implements List<T> {
    private int size;
    private Object[] defaultList;
    private static final int defaultLength = 10;
    public ArrayList() {
        defaultList = new Object[defaultLength];
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    @Override
    public boolean add(T t) {
        if (size <= defaultLength) {
            defaultList[size++] = t;
            return true;
        }
        return false;
    }


    private class MyIterator implements Iterator<T> {
        private int next;
        @Override
        public boolean hasNext() {
            return next < size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            return (T) defaultList[next++];
        }
    }
}
