
import java.util.Stack;
public class QuickSort {

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
                stack.put(new Record(start, position - 1));
            }
            if(end >= position + 1) {
                stack.put(new Record(position + 1, end));
            }
            while(!stack.isEmpty()) {
                Record record = stack.pop();
                position = partition(array, record.left, record.right);
                if(record.left <= position - 1) {
                    partition(array, record.left, position - 1);
                }
                if(record.right >= position + 1) {
                    partition(array, position + 1, record.right);
                }
            }
        }
    }

    private int partition(int[] array, int start, int end) {
        int right = end;
        int mark = array[end];
        while(start < end) {
            while(start < end && array[start] <= mark) {
                ++start;
            }
            while(start < end && array[end] >= mark) {
                --end;
            }
            if(start < end) {
                swap(array, start, end);
            }
        }
        if(right != end) {
            swap(array, right, end);
        }
        return end;
    }


    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    private class Record {
        private int left;
        private int right;
        private Record(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] array = {1, 3, 5, 7, 9, 2, 4, 6, 8, 0};
        quickSort.sort(array);
        System.out.println("Quick sort with recursive:");
        for(int i : array) {
            System.out.print(i + " ");
        }
        System.out.println("\n");
        array = {1, 3, 5, 7, 9, 2, 4, 6, 8, 0};
        quickSort.sortNonRecursive(array);
        System.out.println("Quick sort with non-recursive:");
        for(int i : array) {
            System.out.print(i + " ");
        }
    }
}