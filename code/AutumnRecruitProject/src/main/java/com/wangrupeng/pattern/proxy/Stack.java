package com.wangrupeng.pattern.proxy;

import java.util.Iterator;
import java.util.LinkedList;

public class Stack<T> implements Iterable {
    private LinkedList<T> stack = new LinkedList<T>();
    public T pop() {
        return stack.pop();
    }
    public T peek() {
        return stack.peek();
    }
    public void push(T t) {
        stack.push(t);
    }

    @Override
    public Iterator iterator() {
        return stack.iterator();
    }
}
