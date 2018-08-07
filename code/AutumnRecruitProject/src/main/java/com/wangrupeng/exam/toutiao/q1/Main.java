package com.wangrupeng.exam.toutiao.q1;

import java.util.*;

public class Main {

    static class Node implements Comparable<Node>{
        public int x;
        public int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node node) {
            return this.x - node.x;
        }
    }

    public void process(int[] arrayX, int[] arrayY, int N) {
        TreeSet<Node> treeSet = new TreeSet<>();
        boolean hasBiggerNumber = false;
        for (int i = 0;i < N;i++) {
            for (int j = 0;j < N;j++) {
                if (arrayX[i] < arrayX[j] && arrayY[i] < arrayY[j]) {
                    hasBiggerNumber = true;
                    break;
                }
            }

            if (!hasBiggerNumber) {
                treeSet.add(new Node(arrayX[i], arrayY[i]));
                //list.add(new Node(arrayX[i], arrayY[i]));
            } else {
                hasBiggerNumber = false;
            }
        }
        int size = treeSet.size();
        for (int i = 0;i < size;i++) {
            Node node = treeSet.pollFirst();
            System.out.println(node.x + " " + node.y);
        }
    }

    public void process(Node[] nodes, int N) {
        boolean hasBiggerNumber = false;
        for (int i = 0;i < N;i++) {
            for (int j = i + 1;j < N;j++) {
                if (nodes[i].y < nodes[j].y) {
                    hasBiggerNumber = true;
                    break;
                }
            }
            if (!hasBiggerNumber) {
                System.out.println(nodes[i].x + " " + nodes[i].y);
            } else {
                hasBiggerNumber = false;
            }
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        if (N < 1 || N > 500000) {
            return;
        }
        Node[] nodes = new Node[N];
        for (int i = 0;i < N;i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            nodes[i] = new Node(x, y);
        }
        Arrays.sort(nodes);
        m.process(nodes, N);
    }
}
