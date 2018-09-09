package com.wangrupeng.exam.jd.q1_new;

import java.util.*;

public class Main {
    public boolean process(int[][] array,int n){
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <=n; j++) {
                if(i==j){
                    continue;
                }
                if(array[i][j]==0){
                    boolean right = false;
                    for (List list : lists) {
                        if (list.contains(i) || list.contains(j)) {
                            list.add(i);
                            list.add(j);
                            right = true;
                            break;
                        }
                    }
                    if (!right) {
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        list.add(j);
                        lists.add(list);
                    }
                }
            }
        }

        for(List<Integer> list1:lists){
            List<Integer> list = new ArrayList<>();
            for(Integer i:list1){
                list.add(i);
            }
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    int p = list.get(i);
                    int q = list.get(j);
                    if(array[p][q]==1){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main processor = new Main();
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[][] graph = new int[n+1][n+1];
            for (int j = 0; j < m; j++) {
                int X = scanner.nextInt();
                int Y = scanner.nextInt();
                graph[X][Y] = 1;
                graph[Y][X] = 1;
            }

            if (processor.process(graph,n)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }

}

