package com.wangrupeng.pattern.visitor;

public class MultiDispatch {
    public static void main(String[] args) {
        Child child = new Child();
        child.print();
        child.print(new Visitor2());
    }
}

class Father{
    void print(){
        System.out.println("Father");
    }
}

class Child extends Father {
    void print() {
        System.out.println("Child");
    }
    void print(Visitor2 visitor) {
        visitor.print(this);
    }
}

class Visitor2 {
    public void print(Child child) {
        child.print();
    }
}
