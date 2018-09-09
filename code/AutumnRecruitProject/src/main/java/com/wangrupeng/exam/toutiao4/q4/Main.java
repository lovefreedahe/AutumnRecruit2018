package com.wangrupeng.exam.toutiao4.q4;

import java.util.Scanner;

public class Main {
    public void process() {

    }
    public static boolean isValidUTF8(int[] array) {
            for (int i = 0; i < array.length; ++i) {
                if (array[i] < 0b10000000) {
                    continue;
                } else {
                    int cnt = 0, val = array[i];
                    for (int j = 7; j >= 1; --j) {
                        if (val >= Math.pow(2, j)) ++cnt;
                        else break;
                        val -= Math.pow(2, j);
                    }
                    if (cnt == 1) return false;
                    for (int j = i + 1; j < i + cnt; ++j) {
                        if (array[j] > 0b10111111 || array[j] < 0b10000000) return false;
                    }
                    i += cnt - 1;
                }
            }
            return true;
        }

    public static int judge(int n) {
        if ((128 & n) == 0)
            return -1;
        if ((192 & n) == 128)//10
            return 0;
        if ((224 & n) == 192)
            return 1;
        if ((240 & n) == 224)
            return 2;
        if ((248 & n) == 240)
            return 3;
        return -2;
    }

    public static boolean isValidUTF8Code(int[] data) {
        boolean isUtf8 = false;// 当前num是否在一个验证的字符中
        int times = 0;
        for (int i = 0; i < data.length; i++) {
            if(isUtf8==false){//当前数不在某字符编码内
                times = judge(data[i]);//当前编码需要多少个10
                if (times == -2 || times == 0)//非编码
                    return false;
                if(times>0)//后面需要跟多少个10编码
                    isUtf8=true;
            }else{
                if(times>0&&judge(data[i])==0)//是10编码
                    times--;
                else
                    return false;
                if(times==0)
                    isUtf8=false;
            }
        }
        return times>0?false:true;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] array = new int[N];
        for (int i = 0;i < N;i++) {
            array[i] = scanner.nextInt();
        }
        if (isValidUTF8Code(array)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
