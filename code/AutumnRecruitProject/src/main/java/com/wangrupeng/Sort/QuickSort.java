package com.wangrupeng.Sort;

import com.wangrupeng.pattern.proxy.Queue;
import com.wangrupeng.pattern.proxy.dynamic.RealSubject;

import java.util.Stack;

public class QuickSort {
    public void sort(int[] array) {
        //quickSort2(array, 0, array.length - 1);
        //quickNonRecursive(array, 0, array.length - 1);
        quickNonRecursiveTest(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int start, int end) {
        if (start > end) {
            return;
        }
        int position = partition(array, start, end);
        quickSort(array, start, position - 1);
        quickSort(array, position + 1, end);
    }

    private int partition(int[] array, int left, int right) {
        int begin = left - 1;
        //int end = right;
        int mark = array[right];
        for (int i = left;i < right; ++i) {
            if (array[i] <= mark) {
                if (++begin == i) {
                    continue;
                } else {
                    swap(array, begin, i);
                }
            }
        }
        swap(array, begin + 1, right);
        return begin + 1;
    }

    private void quickSort2(int[] array, int start, int end) {
        if (start < end) {
            int position = partition3(array, start, end);
            quickSort2(array, start, position - 1);
            quickSort2(array, position + 1, end);
        }
    }

    //填充法
    private int partition2(int[] array, int start, int end) {
        int left = start;
        int right = end - 1;
        int mark = array[right];
        while (left < right) {
            while (left < right && array[left] <= mark) {
                ++left;
            }
            if (left < right) {
                array[right--] = array[left];
            }
            while (left < right && array[right] >= mark) {
                right--;
            }
            if (left < right) {
                array[left++] = array[right];
            }
        }
        array[right] = mark;
        return end;
    }

    //交换法
    private int partition3(int[] array, int start, int end) {
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
        if (end != right) {
            swap(array, end, right);
        }
        return end;
    }

    private void quickNonRecursive(int[] array, int start, int end) {
        Stack<Record> stack = new Stack<>();
        if (start < end) {
            int position = partition3(array, start, end);
            if (start <= position - 1) {
                stack.push(new Record(start, position - 1));
            }
            if (end >= position + 1) {
                stack.push(new Record(position + 1, end));
            }
            while (!stack.isEmpty()) {
                Record record = stack.pop();
                position = partition3(array, record.left, record.right);
                if (record.left <= position - 1) {
                    stack.push(new Record(record.left, position - 1));
                }
                if (record.right >= position + 1) {
                    stack.push(new Record(position + 1, record.right));
                }
                System.out.println(stack.size());
            }
        }
        System.out.println();
    }

    private void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    private void quickNonRecursiveTest(int[] array, int start, int end) {
        if (start < end) {
            Stack<Record> stack = new Stack<>();
            int position = partitionTest(array, start, end);
            if (start <= position - 1) {
                stack.push(new Record(start, position - 1));
            }
            if (end >= position + 1) {
                stack.push(new Record(position + 1, end));
            }
            while (!stack.isEmpty()) {
                Record record = stack.pop();
                position = partitionTest(array, record.left, record.right);
                if (record.left <= position - 1) {
                    stack.push(new Record(record.left, position - 1));
                }
                if (record.right >= position + 1) {
                    stack.push(new Record(position + 1, record.right));
                }
            }
        }
    }

    private int partitionTest(int[] array, int start, int end) {
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

    private class Record{
        private int left;
        private int right;
        private Record(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        int[] array = {10, 80, 30, 90, 40, 50, 70};
        QuickSort quickSort = new QuickSort();
        quickSort.sort(array);
        for (int i:array) {
            System.out.print(i + " ");

        }
    }
}
