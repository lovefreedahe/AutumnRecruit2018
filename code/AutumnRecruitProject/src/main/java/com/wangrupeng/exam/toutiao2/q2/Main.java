package com.wangrupeng.exam.toutiao2.q2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    private List<Node> list = new ArrayList<>();
    public void process(String line) {
        String[] nodes = line.split(";");
        for (String node:nodes) {
            String[] positions = node.split(",");
            int x1 = Integer.valueOf(positions[0]);
            int x2 = Integer.valueOf(positions[1]);
            list.add(new Node(x1, x2));
        }
    }

    public List<Node> resolve() {
        list.sort((node1, node2) -> node2.x1.compareTo(node1.x1));
        Stack<Node> stack = new Stack<>();
        List<Node> result = new ArrayList<>();
        list.forEach(node -> stack.push(node));
        while (stack.size() > 1) {
            Node node1 = stack.pop();
            Node node2 = stack.pop();
            List<Node> mergeList = mergeSingle(node1, node2);
            if (mergeList.size() == 2) {
                result.add(mergeList.get(0));
            }
            stack.push(mergeList.get(mergeList.size() - 1));
        }
        result.add(stack.pop());
        return result;
    }

    public List<Node> mergeSingle(Node node1, Node node2) {
        List<Node> result = new ArrayList<>(2);
        if (node1.x2.compareTo(node2.x1) < 0) {
            result.add(node1);
            result.add(node2);
        } else {
            Integer end;
            if (node1.x2.compareTo(node2.x2) >= 0) {
                end = node1.x2;
            } else {
                end = node2.x2;
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main m = new Main();
        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            for (int i = 0;i < n;i++) {
                m.process(scanner.next());
            }
            List<Node> result = m.resolve();
            System.out.print(result.get(0).toString());
            for (int i = 1;i < result.size();i++) {
                System.out.print(";");
                System.out.print(result.get(i).toString());
            }
            System.out.println();
        }

    }
}
