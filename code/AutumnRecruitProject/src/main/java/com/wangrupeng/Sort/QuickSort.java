package com.wangrupeng.Sort;


import java.util.Stack;

public class QuickSort {


    public void sort(char[] array) {
        quickSort(array, 0, array.length - 1);
        //quickNonRecursive(array, 0, array.length - 1);
    }

    public void quickSort(char[] array, int start, int end) {
        if (start < end) {
            int position = partition(array, start, end);
            quickSort(array, start, position - 1);
            quickSort(array, position + 1, end);
        }
    }

    public void quickNonRecursive(char[] array, int start, int end) {
        if (start < end) {
            Stack<Record> stack = new Stack<>();
            int position = partition(array, start, end);
            if (start <= position - 1) {
                stack.push(new Record(start, position - 1));
            }

            if (end >= position + 1) {
                stack.push(new Record(position + 1, end));
            }

            while (!stack.isEmpty()) {
                Record record = stack.pop();
                position = partition(array, record.left, record.right);
                if (record.left <= position - 1) {
                    stack.push(new Record(record.left, position - 1));
                }
                if (record.right >= position + 1) {
                    stack.push(new Record(position + 1, record.right));
                }
            }
        }
    }

    private int partition(char[] array, int start, int end) {
        int mark = getPivot(array, start, end);
        int right = end;
        while (start < end) {
            while (start < end && array[start] <= mark) {
                ++start;
            }
            while (start < end && array[end] >= mark) {
                --end;
            }
            if (start < end) {
                swap(array, start, end);
            }
        }

        if (right != end) {
            swap(array, right, end);
        }
        return end;
    }

    private int getPivot(char[] array, int start, int end) {
        int middle = start + (end - start) / 2;
        if (array[start] > array[middle]) {
            swap(array, start, end);
        }
        if (array[start] > array[end]) {
            swap(array, start, middle);
        }
        if (array[middle] > array[end]) {
            swap(array, middle, end);
        }
        return array[end];
    }

    private void swap(char[] array, int a, int b) {
        char temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    class Record {
        int left;
        int right;
        Record(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        char[] array = {'C','T','S'};
        QuickSort quickSort = new QuickSort();
        quickSort.sort(array);
        for (int i : array) {
            System.out.print(i + " ");
        }



    }
}
