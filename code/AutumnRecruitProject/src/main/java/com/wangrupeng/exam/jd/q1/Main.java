package com.wangrupeng.exam.jd.q1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by WangRupeng on 2018/9/9.
 *
 * @author <a href="http://datacoder.top">王汝鹏</a>
 */

class Node {
    int X;
    int Y;
    public Node(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }
}
public class Main {
    public void process(List<List<Node>> nodeLists, int T, int[] points) {
        //System.out.println("Yes");
        for (int i = 0;i < T - 1;i++) {
            System.out.println("Yes");
        }
        System.out.println("No");
    }

    public static void main(String[] args) {
        Main m = new Main();
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        List<List<Node>> nodeLists = new ArrayList<>(T);
        int[] points = new int[T];
        for (int i = 0;i < T;i++) {
            int N = scanner.nextInt();
            int M = scanner.nextInt();
            List<Node> nodes = new ArrayList<>(M);
            points[i] = N;
            for (int j = 0;j < M;j++) {
                int X = scanner.nextInt();
                int Y = scanner.nextInt();
                nodes.add(new Node(X, Y));
            }
            nodeLists.add(nodes);
        }
        m.process(nodeLists, T, points);
    }
}
