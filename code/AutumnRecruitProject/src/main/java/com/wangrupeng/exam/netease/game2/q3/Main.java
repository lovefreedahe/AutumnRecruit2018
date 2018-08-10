package com.wangrupeng.exam.netease.game2.q3;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

class TimeSection {
    Date date1;
    Date date2;
    public TimeSection(Date date1, Date date2) {
        this.date1 = date1;
        this.date2 = date2;
    }
}

public class Main {
    private DateFormat dateFormat = new SimpleDateFormat("dd hh:mm:ss");
    private HashMap<Integer, TimeSection[]> hashMap = new HashMap<>(7);
    public void process(int week, int numbers, String[] time) throws ParseException {
        TimeSection[] timeSections = new TimeSection[numbers];
        for (int i = 0;i < numbers; i++) {
            String[] dates = time[i].split("-");
            TimeSection timeSection = new TimeSection(dateFormat.parse(week + " " + dates[0]), dateFormat.parse(week + " " + dates[1]));
            timeSections[i] = timeSection;
        }
        hashMap.put(week, timeSections);
    }

    public void search(int week, String time) throws ParseException{
        Date date = dateFormat.parse(week + " " + time);
        TimeSection[] timeSections;
        String result = "";
        if (hashMap.containsKey(week)) {
            timeSections = hashMap.get(week);
            for (TimeSection timeSection : timeSections) {
                long time1 = diffTime(date, timeSection.date1);
                long time2 = diffTime(date, timeSection.date2);
                if (time1 < 0) {
                    result = -time1 + "";
                    break;
                } else if (time1 >= 0 && time2 <= 0) {
                    result = "0";
                    break;
                }
            }
            if (result.equals("")) {
                week++;
                while (true) {
                    if (hashMap.containsKey(week% 7)) {
                        long time1 = diffTime(date, hashMap.get(week%7)[0].date1);
                        result = -time1 + "";
                        break;
                    } else {
                        week++;
                    }
                }

                if (result.contains("-")){
                    result = (int)(604800 - Double.valueOf(result.substring(1, result.length() - 1))) + "";
                }
            }
        } else {
            week++;
            while (true) {
                if (hashMap.containsKey(week% 7)) {
                    long time1 = diffTime(date, hashMap.get(week%7)[0].date1);
                    result = -time1 + "";
                    break;
                } else {
                    week++;
                }
            }

            if (result.contains("-")){
                result = (int)(604800 - Double.valueOf(result.substring(1, result.length() - 1))) + "";
            }
        }
        System.out.println(result);
    }

    public void clearHashMap() {
        hashMap.clear();
    }

    private long diffTime(Date date1, Date date2) {
        return  (date1.getTime() - date2.getTime()) / 1000;
    }

    public static void main(String[] args) throws ParseException{
        Scanner scanner = new Scanner(System.in);
        Main m = new Main();
        int T = scanner.nextInt();
        int K, W, M;
        String[] time;
        //System.out.println(T);
        for (int i = 0;i < T;i++) {
            m.clearHashMap();
            K = scanner.nextInt();
            for (int j = 0;j < K;j++) {
                W = scanner.nextInt();
                M = scanner.nextInt();
                time = new String[M];
                for (int n = 0;n < M;n++) {
                    time[n] = scanner.next();
                }
                m.process(W, M, time);
            }

            int C = scanner.nextInt();
            int week;
            String search_time;
            for (int j = 0;j < C;j++) {
                week = scanner.nextInt();
                search_time = scanner.next();
                m.search(week, search_time);
            }
        }
    }
}
