package com.wangrupeng.test;

public class QuickSort {
    public void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int start, int end) {
        if (end < start) {
            return;
        }

        int position = partition(array, start, end);

        quickSort(array, start, position - 1);
        quickSort(array, position + 1, end);

    }

    private int partition(int[] array, int start, int end) {
        int mark = array[end];
        int i = start - 1;
        for (int j = start;j < end; ++j) {
            if (array[j] <= mark ) {
                if (++i == j) {
                    continue;
                } else {
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }

        array[end] = array[i + 1];
        array[i + 1] = mark;
        return i + 1;
    }

    public static void main(String[] args) {
        int[] array = {1, 3, 5, 7, 9, 2, 4, 6, 8, 0 };
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(array);
        for (int i : array) {
            System.out.print(i);
            System.out.print(" ");
        }
    }
}
