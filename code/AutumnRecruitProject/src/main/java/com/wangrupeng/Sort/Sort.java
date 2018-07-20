package com.wangrupeng.Sort;

public class Sort {

    //选择排序
    //时间复杂度：O(n^2)
    public void selectSort(int[] array) {
        for (int i = 0; i < array.length; ++i) {
            int min = array[i];
            int pos = i;
            for (int j = i + 1; j < array.length; ++j) {
                if (array[j] > min) {
                    min = array[j];
                    pos = j;
                }
            }
            swap(array, i, pos);
        }
    }

    private void swap(int [] arrays, int pos1, int pos2) {
        int temp = arrays[pos2];
        arrays[pos2] = arrays[pos1];
        arrays[pos1] = temp;
    }

    //插入排序
    public void insert(int[] array) {
        int temp;
        for (int i = 0;i < array.length - 1; ++i) {
            temp = array[i + 1];
            int j = i + 1;
            while (j> 0 && temp < array[j - 1]) {
                array[j] = array[j - 1];
                --j;
            }
            array[j] = temp;
        }
    }

    //插入排序
    public void insertSort(int[] array, int start, int gap) {
        int temp;
        for (int i = start;i < start + gap - 1; ++i) {
            temp = array[i + 1];
            int j = i + 1;
            while (j> start && temp < array[j - 1]) {
                array[j] = array[j - 1];
                --j;
            }
            array[j] = temp;
        }
    }

    public void bubbleSort(int[] array) {
        for (int i = 0;i < array.length; ++i) {
            for (int j = array.length - 1; j > 0 ;--j) {
                if (array[j] < array[j - 1]){
                    swap(array, j, j - 1);
                }
            }
        }
    }

    public void shellSort(int[] array) {
        int gap = array.length / 2;
        while (gap > 1) {
            int length = array.length - gap + 1;
            for (int i = 0; i <  gap; ++i) {
                insertSort(array, i, length);
            }
            gap = gap / 2;
        }
    }

    public void quickSort(int[] array) {
        int head = array[0];
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int start, int end) {
        if (end < start) {
            return;
        }
        int mark = array[start];
        int i = start + 1;
        int j = end;
        while (i <= j) {
            if (array[i] > mark) {
                ++i;
            }
            if (array[j] < mark) {
                --j;
            }
        }


    }



    public static void main(String[] args) {
        Sort sort = new Sort();
        int[] array = {1, 3, 5, 7, 9, 2, 4, 6, 8, 0 };
        sort.shellSort(array);
        for (int i : array) {
            System.out.print(i);
            System.out.print(" ");
        }
    }
}
