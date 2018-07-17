## 剑指offer

<!-- TOC -->

- [剑指offer](#剑指offer)
- [实现Singleton](#实现singleton)
        - [1. 急切初始化](#1-急切初始化)
        - [2. 静态块初始化](#2-静态块初始化)
        - [3. 懒人模式](#3-懒人模式)
        - [4. 线程安全模式](#4-线程安全模式)
        - [5. Bill Pugh模式](#5-bill-pugh模式)
        - [防止反射破坏单例模式](#防止反射破坏单例模式)
        - [防止序列化破坏单例模式](#防止序列化破坏单例模式)
- [二维数组查找](#二维数组查找)
- [替换空格](#替换空格)
- [从尾到头遍历List](#从尾到头遍历list)
- [重建二叉树](#重建二叉树)
- [两个栈实现一个队列](#两个栈实现一个队列)
- [旋转数组的最小数字](#旋转数组的最小数字)
- [斐波那契数列](#斐波那契数列)
- [青蛙跳台阶](#青蛙跳台阶)
- [青蛙跳台阶变态版](#青蛙跳台阶变态版)
- [](#)
- [](#-1)
- [](#-2)
- [](#-3)
- [](#-4)
- [](#-5)
- [](#-6)
- [](#-7)
- [](#-8)
- [](#-9)
- [](#-10)
- [](#-11)
- [](#-12)
- [](#-13)
- [](#-14)
- [](#-15)
- [](#-16)
- [](#-17)
- [](#-18)
- [](#-19)
- [](#-20)
- [](#-21)
- [](#-22)
- [](#-23)
- [](#-24)
- [](#-25)
- [](#-26)
- [](#-27)
- [](#-28)
- [](#-29)
- [](#-30)
- [](#-31)
- [](#-32)
- [](#-33)
- [](#-34)
- [](#-35)
- [](#-36)

<!-- /TOC -->

## 实现Singleton
> ### 题目描述

设计一个类，只能生成该类的一个实例。

> ### 解决方法  
#### 1. 急切初始化  
最简单的方法，即使没有被调用也会被初始化，浪费资源。而且getInstance无法抛出异常。
```java
public class EagerInitializationSingleton {
    private static final EagerInitializationSingleton instance = new EagerInitializationSingleton();

    private EagerInitializationSingleton() {}

    public static EagerInitializationSingleton getInstance() {
        return instance;
    }
}
```
#### 2. 静态块初始化  
可以抛出异常。浪费资源。
```java
public class StaticBlockInitilizationSingleton {
    private static StaticBlockInitilizationSingleton instalce;
    private StaticBlockInitilizationSingleton() {}

    static {
        try {
            instalce = new StaticBlockInitilizationSingleton();
        } catch (Exception e) {
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }

    public static StaticBlockInitilizationSingleton getInstalce() {
        return instalce;
    }
}
```

#### 3. 懒人模式  
第一次调用才初始化。但是线程不安全。
```java
public class LazyInitializationSingleton {
    private static LazyInitializationSingleton instance;
    private LazyInitializationSingleton() {}

    public static LazyInitializationSingleton getInstance() {
        if (instance == null) {
            instance = new LazyInitializationSingleton();
        }
        return instance;
    }
}
```
#### 4. 线程安全模式  
线程安全，但是synchronized耗费资源，我们仅仅需要在初始化实例的时候调用它。
```java
public class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;
    private ThreadSafeSingleton() {}
    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }
}
```
双重检测  
```java
public static ThreadSafeSingleton getInstanceDoubleCheck() {
    if (instance == null) {
        synchronized (ThreadSafeSingleton.class) {
            if (instance == null) {
                instance = new ThreadSafeSingleton();
            }
        }
    }
    return instance;
}
```
#### 5. Bill Pugh模式
借助于内部静态帮助类来生成单例。
```java
public class BillPughSingleton {
    private BillPughSingleton() {}
    private static class SingletonHelper {
        private static final BillPughSingleton instance = new BillPughSingleton();
    }
    public static BillPughSingleton getInstance() {
        return SingletonHelper.instance;
    }
}
```
> #### 以上5种生成单例的方法，都会被java的反射技术破坏  
```java
public class ReflectionSingletonTest {
    public static void main(String[] args) {
        EagerInitializationSingleton instanceOne = EagerInitializationSingleton.getInstance();
        EagerInitializationSingleton instanceTwo = null;
        try {
            Constructor[] constructors = EagerInitializationSingleton.class.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                constructor.setAccessible(true);
                instanceTwo = (EagerInitializationSingleton)constructor.newInstance();
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(instanceOne.hashCode());
        System.out.println(instanceTwo.hashCode());
    }
}
```
#### 防止反射破坏单例模式
* 使用Enum方式生成单例
```java
public enum Singleton {
    INSTANCE;
    private String name;
    public void set(String name) {
        this.name = name;
    }
    public String get() {
        return this.name;
    }
    public void otherFunc() {
    }
}
```

* 限制构造函数只能调用一次。
```java
private static boolean flag = true;
private EagerInitializationSingleton() {
    if (!flag) {
        flag = !flag;
    } else {
        throw new RuntimeException("Could not create the instance, cause there's already have.");
    }
}
```
#### 防止序列化破坏单例模式
```java
public class SerializedSingleton implements Serializable{
    private static final long serialVersionUID = -7604766932017737115L;
    private SerializedSingleton() {}
    private static class SingletonHelper {
        private static final SerializedSingleton instance = new SerializedSingleton();
    }
    public static SerializedSingleton getInstance() {
        return SingletonHelper.instance;
    }
    //加入这个函数就可以
    protected Object readResolve() {
        return getInstance();
    }
}
```
测试
```java
public class SingletonSerializedTest {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        SerializedSingleton instanceOne = SerializedSingleton.getInstance();
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream("file.ser"));
        out.writeObject(instanceOne);
        out.close();

        ObjectInput input = new ObjectInputStream(new FileInputStream("file.ser"));
        SerializedSingleton instanceTwo = (SerializedSingleton)input.readObject();
        input.close();
        System.out.println(instanceOne.hashCode());
        System.out.println(instanceTwo.hashCode());
    }
}
```

## 二维数组查找
> ### 题目描述
在二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数输入一个这样的数组和一个整数，判断数组中是否含有改整数。
> ### 解决方法

```java
public class Solution {
    public boolean Find(int target, int [][] array) {
        int column = array[0].length - 1;
        int row = 0;
        while(column >= 0 && row < array.length) {
            if(target > array[row][column]) {
                row++;
            } else if(target < array[row][column]) {
                column--;
            } else {
                return true;
            }
           
        }
        return false;
    }
}
```

## 替换空格
> ### 题目描述
实现一个函数,讲一个字符串中的空格替换成"%20"
> ### 解决方法

```java
public class Solution {
    public String replaceSpace(StringBuffer str) {
        int length = str.length();
        String sample = "%20";
        for (int i = 0; i < length;i++) {
            if (str.charAt(i) == ' ') {
                str.replace(i, i + 1, sample);
                length += 2;
            }
        }
        return str.toString();
    }
}
```

## 从尾到头遍历List
> ### 题目描述  
输入一个链表，按链表值从尾到头的顺序返回一个LinkedList。
> 解决办法

递归
```java
import java.util.ArrayList;

 class ListNode {
         int val;
         ListNode next = null;

         ListNode(int val) {
             this.val = val;
         }
     }

public class Solution {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = null;
        if(listNode == null) {
            return new ArrayList<>();
        }
        if(listNode.next == null) {
            list = new ArrayList<>();
            list.add(listNode.val);
        } else {
            list = printListFromTailToHead(listNode.next);
            list.add(listNode.val);
        }
        return list;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode root = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        root.next = node1;
        node1.next = node2;
        ArrayList<Integer> list = solution.printListFromTailToHead(root);
        for (int i : list) {
            System.out.println(i);
        }
    }
}
```

## 重建二叉树
> 题目描述

输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
> 解决办法

```java
public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        return reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    public TreeNode reConstructBinaryTree(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preStart]);
        for (int i = inStart;i <= inEnd ;i++) {
            if (in[i] == pre[preStart]) {
                root.left = reConstructBinaryTree(pre, preStart + 1, preStart + i - inStart, in, inStart, i - 1);
                root.right = reConstructBinaryTree(pre, preStart + i - inStart + 1, preEnd, in, i + 1, inEnd);
                break;
            }
        }
        return root;
    }
```


## 两个栈实现一个队列
> 题目描述

用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
> 解决办法

```java
public class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        while(stack2.size() > 0) {
            int tmp = stack2.pop();
            stack1.push(tmp);
        }
        stack1.push(node);
    }

    public int pop() {
        if (stack1.size() == 0 && stack2.size() != 0) {
            return stack2.pop();
        }
        while(stack1.size() > 1) {
            int tmp = stack1.pop();
            stack2.push(tmp);
        }
        return stack1.pop();
    }
}
```

## 旋转数组的最小数字
> 题目描述

把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
> 解决办法

```java
import java.util.ArrayList;
public class Solution {
    public int minNumberInRotateArray(int [] array) {
        if(array.length == 0) {
            return 0;
        }
        int indexFirst = 0;
        int indexLast = array.length - 1;
        int indexMid = indexFirst;
        while(array[indexFirst] >= array[indexLast] && indexFirst < indexLast){
            if (indexLast - indexFirst == 1) {
                indexMid = indexLast;
                break;
            }
            indexMid = (indexLast + indexFirst)/2 ;
            if(array[indexMid] >= array[indexFirst]) {
                indexFirst = indexMid;
            } else{
                indexLast = indexMid;
            }
        }
        
        return array[indexMid];
    }
}
```

## 斐波那契数列
> 题目描述

大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项。
n<=39
> 解决办法

```java
public class Solution {
    //1 1 2 3 5 8
    public int Fibonacci(int n) {
        if(n == 0) {
            return 0;
        }
        if(n == 1) {
            return 1;
        }
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }

    public int FibonacciEffective(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int tmp1 = 0;
        int tmp2 = 1;
        for (int i = 2;i < n;i++) {
            int tmp3 = tmp2;
            tmp2 = tmp1 + tmp2;
            tmp1 = tmp3;
        }
        return tmp1 + tmp2;
    }

    public int FibonacciArray(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] array = new int[n + 1];
        array[0] = 0;
        array[1] = 1;
        for (int i = 2; i<= n;i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[n];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.Fibonacci(6));
        System.out.println(solution.FibonacciEffective(6));
        System.out.println(solution.FibonacciArray(6));
    }
}

```

## 青蛙跳台阶
> 题目描述

一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
> 解决办法

```java
public class Solution {
    public int JumpFloor(int target) {
        /*if(target == 0) {
            return 1;
        }
        if(target == 1) {
            return 1;
        }
        return JumpFloor(target - 1) + JumpFloor(target -2);
        */
         if (target == 0) {
            return 1;
        }
        if (target == 1) {
            return 1;
        }

        int tmp1 = 1;
        int tmp2 = 1;
        for (int i = 2; i < target; i++) {
            int tmp3 = tmp1 + tmp2;
            tmp1 = tmp2;
            tmp2 = tmp3;
        }
        return tmp1 + tmp2;
    }
}
```

## 青蛙跳台阶变态版
> 题目描述
一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。

> 解决办法

```java
public class FogUpgrade {
    public int JumpFloorII(int target) {
        if (target == 0 || target == 1) {
            return 1;
        }
        return 2 * JumpFloorII(target - 1);
    }

    public int JumpFloorIII(int target) {
        if (target == 0 || target == 1) {
            return 1;
        }
        int tmp1 = 1;
        int tmp2 = 1;
        int result = 2;
        for (int i = 2; i < target;i++) {
            result += result;
        }
        return result;
    }

    public static void main(String[] args) {
        FogUpgrade fogUpgrade = new FogUpgrade();
        System.out.println(fogUpgrade.JumpFloorIII(4));
    }
}
```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

## 
> 题目描述


> 解决办法

```java

```

