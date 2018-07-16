package com.wangrupeng.pattern.prototype;

//使用 java 自带的支持
public class APITestUse {
    public static void main(String args[]) throws CloneNotSupportedException{
        MyObject myObject = new MyObject();
        myObject.i = 500;
        MyObject myObjectClone = (MyObject) myObject.clone();
        System.out.println(myObjectClone.i);
    }
}
//一个可以复制的对象
class MyObject implements Cloneable{
    int i;
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}//结果会输出 500
