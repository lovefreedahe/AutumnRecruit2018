package com.wangrupeng.exam.netease2.q3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by WangRupeng on 2018/9/8.
 *
 * @author <a href="http://datacoder.top">王汝鹏</a>
 */
class Node implements Comparable<Node> {
    int x;
    int y;
    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Node node) {
        return this.y - node.y;
    }
}
public class Main {
    public void process(List<Node> list, int count, int m) {
        if (list.size() == 0 || (list.size() == 1 && list.get(0).x == 1)) {
            System.out.println(0);
        }
        if (list.size() == 1 && list.get(0).x != 1) {
            System.out.println(list.get(0).y);
        }
        Collections.sort(list);
        System.out.println(list.get(0).y + list.get(1).y);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List<Node> list = new ArrayList<>(n);
        int count = 0;
        for (int i = 0;i < n;i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            if (x == 1) {
                count++;
            } else {
                list.add(new Node(x, y));
            }
        }
        Main ma = new Main();
        ma.process(list, count, m);
    }
}
