package com.wangrupeng.practice.design_pattern.builder;

/**
 * builder模式适用于有多个参数的时候，省的创建多个多参数构造函数
 */
public class Student {
    private int age = 18;
    private boolean male = false;
    private int height = 175;
    private String className = "";
    private static class Builder {
        private int age = 18;
        private boolean male = false;
        private int height = 175;
        private String className = "";
        public Builder setAge(int age) {
            this.age = age;
            return this;
        }
        public Builder setMale(boolean male) {
            this.male = male;
            return this;
        }
        public Builder setHeight(int height) {
            this.height = height;
            return this;
        }
        public Builder setClassName(String className) {
            this.className = className;
            return this;
        }
        public Student build() {
            Student student = new Student();
            student.age = this.age;
            student.male = this.male;
            student.height = this.height;
            student.className = this.className;
            return student;
        }
    }

    @Override
    public String toString() {
        return "Age:" + age + ", male:" + male + ", height:" + height + ",className:" + className;
    }

    public static void main(String[] args) {
        Student student = new Builder().setAge(23).setMale(true).setHeight(175).setClassName("1709").build();
        System.out.println(student);
    }
}
