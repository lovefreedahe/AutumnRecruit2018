package com.wangrupeng.exam.toutiao4.q2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt();
        int[][] array = new int[M][];

        for (int i = 0;i <M ;i++) {
            array[i] = new int[M];
            for (int j = 0;j < M;j++) {
                array[i][j] = scanner.nextInt();
            }
        }
        System.out.println(DFS(array));
    }
    public static int DFS(int[][] arr){
        if(arr==null||arr.length==0||arr[0].length==0){
            return 0;
        }
        int res=0;
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                if(arr[i][j]==1){
                    change(arr,i,j);
                    res++;
                }
            }
        }
        return res;
    }

    private static void dfs(int[][] arr, int i, int j) {
        if(arr[i][j]==0 || i<0 || j<0 || i>=arr.length
            ||j>=arr[0].length) return;
        else{
            arr[i][j]=0;
            dfs(arr,i+1,j);
            dfs(arr,i,j+1);
            dfs(arr,i-1,j);
            dfs(arr,i,j-1);
        }

    }

    public static int[][] change(int[][] grid, int i, int j) {
        grid[i][j] = 0;

        if (i > 0 && grid[i - 1][j] == 1) {
            grid = change(grid, i - 1, j);
        }
        if (j < grid[i].length - 1 && grid[i][j + 1] == 1) {
            grid = change(grid, i, j + 1);
        }
        if (j > 0 && grid[i][j - 1] == 1) {
            grid = change(grid, i, j - 1);
        }
        if (i < grid.length - 1 && grid[i + 1][j] == 1) {
            grid = change(grid, i + 1, j);
        }

        return grid;
    }

}
