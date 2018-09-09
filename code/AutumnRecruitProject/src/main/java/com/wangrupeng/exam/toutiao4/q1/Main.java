package com.wangrupeng.exam.toutiao4.q1;

import java.util.Scanner;

public class Main {
    public int process(String str) {
        int length = 0 ;

        if (str == null || str.length()== 0 ) {
            return 0;
        }
        if (str.length() == 1){
            return 1;
        }

        int firstPoint = 0;
        int nextPoint = 0;

        boolean[] exist=new boolean[255];

        while (nextPoint < str.length() && firstPoint <str.length()){
            int currMax = 0;
            int index = str.charAt(nextPoint)-0;
            while (exist[index] == false&&nextPoint<str.length()){
                exist[str.charAt(nextPoint)-0] = true;
                nextPoint++;
                if (nextPoint < str.length()){
                    index = str.charAt(nextPoint)-0;
                }

            }

            currMax = Math.max(currMax,nextPoint-firstPoint);
            firstPoint++;
            nextPoint=firstPoint;
            length = Math.max(length,currMax);
            for (int i = 0 ; i < 255 ; i++)
            {
                exist[i] = false;
            }

        }

        return length;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main m = new Main();
        String str = scanner.nextLine();
        System.out.println(m.process(str));
    }
}
