package com.wangrupeng.exam.netease.game.q2;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public void process(int numMsg, int[] message) {
        HashMap<Integer, Integer> hashMap = new HashMap<>(numMsg);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = numMsg - 1;i >= 0;i--) {
            if (hashMap.get(message[i]) == null) {
                stringBuilder.append(message[i]);
                stringBuilder.append(" ");
                hashMap.put(message[i], 1);
            }
        }
        System.out.println(stringBuilder.toString().trim());
    }

    public static void main(String[] args) {
        Main m = new Main();
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        for (int i = 0;i < number;i++) {
            int numMsg = scanner.nextInt();
            int[] messageIds = new int[numMsg];
            for (int j = 0;j < numMsg;j++) {
                messageIds[j] = scanner.nextInt();
            }

            m.process(numMsg, messageIds);
        }
    }
}
