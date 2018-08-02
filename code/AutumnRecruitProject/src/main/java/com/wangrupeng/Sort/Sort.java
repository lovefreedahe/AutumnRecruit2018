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
        int position = partition2(array, start, end);

        quickSort(array, start, position - 1);
        quickSort(array, position + 1, end);
    }

    int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++) {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot) {
                //i++;
                if (++i == j) {
                    continue;
                } else {
                    // swap arr[i] and arr[j]
                    //swap(arr, i, j);
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }


    private int partition2(int[] array, int start, int end) {
        int mark = array[end];
        int i = start - 1;
        for (int j = start;j < end;++j) {
            if (array[j] <= mark) {
                if (++i == j) {
                    continue;
                } else {
                    swap(array, i, j);
                }
            }
        }
        swap(array, i + 1, end);
        return i + 1;
    }

    public void mergeSort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(int[] array, int start, int end) {
        if (start < end) {
            int middle = (end + start)/2;
            mergeSort(array, start, middle);
            mergeSort(array, middle + 1, end);
            merge(array, start, middle, end);
        }
    }

    private void merge(int[] array, int start, int middle, int end) {
        int length = end - start + 1;
        int[] temp = new int[length];
        int i = start;
        int j = middle + 1;
        int k = 0;

        while (i <= middle && j <=end) {
            if (array[i] < array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }

        while (i <= middle) {
            temp[k++] = array[i++];
        }

        while (j <= end) {
            temp[k++] = array[j++];
        }

        for (int x = 0;x < length; ++x) {
            array[x + start] = temp[x];
        }
    }

    public static void main(String[] args) {
        Sort sort = new Sort();
        int[] array = {1, 3, 5, 7, 9, 2, 4, 6, 8, 0 };
        //int[] array = {10, 80, 30, 90, 40, 50, 70};
        sort.mergeSort(array);
        for (int i : array) {
            System.out.print(i);
            System.out.print(" ");
        }
    }
}
