# 剑指offer

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

## 
> ### 题目描述  
输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
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

