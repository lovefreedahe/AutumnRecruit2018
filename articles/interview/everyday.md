# 2018.8.24
## 线程安全
1. 什么是可重入锁？
java.util.concurrent包中的Lock框架，用java语言定义的锁，而不是synchronized这种语言特性，ReetrantLock是Lock的一种实现，可以实现锁投票、公平锁、可中断锁、定时锁等功能。**在激烈资源竞争的时候可以实现更佳的性能，JVM在资源调度上可以花更少的时间。**
reetrant指的是每当加一个锁count加一，类似于synchronized的语义，如果lock多次，unlock就需要调用对应的次数。
可重入锁指的是同一个线程可以在没有释放该锁之前再次加锁。
2. 如何设置公平锁？
```java
new ReetrantLock(true);
```

3. AQS(AbstractQueuedSynchronizer)队列同步器
类似于在处理synchronized时，JVM使用的monitor对象，会有一个state表示是否有资源占用当前资源。

```java
/**
 * AQS抽象类
 */
public abstract class AbstractQueuedSynchronizer
    extends AbstractOwnableSynchronizer{
//指向同步队列队头
private transient volatile Node head;

//指向同步的队尾
private transient volatile Node tail;

//同步状态，0代表锁未被占用，1代表锁已被占用
private volatile int state;

//省略其他代码......
}
```

4. 锁
    1. 自旋锁和互斥锁
    自旋锁：如果资源被占用，当前线程就会循环查看资源是否被释放。效率高，适用于时间较短的情况。
    互斥锁：sleep and waiting，如果资源被占用，则休眠当前线程,并放于等待队列中。
    2. 乐观锁、悲观锁、共享锁、排它锁
    乐观锁：乐观的认为没有线程占用对当前资源，在修改资源之前判断是否与期望值相同，如果相同则修改，如果不同则放弃修改。ABA问题，加入版本号，每次修改，版本号加一。
    悲观锁：悲观的认为当前资源会被强占，每次操作都需要获取锁。
    共享锁：多个线程可以共享一个锁。
    排它锁：只能有一个线程获取锁。
    3. synchronized和lock区别
        * synchronized执行结束自动释放锁，lock需要显示调用unlock释放锁。
        * lock可以主动放弃锁
        * lock可以实现公平锁，提高多线程共同工作的效率

## 设计模式
1. 设计模式的原则
    1. 单一职责原则
    类的功能尽可能单一,承担的职责越多，复用的可能就越小，职责间的耦合度也会越高
    2. 开闭原则
    对修改关闭，对扩展开放。
    3. 依赖倒置原则
    针对接口编程
    4. 里氏替换原则
    所有引用父类的地方必须能够透明的使用子类的对象
    5. 接口隔离原则
    使用多个接口，而不是单一的接口。
    6. 迪米特法则
    一个软件实体应该尽可能少的与其他实体发生相互作用。降低系统的耦合度

## Linux
1. Buffere和Cache的区别
    * 都是占用内存
    * Buffer缓冲区是读写缓冲区，是针对内存和磁盘交换速度而设计的。在内存中写一个很大的block块，在一起写到磁盘中。一般用在写入磁盘。
    * Cache高速缓存是用于Cpu和内存之间的缓冲。主要是CPU太快,内存慢，且有些值访问次数很多，所以放入cache中。Cache一般应用在IO请求上

## 操作系统
1. 大端(big-endian)和小端(little-endian)
    * 小端：低字节数据存储在内存低地址处，高字节数据存储在内存高地址处
    * 大端：低字节数据存储在内存高地址处，高字节数据存储在内存低地址处

## 计算机网络
1. DNS什么时候使用TCP传输
当传输数据超过512个字节(UDP报文最大值)会用TCP
2. URI和URL的区别
URI是唯一的标识符类似于人的身份证号，URL是唯一的一个地址：湖北省/武汉市/洪山区/华中科技大学/张三 这就是URL。
3. 
