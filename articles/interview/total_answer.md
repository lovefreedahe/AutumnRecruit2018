<!-- TOC -->

- [java基础](#java基础)
    - [基础](#基础)
        - [java性质](#java性质)
            - [反射](#反射)
        - [数据类型](#数据类型)
            - [基本数据类型](#基本数据类型)
            - [引用类型](#引用类型)
                - [String](#string)
        - [装箱拆箱](#装箱拆箱)
    - [关键字](#关键字)
    - [JDK源码](#jdk源码)
    - [数据结构](#数据结构)
        - [集合类](#集合类)
        - [树](#树)
        - [栈(Stack)](#栈stack)
        - [队列(Queue)](#队列queue)
    - [java虚拟机](#java虚拟机)
        - [基础](#基础-1)
        - [垃圾回收](#垃圾回收)
    - [线程](#线程)
        - [基础](#基础-2)
        - [线程安全(锁)](#线程安全锁)
    - [异常](#异常)
- [计算机网络](#计算机网络)
    - [基础](#基础-3)
    - [性质和架构](#性质和架构)
    - [协议](#协议)
        - [HTTP](#http)
        - [其他](#其他)
- [算法](#算法)
- [分布式](#分布式)
    - [基础](#基础-4)
    - [框架](#框架)
        - [Storm](#storm)
        - [Kafka](#kafka)
        - [Zookeeper](#zookeeper)
        - [HBase](#hbase)
- [设计模式](#设计模式)
- [Linux](#linux)
- [参考](#参考)

<!-- /TOC -->

# java基础
## 基础
### java性质
1. java是值传递还是引用传递
2. 构造方法能不能显示调用？
3. 重载和重写的区别
4. 内部类和内部静态类的区别
5. 抽象类和接口区别
6. 介绍一下注解。自定义注解的场景和实现。
    详细讲解见[此链接](https://blog.csdn.net/briblue/article/details/73824058/)
    注解可以理解为标签，自定义注解的实现方式如下：
    ```java
    @interface TestAnnotation {}
    @TestAnnotation
    public class Test {}
    ```
    元注解：一种基本注解，可以注解到注解上面。一共有五种：
    1. Target
    2. Inherited
    3. Repeatable
    4. Retention
    5. Documented
    ```java
    @interface TestAnnotation{}
    @TestAnnotation
    public class Test extends TestParent{
        @Deprecated
        public int testFunction() {
            return 0;
        }

        @Override
        public int function() {
            return 1;
        }

        private void testDeprecatedAnnotation() {
            this.testFunction();
        }

        @SuppressWarnings("deprecation")
        private void testSuppressWariningsAnnotation() {
            this.testFunction();
        }

        @SafeVarargs
        static void safeVarargsTest(List<String>... stringLists) {
            Object[] array = stringLists;
            List<Integer> tmpList = Arrays.asList(42);
            array[0] = tmpList; // Semantically  invalid , but compiles without warnings
            String s = stringLists[0].get(0); //ClassCastException at runtime
        }

    }
    ```
    
7. euqals()和==区别 
8. **有哪些hash算法？冲突的解决方法？ Integer, Boolean, String 以及HashMap的 hashcode 方法的实现**

#### 反射
1. 反射用途和实现
    * 原理
    要理解反射，首先要知道JVM是如何加载并使用类的，平常我们会通过new来实例化并调用一个对象，JVM启动时,代码中的类会被编译成.class文件，并被类加载器加载到方法区中。但是有时候我们会在程序运行时才能确定需要用到哪一个类，比如数据库对象，数据库类型那么多，我们总不能在程序启动前每一种都实例化一个对象吧？或者说突然接收到一个网络请求需要的类没有加载到JVM中，总不能把服务停了，然后new一下，再启动服务吧？
    所以就有了反射，反射可以在程序运行时，让JVM从服务器中得到类的信息，创建类的对象，调用类的方法。
2. java反射创建对象和new对象那个效率高，为什么
    参考[文章](http://www.xie4ever.com/2016/12/09/java%E5%8F%8D%E5%B0%84%E4%B8%BA%E4%BB%80%E4%B9%88%E6%85%A2%EF%BC%9F/)
    new效率高。
    1. 使用反射时，JVM无法知道你在干什么所以无法做优化，new的过程会进行优化
    2. 所有被注入和创建的东西都需要被找到。类根据类名称，方法根据匹配。
    3. 参数需要装箱和拆箱，并需要检查这个方法可不可以获得，能不能执行,传入参数类型检查等。

    虽然反射相对于new效率慢，但是这个慢是相对于机器来说的，平时我们使用的IDE大量使用了反射，但是我们的体验并不差，而且在分析XML和数据库读取问题上，牺牲的这点效率真的没什么。
3. 


### 数据类型
#### 基本数据类型
1. char类型能不能转成int类型？能不能转化成string类型，能不能转成double类型
    char 可以转换成int,double,float,为其对应assic码的值。不能转为String,可以通过一下方法来转换
    ```java
    char a = 'a';
    String str = String.valueOf(a);
    ```
2. 例如： if(a+1.0=4.0)，这样做好吗？（浮点数误差）如果不好，怎么解决？
BigDecimal
```java
BigDecimal b1 = new BigDecimal(Double.toString(1.01));
BigDecimal b2 = new BigDecimal(Double.toString(2.02));
System.out.println(b1.add(b2));
```
3. 数组实例化有几种方式？实例化之后还能不能改变长度？如何对一个数据进行反序？
    一旦定义就不能改变长度。
    ```java
    int[] a = new int[]{1, 2, 3};
    int[] b = new int[3];
    int[] c = new int[a.length];
    for(int i = 0;i < a.length;++i) {
        c[i] = a[a.length - i - 1];
    }
    ```
4. Java中各种数据类型默认值

序号 | 数据类型 | 大小(byte) | 封装类 | 默认值 | 范围 |
-- | -- | -- | -- | -- | -- |
1 | byte | 1 | Byte | 0 | -128~127 |
2 | short | 2 | Short | 0 | -32768~32767|
3 | int | 4 | Integer | 0 | -2147483648~2147483647 |
4 | long | 8 | Long | 0L |-9223372036854775808~9223372036854775807 |
5 | float | 4 | Float | 0.0f | 1.4E-45~3.4028235E38 |
6 | double | 8 | Double | 0.0 | 4.9E-324~1.7976931348623157E308 |
7 | char | 2 | Char | '/u0000'(null) | 0~65535 |
8 | boolean | 1 | Boolean | false | true or false |

引用类型的初始值都为null
5. ++i和i++区别
    ++i先自增后返回，i++先返回后自增。++i效率更高，因为i++需要创建新的返回值对象来保存i原来的值。
#### 引用类型
##### String
1. String str=”aaa”,与String str=new String(“aaa”)一样吗？
    不一样，前者数据保存在常量池中，后者创建了新对象
2. String的常用方法
    ```java
    charAt：返回指定索引处的字符
    indexOf()：返回指定字符的索引
    replace()：字符串替换
    trim()：去除字符串两端空白
    split()：分割字符串，返回一个分割后的字符串数组
    getBytes()：返回字符串的byte类型数组
    length()：返回字符串长度
    toLowerCase()：将字符串转成小写字母
    toUpperCase()：将字符串转成大写字符
    substring()：截取字符串
    format()：格式化字符串
    equals()：字符串比较
    ```
3. String反转
    StringBuffer和StringBuilder的reverse()函数
### 装箱拆箱
1. 基本类型和包装类型比较
    基本类型存储相对简单，效率较高；在Collection,Map中必须使用包装类型。
    比较：
    1. 存储位置：基本类型直接将变量值保存在栈中；包装类型的对象保存在堆中。
    2. 声明方式：基本类型直接赋值使用；包装类型需要new
    3. 初始值：基本类型如int,short等初始值为0；包装类型初始值为null
2. 

## 关键字
1. final,finally, finalize区别
2. static关键字有什么作用
    方便在没有new对象的情况下调用方法和变量，同样也可以使用对象调用静态方法和对象
    1. 修饰方法
    可以直接通过类名直接调用
    2. 修饰变量
    当且仅当类第一次加载时初始化，在内存中,静态对象被所有对象共享。
    3. 代码块
    类中的代码块只调用一次。
3. super和this
    
4. sychronized
    1. 代码块
    作用于整个对象
    2. 方法
    作用于整个对象
    3. 类
    作用于整个类
    4. 静态方法
    作用于整个类
5. transient
    被修饰的变量不会被序列化
## JDK源码
1. java最顶级父类，其常用方法以及访问权限, 有哪些是protected的
2. StringBuffer, StringBuilder对比
3. Math类有哪些常用方法
    ```java
    pow()：幂运算
    sqrt()：平方根
    round()：四舍五入
    abs()：求绝对值
    random()：生成一个0-1的随机数，包括0不包括1
    ```
4. Iterator和ListIterator区别
    1. Iterator可以用在Collection和Map，ListIterator只能遍历List
    2. Iterator单向，ListIterator双向遍历。
    3. Iterator只能删除值，ListIterator可以删除，添加元素和修改
    4. ListIterator可以定位当前索引的位置。
5. Enumeration和Iterator的区别
    1. Enumeration是废弃的接口，没有删除方法。
6. fast-fail和fast-safe
    java集合中的一种错误机制。
    * fast-fail
    在Iterator遍历集合的时候，如果元素在遍历期间被修改，会抛出ConcurrentModifyException,最好使用迭代器自带的remove方法。
    例如HashMap,Vector,ArrayList,HashSet
    * fast-safe
    对集合的修改操作都是基于复制-修改进行的,更容易造成内存溢出,java.util.concurrent包下的类都是fast-safe,CopyOnWriteArrayList
7. Comparable和Comparator区别

8. 

## 数据结构
### 集合类
1. List,Set, Map接口的对比
2. ArrayList, LinkedList和Vector对比
3. HashMap, ConcurrentHashMap和HashTable
4. 基于Set和Map接口的类的区别与联系
5. 双向链表
### 树
1. B树, B+树
2. 红黑树
3. 二叉查找树和二叉排序树

### 栈(Stack)

### 队列(Queue)
1. java中有哪些队列的实现？
    1. BlockingQueue
    2. LinkedBlockingQueue
    3. PriorityQueue
    4. 
2. 优先队列和堆的区别
    

## java虚拟机
### 基础
1. 类加载机制和双亲委派模型？
2. 能不能自己写个类叫java.lang.System?
3. JVM对象分配原则
4. JVM是如何对创建对象的过程优化的
    参考[文章](https://blog.csdn.net/qq_36807862/article/details/80942592)
### 垃圾回收
1. 如果对象引用置为null，是否会被立即回收？
2. jvm有哪些垃圾收集器？各自的特点以及互相之间的区别是什么？
3. GC的类别以及触发时机
4. finallize()调用时机和作用
5. JDK1.8 的GC有哪些改进
6. 有哪些GC算法？
7. JVM如何标记要回收的内存？


## 线程
进程与线程的区别
1. 进程是资源分配的基本单位，线程是程序计算的最小单位
2. 通信。进行通过IPC的方式(消息队列,信号量,管道,FIFO,共享内存)进行通信，线程通过共享内存，全局变量进行通信。
3. 一个进程至少有一个线程
### 基础
1. 创建线程的方式, 实现及比较
2. sleep(), join(), yield()区别
3. CountDownLatch和CycliBarrier各自的原理和区别
4. 线程池的原理, 几种实现方式以及生命周期
5. ThreadLocal原理
6. Semaphore, Exchanger
7. 线程同步的方法以及区别
    wait(), notify(), notifyAll()
8. Executor和Daemon
9. 对Runtime的了解


### 线程安全(锁)
1. Synchronize
2. volatile
3. Synchronize和Lock的区别
4. CAS乐观锁，ABA问题
5. 乐观锁的业务场景及实现方式
6. 如何却别N个线程访问N个资源同时又不导致死锁？
7. 多线程如何避免死锁？


## 异常
1. 异常分类(checked&&unchecked)
2. try catch finally语句中 return 返回值问题
3. Error和Exception区别
4. 处理完异常后Exception对象会怎样？

# 计算机网络
## 基础
1. TCP和UDP区别以及各自的报头格式
2. OSI协议、五层协议以及TCP/IP协议
3. TCP三次握手和四次挥手的具体过程
4. 为什么TCP要三次握手和四次挥手？
5. TCP四次挥手最后的timewait时间以及原因
6. TCP如何保证可靠传输
7. 打开浏览器输入URL地址道开始浏览网页的过程发生了什么？
7. IP地址编址方式和子网划分
8. 交换机和路由器的区别(了解即可)
## 性质和架构
1. SESSION和COOKIE区别
2. java服务器网络开发时， 说明阻塞(blocking)/非阻塞(non-blocking)与同步(sync)/异步(async)IO的区别

## 协议
### HTTP
1. HTTP请求的POST和GET的区别
2. HTTP1.0, 1.1, 2.0 和HTTPS
### 其他
1. 常见的协议以及其传输方式(TCP还是UDP)
2. DNS协议以及解析过程
3. ARP协议
4. ICMP以及用途
5. 

# 算法
1. 常见的排序算法实现、性质以及应用场景
2. 将两个大文件中相同的字符串打印出来
3. 

# 分布式
## 基础
1. 分布式一致性协议有哪些？各自的实现过程和比较
2. 如何实现分布式缓存？
3. 什么是CAP？如何权衡？
4. 分布式锁有哪些？各自的性质

## 框架
### Storm
1. 数据一致性
2. 亿级数据(比如IP地址)去重
3. 求TopN
4. Worker, Executor, Task之间的关系

### Kafka
1. 数据一致性
2. 底层结构，能够自己画图说明
3. kafka(消息队列)和RPC的区别和各自的应用场景
4.  


### Zookeeper
1. ZAB协议，leader选举过程
2. 在HBase和Kafka中的应用
3. ZK如何保证数据一致性的？
4. znode有哪些类型？watcher的监听通知是永久的嘛？
5. 临时节点什么时候会被删除？


### HBase
1. 数据保存和读取的过程， HBase为什么快？
2. 架构图(能够用手画出来)
3. rowkey如何设计？
4. 每天百亿级数据存入HBase，如何保证数据的存储正确和在规定的时间录入完毕？
5. compaction方式哪有几种？什么时候使用？

# 设计模式
1. 用过哪些设计模式？实际应用场景？
2. 对设计模式的看法和认知？

# Linux
1. 如何查看大文件的信息？
2. shell脚本如何查看传入参数的个数
3. 


# 参考
1. [秒懂，Java 注解 （Annotation）你可以这样学](https://blog.csdn.net/briblue/article/details/73824058/)
2. [反射](https://www.zhihu.com/question/24304289)