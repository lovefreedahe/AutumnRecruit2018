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
7. euqals()和==区别 
8. **有哪些hash算法？冲突的解决方法？ Integer, Boolean, String 以及HashMap的 hashcode 方法的实现**

#### 反射
1. 反射用途和实现
2. java反射创建对象和new对象那个效率高，为什么
3. 


### 数据类型
#### 基本数据类型
1. char类型能不能转成int类型？能不能转化成string类型，能不能转成double类型
2. 例如： if(a+1.0=4.0)，这样做好吗？（浮点数误差）如果不好，怎么解决？
3. 数组实例化有几种方式？实例化之后还能不能改变长度？如何对一个数据进行反序？
4. Java中各种数据类型默认值
5. ++i和i++区别

#### 引用类型
##### String
1. String str=”aaa”,与String str=new String(“aaa”)一样吗？
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

### 装箱拆箱
1. 基本类型和包装类型比较
2. 

## 关键字
1. final,finally, finalize区别
2. static关键字有什么作用
3. super和this
4. sychronized
5. transient

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
5. Emuration和Iterator的区别
6. fast-failed和fast-safe
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
2. 优先队列和堆的区别


## java虚拟机
### 基础
1. 类加载机制和双亲委派模型？
2. 能不能自己写个类叫java.lang.System?
3. JVM对象分配原则
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
3. 常见的协议以及其传输方式(TCP还是UDP)
4. DNS协议以及解析过程
5. ARP协议
6. ICMP以及用途
7. 

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