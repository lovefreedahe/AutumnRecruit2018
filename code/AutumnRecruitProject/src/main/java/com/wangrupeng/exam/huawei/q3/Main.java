package com.wangrupeng.exam.huawei.q3;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ListNode {
    private String value;
    private ListNode next;
    public ListNode(){}
    public ListNode(String value) {

    }

    public ListNode getLast() {
        ListNode nextTemp = this;
        while (nextTemp.next != null) {
            nextTemp = nextTemp.next;
        }
        return nextTemp;
    }
}

public class Main {
    public void process(String defineStr, String type) {
        Pattern pattern = Pattern.compile("int|double|float|char|long|bool|void|wchar_t|short");
        Matcher matcher = pattern.matcher(defineStr);
        String basic = "";
        int position = 0;
        if (matcher.find()) {
            basic = matcher.group();
        }

        Pattern patternType = Pattern.compile(type);
        matcher = patternType.matcher(defineStr);
        if (matcher.find()) {
            position = matcher.start();
        }
        String temp = defineStr.substring(0, position);
        int countPointer = countPointer(temp);
        System.out.print(basic);
        for (int i = 0;i < countPointer;i++) {
            System.out.print(" ");
            System.out.print('*');
        }
    }

    private int countPointer(String words) {
        int count = 0;
        for (int i = words.length() - 1;i >= 0;i--) {
            if (words.charAt(i) == '*') {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main m = new Main();
        String defineStr;
        String type;
        defineStr = scanner.nextLine();
        type = scanner.next();
        m.process(defineStr, type);

    }
}
