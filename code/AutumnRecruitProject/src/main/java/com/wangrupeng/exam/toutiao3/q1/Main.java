package com.wangrupeng.exam.toutiao3.q1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public void process(Map<Integer, ArrayList<Integer>> map, int n) {
        Map<Integer, ArrayList<Integer>> member = new HashMap<>(map.size());
        for (int i = 1;i <= n;i++) {
            ArrayList<Integer> list = map.get(i);
            for (int j : list) {
                if (member.get(j) == null) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    member.put(j, temp);
                } else {
                    member.get(j).add(i);
                }
            }
        }

        System.out.println(member.size());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main m = new Main();
        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            Map<Integer, ArrayList<Integer>> map = new HashMap<>(n);
            int i = 1;

            while ( i <= n) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                String[] array = line.split("\\s+");
                ArrayList<Integer> arrayList = new ArrayList<>(array.length);
                //Map<Integer, Integer> map1 = new HashMap<>(array.length - 1);
                for (int j = 0; j < array.length - 1; j++) {
                    //map1.put(Integer.valueOf(array[j]), 0);
                    arrayList.add(Integer.valueOf(array[j]));
                }
                map.put(i++, arrayList);
            }

            m.process(map, n);
        }
    }
}
