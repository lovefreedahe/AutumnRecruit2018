package com.wangrupeng.jianzhioffer.question4;

/**
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
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