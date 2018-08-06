package com.wangrupeng.exam.pinduoduo.test.triangle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by WangRupeng on 2018/8/5.
 *
 * @author <a href="http://datacoder.top">王汝鹏</a>
 */
public class Main {

    private static class Point {
        public int x;
        public int y;
        public Point() {
            x = 0;
            y = 0;
        }
    }

    public void process(int n, Point[] points) throws IOException{

        int number = n * (n - 1) * (n - 2) / 6;
        int counts = 0;
        for (int i = 0; i < n;i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if ((points[k].x - points[j].x) == (points[j].x - points[i].x) && (points[j].x - points[i].x) == 0) {
                        counts++;
                    } else if ((points[j].x - points[i].x) == 0 ){
                        if ((points[j].y - points[i].y) == 0 ) {
                            counts++;
                        } else {
                            continue;
                        }
                    }/* else  if((points[k].x - points[j].x) == 0) {
                        if ((points[k].y - points[j].y) == 0) {
                            continue;
                        } else {
                            counts++;
                        }
                    }else  if((points[k].x - points[i].x) == 0) {
                        if ((points[k].y - points[i].y) == 0) {
                            continue;
                        } else {
                            counts++;
                        }
                    }*/ else if ((points[k].y - points[j].y) / (points[k].x - points[j].x) == (points[j].y - points[i].y) / (points[j].x - points[i].x)) {
                        counts++;
                    }

                    if (k == n - 1 && j == n - 2 && (points[k].x - points[i].x) == 0 && (points[k].y - points[i].y) == 0) {
                        counts++;

                    }
                }
            }
        }
        System.out.println(number - counts);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String str;
        Main m = new Main();
        int n;
        while ((str = bufferedReader.readLine()) != null) {
            n = Integer.valueOf(str);
            Point[] points = new Point[n];
            for (int i = 0; i < n; ++i) {
                str = bufferedReader.readLine();
                String[] strings = str.split(" ");
                points[i] = new Point();
                points[i].x = Integer.valueOf(strings[0]);
                points[i].y = Integer.valueOf(strings[1]);
            }
            m.process(n, points);
        }
    }
}
