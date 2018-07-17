## 与java有关的面试题目

<!-- TOC -->

- [与java有关的面试题目](#与java有关的面试题目)
- [JVM相关](#jvm相关)
    - [能不能自己写个类叫java.lang.System？](#能不能自己写个类叫javalangsystem)
    - [什么情况下会产生Minor GC?](#什么情况下会产生minor-gc)
    - [什么情况下会产生Full GC?](#什么情况下会产生full-gc)
- [Java基础](#java基础)
    - [接口和抽象类的区别](#接口和抽象类的区别)
- [操作系统](#操作系统)
    - [进程和线程的却别](#进程和线程的却别)

<!-- /TOC -->

## JVM相关
### 能不能自己写个类叫java.lang.System？

* 答案：
    通常不可以，但可以采取另类方法达到这个需求。 
* 解释：
    为了不让我们写System类，类加载采用委托机制，这样可以保证爸爸们优先，爸爸们能找到的类，儿子就没有机会加载。而System类是 __Bootstrap__ 加载器加载的，就算自己重写，也总是使用Java系统提供的System，自己写的System类根本没有机会得到加载。
* 方法
    我们可以自己定义一个类加载器来达到这个目的，为了避免双亲委托机制，这个类加载器也必须是特殊的。由于系统自带的三个类加载器都加载特定目录下的类，如果我们自己的类加载器放在一个特殊的目录，那么系统的加载器就无法加载，也就是最终还是由我们自己的加载器加载。
    
### 什么情况下会产生Minor GC?
Eden区满时
### 什么情况下会产生Full GC?
详细请看[Java虚拟机]()中JVM内存结构的java堆
<div align="center"><img src="../../resources/images/java/jvm/ppt_img.gif"></div></br> 

* System.gc() 
    建议JVM Full GC，但是不一定，能不用尽量不用。
* 老年代空间不足
    * 原因
        老年代空间只有在新生代对象转入及创建为大对象、大数组时才会出现不足的现象，当执行Full GC后空间仍然不足，则抛出如下错误：
        ```shell
        java.lang.OutOfMemoryError: Java heap space 
        ```
    * 措施
        为避免以上两种状况引起的Full GC，调优时应尽量做到让对象在Minor GC阶段被回收、让对象在新生代多存活一段时间及不要创建过大的对象及数组。
* 永久区空间不足
    * 原因
        Permanet Generation中存放的为一些class的信息、常量、静态变量等数据，当系统中要加载的类、反射的类和调用的方法较多时，Permanet Generation可能会被占满，在未配置为采用CMS GC的情况下也会执行Full GC。如果经过Full GC仍然回收不了，那么JVM会抛出如下错误信息：
        ```shell
        java.lang.OutOfMemoryError: PermGen space 
        ```
    * 措施
        为避免Perm Gen占满造成Full GC现象，可采用的方法为增大Perm Gen空间或转为使用CMS GC。
* CMS GC时出现promotion failed和concurrent mode failure
    * 介绍
        对于采用CMS进行老年代GC的程序而言，尤其要注意GC日志中是否有promotion failed和concurrent mode failure两种状况，当这两种状况出现时可能会触发Full GC。
    * 原因
        promotion failed是在进行Minor GC时，survivor space放不下、对象只能放入老年代，而此时老年代也放不下造成的；concurrent mode failure是在

        执行CMS GC的过程中同时有对象要放入老年代，而此时老年代空间不足造成的（有时候“空间不足”是CMS GC时当前的浮动垃圾过多导致暂时性的空间不足触发Full GC）。
    * 措施
        增大survivor space、老年代空间或调低触发并发GC的比率
    
* 由Eden区、From Survivor 区复制时，对象大小大于To Survivor可用内存，则把该对象转存到老年代，且老年代的可用内存小于该对象大小
        
## Java基础
### 接口和抽象类的区别
* 接口不能实例化，抽象类可以
* 一个类可以继承(implements)多个接口，但只能继承(extends)一个类
* 接口中声明的变量默认为final，抽象类中的可以不是final
* 接口中的方法都是public，抽象类的方法可以是private, protected或者public
* 接口中方法都是abstract, 抽象类的可以为abstract也可以不是
* 抽象类可以在不提供方法实现的情况下实现接口


## 操作系统
### 进程和线程的却别
* 地址空间和其他资源：
    进程是相互独立的应用，进程可以有多个线程，进程内的线程在其他进程中不可见
* 通信：
    进程间通信IPC，线程间可以通过全局变量来通信---需要进程同步和互斥手段保持数据一致性。
* 调度和切换
    线程的上下文切换比进程快得多
* 多线程OS中，进程是不可执行的实体。