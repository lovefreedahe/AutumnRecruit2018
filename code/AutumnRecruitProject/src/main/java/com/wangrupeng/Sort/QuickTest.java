package com.wangrupeng.Sort;


import java.util.Random;
import java.util.Stack;
public class QuickTest {
    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public void sortNonRecursive(int[] array) {
        quickSortNonRecursive(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int start, int end) {
        if(start < end) {
            int position = partition(array, start, end);
            quickSort(array, start, position - 1);
            quickSort(array, position + 1, end);
        }
    }

    private void quickSortNonRecursive(int[] array, int start, int end) {
        if(start < end) {
            Stack<Record> stack = new Stack<>();
            int position = partition(array, start, end);
            if(start <= position - 1) {
                stack.push(new Record(start, position - 1));
            }
            if(end >= position + 1) {
                stack.push(new Record(position + 1, end));
            }
            while(!stack.isEmpty()) {
                Record record = stack.pop();
                position = partition(array, record.left, record.right);
                if(record.left <= position - 1) {
                    stack.push(new Record(record.left, position - 1));
                }
                if(record.right >= position + 1) {
                    stack.push(new Record(position + 1, record.right));
                }
            }
        }
    }

    class Record {
        int left;
        int right;
        private Record(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }


    private int partition(int[] array, int start, int end) {
        int right = end;
        int mark = array[end];
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

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
    public static void main(String[] args) {
        QuickTest qsort = new QuickTest();
        int[] array = {1, 3, 5, 7, 9, 2, 4, 6, 8, 0};
        int[] test = new int[10];
        Random random = new Random();
        for (int i = 0;i < 10;i++) {
            test[i] = random.nextInt(10);
        }
        long start = System.currentTimeMillis();
        qsort.sort(test);
        System.out.println("Time used:" + (System.currentTimeMillis() - start));
        System.out.println("Quick sort with recursive:");
        for(int i:test) {
            System.out.print(i + " ");
        }
        System.out.println();
        /*int[] array2 = {1, 3, 5, 7, 9, 2, 4, 6, 8, 0};
        System.out.println("Quick sort with non-recursive:");
        for(int i:array2) {
            System.out.print(i + " ");
        }*/
    }
}
