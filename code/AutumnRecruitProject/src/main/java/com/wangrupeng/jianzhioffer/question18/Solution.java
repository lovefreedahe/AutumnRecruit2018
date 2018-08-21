package com.wangrupeng.jianzhioffer.question18;

import afu.org.checkerframework.checker.igj.qual.I;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Solution {
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        int up = 0, left = 0;
        int down = matrix.length, right = matrix[0].length;
        while (left < right && up < down ) {
            for (int i = left; i < right ; i++) {
                list.add(matrix[up][i]);
            }
            if (up+1 >= down) {
                break;
            }
            for (int i = up + 1;i < down ;i++) {
                list.add(matrix[i][right - 1]);
            }

            if (right - 2 < left) {
                break;
            }
            for (int i = right - 2;i >= left ;--i) {
                list.add(matrix[down - 1][i]);
            }

            for (int i = down - 2;i >= up + 1 ;--i) {
                list.add(matrix[i][left]);
            }
            left++;
            right--;
            up++;
            down--;
        }
        return list;
    }

    public static void main(String[] args) {
        /*int[][] matrix = {{1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,14,15}};*/
        //int[][] matrix = {{1}, {2}, {3}, {4}};
        int[][] matrix = {{1,2,3,4}};
        Solution solution = new Solution();
        List<Integer> list = solution.printMatrix(matrix);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }
}
