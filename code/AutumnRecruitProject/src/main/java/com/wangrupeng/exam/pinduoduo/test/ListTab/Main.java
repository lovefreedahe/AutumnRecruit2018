package com.wangrupeng.exam.pinduoduo.test.ListTab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by WangRupeng on 2018/8/5.
 *
 * @author <a href="http://datacoder.top">王汝鹏</a>
 */
public class Main {

    public void read() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            //System.out.println(str);
            String[] array = str.split(" ");
            if (array.length != 4) {
                return;
            }
            int[] value = new int[4];
            for (int i = 0;i < 4;i++) {
                value[i] = Integer.valueOf(array[i]);
            }
            process(value);
        }
    }

    private void process(int[] array) {
        int offset = array[0];
        int n = array[1];
        int L1 = array[2];
        int L2 = array[3];
        int start1, end1, start2, end2;
        if (offset < L1) {
            start1 = offset;
            start2 = 0;
            if (n >= (L1 - offset)) {
                end1 = L1;
                end2 = n + offset - L1;
            } else {
                end1 = offset + n;
                end2 = 0;
            }
        } else if (offset >= L1 && offset < L2 + L1){
            start1 = L1;
            end1 = L1;
            start2 = offset - L1;
            if (n < L1 + L2 - offset) {
                end2 = offset - L1 + n;
            } else {
                end2 = L2;
            }
        } else {
            start1 = L1;
            end1 = L1;
            start2 = L2;
            end2 = L2;
        }

        System.out.print(start1 + " ");
        System.out.print(end1 + " ");
        System.out.print(start2 + " ");
        System.out.print(end2);
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.read();
    }
}
