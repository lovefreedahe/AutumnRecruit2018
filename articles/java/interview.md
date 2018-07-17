## 与java有关的面试题目

<!-- TOC -->

- [与java有关的面试题目](#与java有关的面试题目)
- [JVM相关](#jvm相关)
    - [能不能自己写个类叫java.lang.System？](#能不能自己写个类叫javalangsystem)
    - [什么情况下会产生Minor GC?](#什么情况下会产生minor-gc)
    - [什么情况下会产生Full GC?](#什么情况下会产生full-gc)
- [Java基础](#java基础)
    - [接口和抽象类的区别](#接口和抽象类的区别)
    - [sychronized方法和代码块](#sychronized方法和代码块)
    - [Iterator和ListIterator的区别是什么？](#iterator和listiterator的区别是什么)
    - [快速失败(fast-failed)和快速安全(fast-safe)](#快速失败fast-failed和快速安全fast-safe)
    - [HashMap和HashTable却别](#hashmap和hashtable却别)
- [操作系统](#操作系统)
    - [进程和线程的却别](#进程和线程的却别)
    - [死锁](#死锁)
    - [如何确保N个线程可以访问N个资源同时又不导致死锁？](#如何确保n个线程可以访问n个资源同时又不导致死锁)
    - [java.util.Collection](#javautilcollection)

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

### sychronized方法和代码块
* 同步方法
    通过this找到当前对象,将当前对象上锁
* 同步代码块
     synchronized（object）{代码内容}。可以指定任意一个对象,更加细粒度。

### Iterator和ListIterator的区别是什么？
* Iterator可用来遍历Set和List集合，但是ListIterator只能用来遍历List。
* Iterator对集合只能是前向遍历，ListIterator既可以前向也可以后向。
* ListIterator实现了Iterator接口，并包含其他的功能，比如：增加元素，替换元素，获取前一个和后一个元素的索引，等等。 

### 快速失败(fast-failed)和快速安全(fast-safe)
* fail-fast
    * 表现
    再用迭代器遍历集合中的对象时，如果遍历过程中对集合中对象的结构进行了修改(增加、删除)，则会跑出ConcurrentModifyExecption。
    * 原因
    迭代器在遍历的时候，会使用modCount的变量，当遍历过程中对象结构发生变化就会改变modCount的值，当迭代器使用hasNext()/next()遍历下一个对象的时候，会比较expectedModCount与modCount的值，如果不匹配，就会跑出异常。
    * 注意
    这里异常的抛出条件是检测到 modCount！=expectedmodCount 这个条件。如果集合发生变化时修改modCount值刚好又设置为了expectedmodCount值，则异常不会抛出。因此，不能依赖于这个异常是否抛出而进行并发操作的编程，这个异常只建议用于检测并发修改的bug。
    * 场景
    java.util包下的集合类都是快速失败的，不能在多线程下发生并发修改（迭代过程中被修改）。
* fail-safe
    * 表现
    采用安全失败机制的集合容器，在遍历时不是直接在集合元素上访问的，而是先生成集合对象的拷贝,在拷贝的集合上遍历。
    * 原理
    由于在遍历时对原集合进行了拷贝，所以修改原集合的值并不会检测到,所以不会触发ConcurrentModifyExecption。
    * 缺点
    基于拷贝内容的优点是避免了Concurrent Modification Exception，但同样地，迭代器并不能访问到修改后的内容，即：迭代器遍历的是开始遍历那一刻拿到的集合拷贝，在遍历期间原集合发生的修改迭代器是不知道的。
    所以ConcurrentHashMap是弱一致性的。
    * 场景 
    java.util.concurrent包下的容器都是安全失败，可以在多线程下并发使用，并发修改。

### HashMap和HashTable却别
* HashMap允许key或值为null，HashTable不行
* HashTable线程安全
* HashTable实现了对键的列举(Enumeration)
## 操作系统
### 进程和线程的却别
* 地址空间和其他资源：
    进程是相互独立的应用，进程可以有多个线程，进程内的线程在其他进程中不可见
* 通信：
    进程间通信IPC，线程间可以通过全局变量来通信---需要进程同步和互斥手段保持数据一致性。
* 调度和切换
    线程的上下文切换比进程快得多
* 多线程OS中，进程是不可执行的实体。

### 死锁
死锁是指多个进程因竞争资源而造成的一种僵局（互相等待），若无外力作用，这些进程都将无法向前推进。
* 互斥条件
    一段时间内一个资源只能被一个进程所占有，如果有其他进程请求就只能等待。
* 不可剥夺：
    一个进程获得的资源在为释放之前，不能被其他进程强行占有。
* 请求和保持条件
    进程已经持有了一个资源，此时还请求另外一个被其他进程占有的资源，就会进入阻塞状态，已有的资源也不会以释放。
* 循环等待
    存在一种进程资源的循环等待链，链中每一个进程已获得的资源同时被 链中下一个进程所请求。

### 如何确保N个线程可以访问N个资源同时又不导致死锁？
使用多线程的时候，一种非常简单的避免死锁的方式就是：指定获取锁的顺序，并强制线程按照指定的顺序获取锁。因此，如果所有的线程都是以同样的顺序加锁和释放锁，就不会出现死锁了。

### java.util.Collection
<div align="center"><img src="../../resources/images/java/datastructure/collection.png"></div></br> 

<div align="center"><img src="../../resources/images/java/datastructure/map.png"></div></br> 

