
<!-- TOC -->

- [基于比较的排序算法](#基于比较的排序算法)
    - [选择排序](#选择排序)
    - [插入排序](#插入排序)
    - [希尔排序](#希尔排序)
    - [冒泡排序](#冒泡排序)
    - [快速排序](#快速排序)
    - [归并排序](#归并排序)
    - [堆排序](#堆排序)
    - [比较](#比较)
- [基于计算的排序算法(时间复杂度O(n))](#基于计算的排序算法时间复杂度on)
- [总结](#总结)
    - [各种排序算法特点以及适用的场景](#各种排序算法特点以及适用的场景)
- [参考](#参考)

<!-- /TOC -->

# 基于比较的排序算法
## 选择排序
* 简介
这是一种最简单直观的排序,是稳定的排序算法。
* 原理
每一趟从待排序的数列中选出最小的（最大的）一个元素，顺序放到已经排好序的数列的最后，直到所有待排元素全部排好.
* 时间复杂度
O(n^2)
* 过程演示
<div align="center"><img src="../../resources/images/algorithm/select_sort.gif"></div></br> 

```shell
|1 3 5 7 9 2 4 6 8 0  选择第一小的数与0位交换
i j
1 3 5 7 9 2 4 6 8 0
i                 j
                 min
0| 3 5 7 9 2 4 6 8 1  选择第二小的数与1位交换
  i j
0 3 5 7 9 2 4 6 8 1
  i               j
                 min
0 1| 5 7 9 2 4 6 8 3  选择第三小的数与2位交换
0 1 2| 7 9 5 4 6 8 3  选择第四小的数与3位交换
0 1 2 3| 9 5 4 6 8 7  选择第五小的数与4位交换
0 1 2 3 4| 5 9 6 8 7  选择第六小的数与5位交换
0 1 2 3 4 5| 9 6 8 7  选择第七小的数与6位交换
0 1 2 3 4 5 6| 9 8 7  选择第八小的数与7位交换
0 1 2 3 4 5 6 7| 8 9  选择第九小的数与8位交换
0 1 2 3 4 5 6 7 8| 9  待排只剩一个数，排序结束

```
* 代码
```java
    public void selectSort(int[] arrays) {
        for (int i = 0;i < arrays.length; ++i) {
            int min = arrays[i];
            int pos = i;
            for (int j = i + 1;j < arrays.length; ++j) {
                if (arrays[j] > min) {
                    min = arrays[j];
                    pos = j;
                }
            }
            swap(arrays, i, pos);
        }
    }

    private void swap(int [] arrays, int pos1, int pos2) {
        int temp = arrays[pos2];
        arrays[pos2] = arrays[pos1];
        arrays[pos1] = temp;
    }
```
## 插入排序
* 简介
这也是一种简单直观的排序算法，是稳定的排序算法。
* 原理
构建有序序列，即对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
* 时间复杂度
O(n^2)。
* 过程演示
<div align="center"><img src="../../resources/images/algorithm/insert_sort.gif"></div></br> 

```shell
1 3 5 7 9 2 4 6 8 0
1 3 5 7 9 9 4 6 8 0  temp=2
1 3 5 7 7 9 4 6 8 0
1 3 5 5 7 9 4 6 8 0
1 3 3 5 7 9 4 6 8 0
1 2 3 5 7 9 4 6 8 0
1 2 3 5 7 9 9 6 8 0  temp=4
1 2 3 5 7 7 9 6 8 0
1 2 3 5 5 7 9 6 8 0
1 2 3 4 5 7 9 6 8 0
1 2 3 4 5 7 9 9 8 0  temp=6
1 2 3 4 5 7 7 9 8 0
1 2 3 4 5 6 7 9 8 0
1 2 3 4 5 6 7 9 9 0  temp=8
1 2 3 4 5 6 7 8 9 0
1 2 3 4 5 6 7 8 9 9  temp=0
1 2 3 4 5 6 7 8 8 9
1 2 3 4 5 6 7 7 8 9
1 2 3 4 5 6 6 7 8 9
1 2 3 4 5 5 6 7 8 9
1 2 3 4 4 5 6 7 8 9
1 2 3 3 4 5 6 7 8 9
1 2 2 3 4 5 6 7 8 9
1 1 2 3 4 5 6 7 8 9
0 1 2 3 4 5 6 7 8 9
```
* 代码
```java
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
```
## 希尔排序

* 简介
也称递减增量排序算法，是插入排序的一种高速且稳定的改进版本。我把希尔排序叫做分组插入排序。是不稳定的排序算法。
* 原理
先把要排序的序列元素以序列长度的1/2为间隔（向下取证）两两分为一组，对每组分别进行插入排序，排完后再以序列长度的1/4为间隔（向下取整）分组，对每组分别进行插入排序，重复上述操作，直到间隔为一，即最后一趟为普通的插入排序（此时序列已基本有序）。
* 时间复杂度
取决于分组间隔gap的值，在O(n(lgn)2)~O(n2)之间
* 过程演示
```shell
1 3 5 7 9 2 4 6 8 0  gap=5
1 3 5 7 9 2 4 6 8 9  temp=0
1 3 5 7 0 2 4 6 8 9  gap=2
1 3 5 7 5 2 4 6 8 9  temp=0
1 3 1 7 5 2 4 6 8 9
0 3 1 7 5 2 4 6 8 9
0 3 1 7 5 2 5 6 8 9  temp=4
0 3 1 7 4 2 5 6 8 9
0 3 1 7 4 7 5 6 8 9  temp=2
0 3 1 3 4 7 5 6 8 9
0 2 1 3 4 7 5 6 8 9
0 2 1 3 4 7 5 7 8 9  temp=6
0 2 1 3 4 6 5 7 8 9  gap=1
0 2 2 3 4 6 5 7 8 9  temp=1
0 1 2 3 4 6 5 7 8 9
0 1 2 3 4 6 6 7 8 9  temp=5
0 1 2 3 4 5 6 7 8 9

```
<div align="center"><img src="../../resources/images/algorithm/shell_sort.png"></div>


* 代码
```java
    public void shellSort(int[] array) {
        int gap = array.length / 2;
        while (gap >= 1) {
            for (int i = 0; i < array.length - gap; ++i) {
                insertSort(array, i, gap);
            }
            gap = gap / 2;
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
```
## 冒泡排序
* 简介
是一种简单的排序算法。因其排序过程中较大（较小）元素会慢慢“浮到”顶部，就像鱼吐泡泡而得名。是稳定的排序。
* 原理
重复的遍历要排序的序列，一次比较两个元素，如果它们的顺序错误就把它们交换过来，直到序列有序。
* 时间复杂度
O(n^2)。
* 过程演示
<div align="center"><img src="../../resources/images/algorithm/bubble_sort.gif"></div></br> 

```shell
|1 3 5 7 9 2 4 6 8 0
|1 3 5 7 9 2 4 6 0 8
|1 3 5 7 9 2 4 0 6 8
|1 3 5 7 9 2 0 4 6 8
|1 3 5 7 9 0 2 4 6 8
|1 3 5 7 0 9 2 4 6 8
|1 3 5 0 7 9 2 4 6 8
|1 3 0 5 7 9 2 4 6 8
|1 0 3 5 7 9 2 4 6 8
0 1| 3 5 7 9 2 4 6 8
0 1| 3 5 7 2 9 4 6 8
0 1| 3 5 2 7 9 4 6 8
0 1| 3 2 5 7 9 4 6 8
0 1 2 3| 5 7 9 4 6 8
0 1 2 3| 5 7 4 9 6 8
0 1 2 3| 5 4 7 9 6 8
0 1 2 3 4 5| 7 9 6 8
0 1 2 3 4 5| 7 6 9 8
0 1 2 3 4 5 6 7| 9 8
0 1 2 3 4 5 6 7 8 9|
```
* 代码
```java
public void bubbleSort(int[] array) {
        for (int i = 0;i < array.length; ++i) {
            for (int j = array.length - 1; j > 0 ;--j) {
                if (array[j] < array[j - 1]){
                    swap(array, j, j - 1);
                }
            }
        }
    }
```
## 快速排序
* 简介
在平均状态下，排序n个项目要O(nlogn)次比较。在最坏状况下则需要O(n^2)次比较，但这种状况并不常见。事实上，快速排序通常要明显比其它O(nlogn)算法更快，因为它的内部循环（inner loop）可以在大部分的架构上很有效率的被实现出来，且在大部分真实世界的数据，可以决定设计的选择，减少所需时间的二次方项之可能性。是不稳定的排序算法。

* 原理

* 时间复杂度
O(nlogn)~O(n^2)。
* 过程演示
```shell
arr[] = {10, 80, 30, 90, 40, 50, 70}
Indexes:  0   1   2   3   4   5   6 

low = 0, high =  6, pivot = arr[h] = 70
Initialize index of smaller element, i = -1

Traverse elements from j = low to high-1
j = 0 : Since arr[j] <= pivot, do i++ and swap(arr[i], arr[j])
i = 0 
arr[] = {10, 80, 30, 90, 40, 50, 70} // No change as i and j 
                                     // are same

j = 1 : Since arr[j] > pivot, do nothing
// No change in i and arr[]

j = 2 : Since arr[j] <= pivot, do i++ and swap(arr[i], arr[j])
i = 1
arr[] = {10, 30, 80, 90, 40, 50, 70} // We swap 80 and 30 

j = 3 : Since arr[j] > pivot, do nothing
// No change in i and arr[]

j = 4 : Since arr[j] <= pivot, do i++ and swap(arr[i], arr[j])
i = 2
arr[] = {10, 30, 40, 90, 80, 50, 70} // 80 and 40 Swapped
j = 5 : Since arr[j] <= pivot, do i++ and swap arr[i] with arr[j] 
i = 3 
arr[] = {10, 30, 40, 50, 80, 90, 70} // 90 and 50 Swapped 

We come out of loop because j is now equal to high-1.
Finally we place pivot at correct position by swapping
arr[i+1] and arr[high] (or pivot) 
arr[] = {10, 30, 40, 50, 70, 90, 80} // 80 and 70 Swapped 

Now 70 is at its correct place. All elements smaller than
70 are before it and all elements greater than 70 are after
it.
```
* 代码
```java
    private void quickSort(int[] array, int start, int end) {
        if (end < start) {
            return;
        }
        int position = partition2(array, start, end);

        quickSort(array, start, position - 1);
        quickSort(array, position + 1, end);
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
```
## 归并排序
* 简介
归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法的一个非常典型的应用。是稳定的排序算法。
* 原理
先申请一个空间用于存储排序后的序列，大小为两个已经排序的序列大小之和。在这两个已经排序的序列头部分分别放置指针，比较指针所指元素的大小，较小的（或较大的）复制到刚刚申请的新序列空间，该指针后移，重复比较、复制到新序列尾部、后移指针，直到遍历完其中一个序列，则另一个序列的剩余元素全部原序复制到新序列尾部。

* 时间复杂度
时间复杂度为O(nlogn)，需要O(n)额外空间
* 过程演示
<div align="center"><img src="../../resources/images/algorithm/merge_sort.gif"></div></br> 

```shell
                        {1 3 5 7 9 2 4 6 8 0}
第一层递归         {1 3 5 7 9}    |     {2 4 6 8 0}
第二层递归       {1 3 5} | {7 9}  |   {2 4 6} | {8 0}
第三层递归     {1 3} |{5}|{7}|{9} |  {2 4}|{6}|{8}|{0}
第四层递归    {1}|{3}|{5}|{7}|{9} |{2}|{4}|{6}|{8}|{0}
第一层归并     {1 3} |{5}| {7 9}  |  {2 4}|{6}| {0 8}
第二层归并       {1 3 5} | {7 9}  |   {2 4 6} | {0 8}
第三层归并         {1 3 5 7 9}    |     {0 2 4 6 8}
第四层归并              {0 1 2 3 4 5 6 7 8 9}

```
* 代码
```java
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
```
## 堆排序
* 简介
堆排序与归并排序相似，不同的是堆排序的时间复杂度为O(nlogn)。又与插入排序相似，不同的是堆排序是不稳定的排序算法且具有空间原地址：任何时候都需要常熟个额外的元素空间存储临时数据。因此，堆排序是集合了归并排序和插入排序优点的一种排序算法。
* 原理

* 时间复杂度

* 过程演示

* 代码
```java
```


## 比较

名称 | 稳定性 | 比较次数  | 空间复杂度 | 适用场景
-- | -- | -- | -- | -- | 
选择排序 | 不稳定 |  n(n-1)/2 | O(1) |
插入排序 | 稳定 | 最好(n-1), 最差n(n-1)/2 | O(1) | 初始序列大量有序 |
希尔排序 | 不稳定 | O(n(lgn)2)~O(n2) | 
冒泡排序 | 稳定 |n(n-1)/2 | O(1) |
快速排序 | 不稳定 | nlog(n)，最差为n(n-1)/2 | log(n) |
归并排序 | 稳定 |nlog2(n)/2~nlog2(n)-n+1 |O(n) | 大量数据，外排序 |
堆排序 | 不稳定 |nlog(n)，和初始顺序关系不大 |O(1) |

<div align="center"><img src="../../resources/images/algorithm/sort.jpg"></div>  

# 基于计算的排序算法(时间复杂度O(n))


# 总结
## 各种排序算法特点以及适用的场景
* 时间复杂度
    * O(n^2)
    选择排序，插入排序，冒泡排序
    * O(nlgn)
    快速排序，堆排序，归并排序
    * O(n)~O(n^1+m)(0<m<1)
    希尔排序
    * O(n)
    桶排序，箱排序，基数排序
    
* 影响排序的因素
    * 待排序数组的长度n
    * 稳定性的要求
    * 时间和空间复杂度
    * 待排序数组初始顺序状态

* 不同条件下，排序算法的选择
    * 如果n比较小(n<=50)
    插入排序或选择排序,选择排序的移动次数要比插入排序的少。
    * 如果n比较大
    选择时间复杂度为O(nlgn)的算法，如快排、堆排序、归并排序。快排是目前基于比较的内部排序中，被认为最好的算法。当前排序的关键字是随机分布的，平均时间最短。
    堆排序需要的辅助空间要少于快速排序，并且不会出现快速排序可能出现的最坏的情况。以上两种排序都是不稳定的。
    若要求排序稳定，可以使用归并排序。
    * 如果数据初始顺序为基本有序
    插入排序，冒泡排序或随机的快速排序


# 参考
* [Java排序算法](https://www.jianshu.com/p/4dac9c141bd5)