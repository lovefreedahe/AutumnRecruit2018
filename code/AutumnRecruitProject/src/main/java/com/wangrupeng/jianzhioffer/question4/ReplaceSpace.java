package com.wangrupeng.jianzhioffer.question4;

public class ReplaceSpace {
    public String replace(String str) {
        String result = str.replaceAll(" ", "%20");
        return result;
    }

    public static void main(String[] args) {
        ReplaceSpace replaceSpace = new ReplaceSpace();
        String str = "What a beautiful world!";
        String result = replaceSpace.replace(str);
        System.out.println(result);
    }
}