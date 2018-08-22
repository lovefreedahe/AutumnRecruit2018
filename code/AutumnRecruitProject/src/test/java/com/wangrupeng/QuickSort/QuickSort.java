package com.wangrupeng.QuickSort;

import org.apache.logging.log4j.core.async.ArrayBlockingQueueFactory;

import java.util.*;
import java.util.concurrent.*;

public class QuickSort {


    public void sort(int[] array) {
        //quickSort(array, 0, array.length - 1);
        quickNonRecursive(array, 0, array.length - 1);
    }

    public void quickSort(int[] array, int start, int end) {
        if (start < end) {
            int position = partition(array, start, end);
            quickSort(array, start, position - 1);
            quickSort(array, position + 1, end);
        }
    }

    public void quickNonRecursive(int[] array, int start, int end) {
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

    private int partition(int[] array, int start, int end) {
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

    private int getPivot(int[] array, int start, int end) {
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

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
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
        int[] array = {8,7,6,5, 3,2,1};
        QuickSort quickSort = new QuickSort();
        quickSort.sort(array);
        for (int i : array) {
            System.out.print(i + " ");
        }



    }
}
