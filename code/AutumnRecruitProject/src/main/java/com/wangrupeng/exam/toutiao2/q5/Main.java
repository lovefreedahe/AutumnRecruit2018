package com.wangrupeng.exam.toutiao2.q5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    private List<Node> list;
    private int M;

    public void process(int x1, int x2) {
        if (x2 < x1) {
            x2 = M + x1;
        }
        list.add(new Node(x1, x2));
    }

    public int resolve() {
        list.sort((node1, node2) -> node2.x1.compareTo(node1.x1));
        Stack<Node> stack = new Stack<>();
        List<Node> result = new ArrayList<>();
        list.forEach(node -> stack.push(node));
        while (stack.size() > 1) {
            Node node1 = stack.pop();
            Node node2 = stack.pop();
            List<Node> mergeList = mergeSingle(node1, node2);
            if (mergeList.size() == 2) {
                stack.push(mergeList.get(1));
            }
            result.add(mergeList.get(0));
        }
        result.add(stack.pop());
        return result.size();
    }

    public List<Node> mergeSingle(Node node1, Node node2) {
        List<Node> result = new ArrayList<>(2);
        if (node1.x2.compareTo(node2.x1) <= 0) {
            result.add(node1);
            result.add(node2);
        } else {
            Integer end;
            if (node1.x2.compareTo(node2.x2) > 0) {
                end = node2.x2;
            } else {
                end = node1.x2;
            }
            result.add(new Node(node1.x1, end));
        }
        return result;
    }


    class Node {
        Integer x1;
        Integer x2;
        private Node(int x1, int x2) {
            this.x1 = x1;
            this.x2 = x2;
        }
        @Override
        public String toString() {
            return x1 + "," + x2;
        }
    }

    public void setList(List<Node> list) {
        this.list = list;
    }

    public void setM(int M) {
        this.M = M;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main m = new Main();
        while (scanner.hasNextInt()) {
            int N = scanner.nextInt();
            m.setList(new ArrayList<>(N));
            int M = scanner.nextInt();
            m.setM(M);
            for (int i = 0;i < N; i++) {
                int x1 = scanner.nextInt();
                int x2 = scanner.nextInt();
                m.process(x1, x2);
            }
            System.out.println(m.resolve() - 1);
        }

    }
}
