package com.wangrupeng.exam.xiecheng.q2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Record {
    int number;
    int start;
    int end;
    Record(int number, int start, int end) {
        this.number = number;
        this.start = start;
        this.end = end;
    }
}

public class Main {
    void process(int N, int search, Record[] records) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0;i < N;i++) {
            if (search>= records[i].start && search <= records[i].end) {
                arrayList.add(records[i].number);
            }
        }
        if (arrayList.size() == 0) {
            System.out.println("null");
        } else {
            int[] results = new int[arrayList.size()];
            for (int i = 0;i < arrayList.size();i++) {
                results[i] = arrayList.get(i);
            }
            Arrays.sort(results);
            for (int i: results) {
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int search = scanner.nextInt();
        Record[] records = new Record[N];
        for (int i = 0;i <N;i++) {
            int number = scanner.nextInt();
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            records[i] = new Record(number, start, end);
        }
        m.process(N, search, records);
    }

}
