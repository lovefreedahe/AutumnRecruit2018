package com.wangrupeng.netease;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameTest {
    public static void main(String[] args) throws Exception {
        //Date date;
        DateFormat dateFormat = new SimpleDateFormat("dd hh:mm:ss");
        Date date = dateFormat.parse("0 10:00:00");
        System.out.println(date);
        Date date1 = dateFormat.parse("7 10:00:00");
        System.out.println(date1);
        long result = (date1.getTime() - date.getTime()) / 1000;
        result = -result;
        String test = result + "";
        System.out.println(result + "");
        if (test.contains("-")) {
            test = (int)(604800 - Double.valueOf(test.substring(1, test.length() - 1))) + "";
        }
        System.out.println(test);
    }
}
