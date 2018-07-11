package com.wangrupeng.jianzhioffer.question4;

public class ReplaceSpace {
    public String replace(String str) {
        String result = str.replaceAll(" ", "%20");
        return result;
    }

    public String replaceSpace(StringBuffer str) {
        int length = str.length();
        String sample = "%20";
        for (int i = 0; i < length;i++) {
            if (str.charAt(i) == ' ') {
                str.replace(i, i + 1, sample);
                length += 2;
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        ReplaceSpace replaceSpace = new ReplaceSpace();
        String str = "What a beautiful world!";
        String result = replaceSpace.replace(str);
        System.out.println(result);
        result = replaceSpace.replaceSpace(new StringBuffer(str));
        System.out.println(result);
    }
}