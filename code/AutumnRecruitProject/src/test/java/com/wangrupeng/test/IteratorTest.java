package com.wangrupeng.test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorTest {
    public static void main(String[] args) {
        List<Number> numbers = new ArrayList<>();
        for (int i = 0;i < 4;i++) {
            numbers.add(new Number(i));
        }
        for (Number i : numbers) {
            System.out.println(i.getValue());
        }
        Iterator<Number> numberIterator = numbers.iterator();
        while (numberIterator.hasNext()) {
            numberIterator.next().setValue(5);
        }

        Iterator<Number> numberIterator2 = numbers.iterator();
        while (numberIterator2.hasNext()) {
            Number number = numberIterator2.next();
            number = new Number(10);
        }

        System.out.println("After:");
        for (Number number : numbers) {
            System.out.println(number.getValue());
        }
    }


}
class Number {
    private int value;
    public Number(int value) {
        this.value = value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public int getValue() {
        return this.value;
    }
}