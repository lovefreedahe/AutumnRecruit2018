package com.wangrupeng.basic;

class Father {
    private String name;
    public void func1() {
        System.out.println("Father:func1");
    }
    public void func2() {
        System.out.println("Father:func2");
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class JavaFengzhuang extends Father {
    private int age;
    private String name;
    public void func1(int a) {
        System.out.println("Son:func1");
    }

    public void func2() {
        System.out.println("Son:func2");
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Father father = new JavaFengzhuang();
        father.setName("Test");
        System.out.println(father.getName());
        father.func2();
        father.func1();
        ((JavaFengzhuang) father).func1(1);
    }
}
