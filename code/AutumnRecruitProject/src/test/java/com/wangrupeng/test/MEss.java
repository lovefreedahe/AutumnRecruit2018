package com.wangrupeng.test;

public class MEss {

    public int count(int n, int k) {
        int count = 0;
        for (int x = 1;x <= n; ++x) {
            for (int y = 1;y <= n;++y) {
                if (x % y >= k) {
                    ++count;
                }
            }
        }
        return count;
    }
    public static void main(String[] args) {

    }
}
