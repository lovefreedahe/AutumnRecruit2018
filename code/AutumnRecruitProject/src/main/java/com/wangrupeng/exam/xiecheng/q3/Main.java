package com.wangrupeng.exam.xiecheng.q3;

import java.util.Scanner;

class Command {
    char operation;
    int key;
    int value;
    Command(char operation, int key, int value) {
        this.operation = operation;
        this.key = key;
        this.value = value;
    }
}

public class Main {
    private int[] LRU = null;
    private int last = 0;
    private int size = 0;
    void init(int size) {
        if (LRU == null) {
            LRU = new int[size];
        }
    }

    void process(Command command, int s) {
        if (LRU != null) {
            if (command.operation == 'g') {
                System.out.println(LRU[command.key]);
            } else if (command.operation == 'p') {
                LRU[(command.key - 1) % s] = command.value;
            }
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        m.init(size);
        while (scanner.hasNextLine()) {
            char operation = scanner.next().charAt(0);
            int key = scanner.nextInt();
            int value = 0;
            if (operation == 'p') {
                value = scanner.nextInt();
            }
            m.process(new Command(operation, key, value), size);

        }
    }
}
