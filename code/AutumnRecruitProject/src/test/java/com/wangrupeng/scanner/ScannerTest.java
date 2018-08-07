package com.wangrupeng.scanner;

import java.util.Scanner;

public class ScannerTest {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder("test ");
        System.out.println(stringBuilder.toString());
        stringBuilder.replace(0,1, "a");
        System.out.println(stringBuilder.toString());
        stringBuilder.delete(0, 1);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        System.out.println(stringBuilder.toString());
    }
}
