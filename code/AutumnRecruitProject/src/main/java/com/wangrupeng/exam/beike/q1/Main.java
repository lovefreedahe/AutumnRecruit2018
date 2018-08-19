package com.wangrupeng.exam.beike.q1;

import java.util.Scanner;

public class Main {
    private int T1;
    private int T2;
    private int P1;
    private int P2;
    private int P3;
    static class TimeRange {
        int start;
        int end;
        public TimeRange(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public Main(int P1, int P2, int P3, int T1, int T2) {
        this.P1 = P1;
        this.P2 = P2;
        this.P3 = P3;
        this.T1 = T1;
        this.T2 = T2;
    }
    public void process(TimeRange[] timeRanges, int n) {
        TimeRange next = null;
        TimeRange current = null;
        int count = 0;
        for (int i = 0;i < n;i++) {
            current = timeRanges[i];
            count += (current.end - current.start) * P1;
            if (i + 1 < n) {
                next = timeRanges[i+1];
            }
            if (next != null) {
                count += judge(current.end, next.start);
            }
            next = null;
        }
        System.out.println(count);
    }

    public int judge(int start, int end) {
        int range = end - start;
        if (range <= T1) {
            return this.P1 * range;
        } else if (range > T1 && range <= T2 + T1) {
            return this.P1 * T1 + this.P2 * (range - T1);
        } else {
            return this.P1 * T1 + this.P2 * T2 + this.P3 * (range - T1 - T2);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int P1 = scanner.nextInt();
        int P2 = scanner.nextInt();
        int P3 = scanner.nextInt();
        int T1 = scanner.nextInt();
        int T2 = scanner.nextInt();
        Main m = new Main(P1, P2, P3, T1, T2);
        TimeRange[] timeRanges = new TimeRange[n];
        for (int i = 0;i < n;i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            timeRanges[i] = new TimeRange(start, end);
        }
        m.process(timeRanges, n);
    }
}
