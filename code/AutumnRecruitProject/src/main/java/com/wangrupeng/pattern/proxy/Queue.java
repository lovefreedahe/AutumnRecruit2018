package com.wangrupeng.pattern.proxy;

import java.util.Iterator;
import java.util.LinkedList;

public class Queue<T> implements Iterable {
    private LinkedList<T> queue = new LinkedList<T>();
    public void enqueue(T t) {
        queue.offer(t);
    }
    public T dequeue() {
        return queue.poll();
    }
    public T peek() {
        return queue.peek();
    }
    @Override
    public Iterator iterator() {
        return null;
    }
}
