package com.wangrupeng;

public class regx {
    public static void main(String[] args) {
        String te = "typedef int INT; typedef INT** INTP;";
        te = te.replaceAll("\\*", "");//将三个*一起转换成abc
        System.out.println(te);
    }
}
