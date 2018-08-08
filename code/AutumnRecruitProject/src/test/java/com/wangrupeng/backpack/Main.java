package com.wangrupeng.backpack;

import java.util.Scanner;

class Treasure{
    private int weight;
    private int value;

    public Treasure(int value, int weight) {
        this.weight = weight;
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

}

public class Main {

    public void process(String valueTotal, String weightTotal, int capacity) {
        String[] values = valueTotal.split(",");
        String[] weights = weightTotal.split(",");
        if (values.length != weights.length) {
            return;
        }
        int number = values.length;
        Treasure[] treasures = new Treasure[values.length];
        for (int i = 0;i < number;i++) {
            treasures[i] = new Treasure(Integer.valueOf(values[i]), Integer.valueOf(weights[i]));
        }

        int[][] bestValues = new int[number + 1][capacity + 1];
        for (int j = 0; j <= capacity; j++) {
            for (int i = 0; i <= number; i++) {
                if (i == 0 || j == 0) {
                    bestValues[i][j] = 0;
                } else {
                    if (j < treasures[i - 1].getWeight()) {
                        bestValues[i][j] = bestValues[i - 1][j];
                    } else {
                        int weight = treasures[i - 1].getWeight();
                        int value = treasures[i - 1].getValue();
                        bestValues[i][j] = Math.max(bestValues[i - 1][j], value
                                + bestValues[i - 1][j - weight]);
                    }
                }
            }
        }

        System.out.println(bestValues[number][capacity]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String valueTotal, weightTotal;
        int capacity;
        Main m = new Main();
        while (scanner.hasNext()) {
            valueTotal = scanner.next();
            weightTotal = scanner.next();
            capacity = scanner.nextInt();
            m.process(valueTotal, weightTotal, capacity);
        }
    }
}
