
<!-- TOC -->

- [概述](#概述)
    - [创建型模式](#创建型模式)
    - [行为型模式](#行为型模式)
    - [结构型模式](#结构型模式)
- [创建型模式](#创建型模式-1)
    - [单例模式](#单例模式)
        - [1. 急切初始化](#1-急切初始化)
        - [2. 静态块初始化](#2-静态块初始化)
        - [3. 懒人模式](#3-懒人模式)
        - [4. 线程安全模式](#4-线程安全模式)
        - [5. Bill Pugh模式](#5-bill-pugh模式)
        - [防止反射破坏单例模式](#防止反射破坏单例模式)
        - [防止序列化破坏单例模式](#防止序列化破坏单例模式)
    - [2. 工厂方法模式（Factory Method Pattern）](#2-工厂方法模式factory-method-pattern)
        - [简单工厂模式](#简单工厂模式)
        - [工厂方法模式](#工厂方法模式)
    - [5. 原型模式(Prototype Pattern)](#5-原型模式prototype-pattern)
        - [介绍](#介绍)
        - [何时使用](#何时使用)
        - [优点](#优点)
        - [实现](#实现)
- [行为型模式](#行为型模式-1)
    - [1. 责任链模式(Chain of Responsibility Pattern)](#1-责任链模式chain-of-responsibility-pattern)
        - [介绍](#介绍-1)
        - [何时使用](#何时使用-1)
        - [优点](#优点-1)
        - [实现](#实现-1)
    - [2. 命令模式(Command Pattern)](#2-命令模式command-pattern)
        - [介绍](#介绍-2)
        - [何时使用](#何时使用-2)
        - [优点](#优点-2)
        - [实现](#实现-2)
    - [3. 解释器模式(Interpreter Pattern)](#3-解释器模式interpreter-pattern)
        - [介绍](#介绍-3)
        - [何时使用](#何时使用-3)
        - [优点](#优点-3)
        - [角色](#角色)
        - [步骤](#步骤)
        - [实现](#实现-3)
    - [4. 迭代器模式(Iterator Pattern)](#4-迭代器模式iterator-pattern)
        - [介绍](#介绍-4)
        - [何时使用](#何时使用-4)
        - [优点](#优点-4)
        - [实现](#实现-4)
    - [5. 中介者模式(Mediator Pattern)](#5-中介者模式mediator-pattern)
        - [介绍](#介绍-5)
        - [何时使用](#何时使用-5)
        - [优点](#优点-5)
        - [实现](#实现-5)
    - [6. 备忘录模式(Memento Pattern)](#6-备忘录模式memento-pattern)
        - [介绍](#介绍-6)
        - [何时使用](#何时使用-6)
        - [优点](#优点-6)
    - [角色](#角色-1)
        - [实现](#实现-6)
    - [7. 观察者模式(Observer Pattern)](#7-观察者模式observer-pattern)
        - [介绍](#介绍-7)
        - [何时使用](#何时使用-7)
        - [优点](#优点-7)
        - [实现](#实现-7)
    - [8. 状态模式(State Pattern)](#8-状态模式state-pattern)
        - [介绍](#介绍-8)
        - [何时使用](#何时使用-8)
        - [优点](#优点-8)
        - [实现](#实现-8)
    - [9. 策略模式(Strategy Pattern)](#9-策略模式strategy-pattern)
        - [介绍](#介绍-9)
        - [何时使用](#何时使用-9)
        - [角色](#角色-2)
        - [优点](#优点-9)
        - [实现](#实现-9)
    - [10. 模板方法模式(Template Method Pattern)](#10-模板方法模式template-method-pattern)
        - [介绍](#介绍-10)
        - [何时使用](#何时使用-10)
        - [优点](#优点-10)
        - [实现](#实现-10)
    - [11. 访问者模式(Visitor Pattern)](#11-访问者模式visitor-pattern)
        - [介绍](#介绍-11)
        - [何时使用](#何时使用-11)
        - [优点](#优点-11)
        - [实现](#实现-11)
            - [分派](#分派)
- [结构型模式](#结构型模式-1)
    - [1. 适配器模式(Adapter Pattern)](#1-适配器模式adapter-pattern)
        - [介绍](#介绍-12)
        - [何时使用](#何时使用-12)
        - [优点](#优点-12)
        - [实现](#实现-12)
    - [2. 组合模式(Composite Pattern)](#2-组合模式composite-pattern)
        - [介绍](#介绍-13)
        - [何时使用](#何时使用-13)
        - [优点](#优点-13)
        - [实现](#实现-13)
    - [3. 代理模式(Proxy Pattern)](#3-代理模式proxy-pattern)
        - [介绍](#介绍-14)
        - [何时使用](#何时使用-14)
        - [优点](#优点-14)
        - [实现](#实现-14)
    - [4. 享元模式(Plyweight Pattern)](#4-享元模式plyweight-pattern)
        - [介绍](#介绍-15)
        - [何时使用](#何时使用-15)
        - [优点](#优点-15)
        - [角色](#角色-3)
        - [实现](#实现-15)
    - [5. 外观模式](#5-外观模式)
        - [介绍](#介绍-16)
        - [何时使用](#何时使用-16)
        - [优点](#优点-16)
        - [实现](#实现-16)
    - [6. 桥接模式(Bridge Pattern)](#6-桥接模式bridge-pattern)
        - [介绍](#介绍-17)
        - [何时使用](#何时使用-17)
        - [优点](#优点-17)
        - [实现](#实现-17)
    - [7. 装饰模式(Decorator Pattern)](#7-装饰模式decorator-pattern)
        - [介绍](#介绍-18)
        - [何时使用](#何时使用-18)
        - [优点](#优点-18)
        - [实现](#实现-18)
- [参考](#参考)

<!-- /TOC -->

## 概述
 设计模式是针对某一类问题的最优解决方案，是从许多优秀的软件系统中总结出的。Java中设计模式（java design patterns）通常有23种。模式可以分成3类：
 * 创建型
 * 行为型
 * 结构型。

### 创建型模式
创建型模式涉及对象的实例化，特点是不让用户代码依赖于对象的创建或排列方式，避免用户直接使用new创建对象。创建型模式有以下5个：
* 工厂方法模式
* 抽象工厂方法模式
* 生成器模式
* 原型模式
* 单例模式
### 行为型模式
行为型模式涉及怎样合理的设计对象之间的交互通信，以及怎样合理为对象分配职责，让设计富有弹性，易维护，易复用。行为型模式有以下11个：
* 责任链模式
* 命令模式
* 解释器模式
* 迭代器模式
* 中介者模式
* 备忘录模式
* 观察者模式
* 状态模式
* 策略模式
* 模板方法模式
* 访问者模式

### 结构型模式
结构型模式涉及如何组合类和对象以形成更大的结构，和类有关的结构型模式涉及如何合理使用继承机制；和对象有关的结构型模式涉及如何合理的使用对象组合机制。结构型模式有以下7个：
* 适配器模式
* 组合模式
* 代理模式
* 享元模式
* 外观模式
* 桥接模式
* 装饰模式

模式中涉及的重要角色，会在描述中（加粗字体）介绍出来。下面就逐一介绍。


## 创建型模式
### 单例模式
保证一个类仅有一个实例，并提供一个访问它的全局访问点。
* 何时使用
    当系统需要某个类只有一个实例的时候
* 优点
    * 单例模式的类唯一实例由其本身控制，可以很好的控制用户何时访问它。
    * 单例模式概念很简单，而且也比较常用。
    * 在使用这个模式的时候，我们要考虑是否会在多线程中使用

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

### 2. 工厂方法模式（Factory Method Pattern）
别名：虚拟构造（Another Name：Virtual Constructor）。
定义一个用于创建对象的接口，让子类决定实例化哪一个类。工厂方法使一个类的实例化延迟到其子类。
* 何时使用
    * 用户需要一个类的子类的实例，但不希望与该类的子类形成耦合
    * 用户需要一个类的子类的实例，但用户不知道该类有哪些子类可用

* 优点
    * 使用工厂方法可以让用户的代码和某个特定类的子类的代码解耦
    * 工厂方法使用户不必知道它所使用的对象是怎样被创建的，只需知道该对象有哪些方法即可。

#### 简单工厂模式
介绍工厂方法模式前，先介绍一下简单工厂模式，简单工厂模式也是一种工厂方法模式。
简单工厂模式又称静态工厂方法模式。从命名上就可以看出这个模式一定很简单。它存在的目的很简单：定义一个用于创建对象的接口。
__适用场景__：如果一个一些对象（产品），已经确定了并不易改变和添加新的产品，那么久可以使用简单工厂模式。下面就是简单工厂的例子：
```java
//演示简单工厂
public class SimpleFactory {
    public static void main(String[] args) {
        Factory factory = new Factory();
        try {
            Apple apple = factory.produce("macbook");
            apple.run();
            apple = factory.produce("iphone");
            apple.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//抽象产品
public interface Apple {
    void run();
}
//具体产品X2
public class MacBook implements Apple{
    @Override
    public void run() {
        System.out.println("Produce a macbook.");
    }
}
public class IPhone implements Apple{
    @Override
    public void run() {
        System.out.println("Produce an iphone.");
    }
}

//工厂
class Factory {
    Apple produce(String product) throws Exception{
        if (product.equals("macbook")) {
            return new MacBook();
        } else if (product.equals("iphone")) {
            return new IPhone();
        }
        throw new Exception("No such product!");
    }
}

```

#### 工厂方法模式
简单工厂模式是不易维护的，如果需要添加新的产品，则整个系统都需要修改。
如果我们需要添加诸如PRO7、PRO8等产品，直接在工程类中添加即可。但是如果这时候根部不知道还有什么产品，只有到子类实现时才知道，这时候就需要工厂方法模式。
而在实际应用中，很可能产品是一个多层次的树状结构。由于简单工厂模式中只有一个工厂类来对应这些产品，所以实现起来是比较麻烦的，那么工厂方法模式正式解决这个问题的，下面就介绍工厂方法模式。

工厂方法模式去掉了简单工厂模式中工厂方法的静态属性，使得它可以被子类继承。这样在简单工厂模式里集中在工厂方法上的压力可以由工厂方法模式里不同的工厂子类来分担。
针对上面的例子，如果使用工厂方法模式，即将工厂定义为一个接口，然后由具体的工厂来确定需要生成什么样的产品，为了与简单工厂比较，代码：
```java
//工厂方法模式
public class FactoryMethod {
    public static void main(String[] args) {
        IFactory macbook = new MacFactory();
        macbook.produce().run();
        IFactory phone = new PhoneFactory();
        phone.produce().run();
    }
}
interface IFactory {
    Apple produce();
}

class MacFactory implements IFactory {
    @Override
    public Apple produce() {
        return new MacBook();
    }
}

class PhoneFactory implements IFactory {
    @Override
    public Apple produce() {
        return new IPhone();
    }
}
```
如果了解Java的集合框架，那么它就是一个很好的例子：
Java中的Collection接口的实现都能通过iterator()方法返回一个迭代器，而不同方式实现的迭代器都在该实现中以内部类的方式对Iterator接口实现的，然后通过iterator()方法返回。那么，这个iterator()方法就是一种工厂方法。

可以看到，在这里抽象产品是Iterator接口，具体产品就是Collection接口的实现中对Iterator接口的实现，构造者是Collection接口，其提供的工厂方法就是Iterator iterator();，具体构造者就是Collection的实现。而工厂方法模式的结构，也就是由前面加粗的4部分组成。
如果对Java容器不熟悉，下面再提供一个例子(模仿Iterator，其实顺便也介绍了Iterator)：
如果有多种数据结构要遍历，我们就需要一种用于遍历不同结构的工具，首先我们就需要为这个工具定义一个接口（抽象产品），用于描述如何来遍历：
```java
//只是需要遍历一堆数据，那么只需要2个方法就可以了
public interface Iterator<T> {  
    boolean hasNext();  //是否还有下一个元素
    T next();       //得到下一个元素
}
```
然后就是我们要遍历的目标，而这些目标此处我们暂定为列表，这就是构造者：
```java
//便于介绍，不做多的操作
public interface List<T> {
    Iterator<T> iterator();   //返回一个遍历器
    boolean add(T t);   //添加元素到列表
}
```
对于List可能有多种实现方式，比如数组和链表，此处就简陋的介绍一下，而这些就是具体构造者，而里面有遍历器的具体实现（具体产品），此处以内部类的形式放到了List的实现（具体构造者）里面，也完全可以修改代码将遍历器的实现（具体产品）独立出来：

* ArrayList的实现： 
```java
//方便演示而实现的简陋的数组list
public class ArrayList<T> implements List<T>{
    private int size;               //存放的元素个数,会默认初始化为0
    private Object[] defaultList;   //使用数组存放元素
    private static final int defaultLength = 10;//默认长度
    public ArrayList(){             //默认构造函数
    }
        defaultList = new Object[defaultLength];
    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }
    //添加元素
    @Override
    public boolean add(T t) {
        if(size<=defaultLength){
            defaultList[size++] = t;
            return true;
        }
        return false;
    }
    //遍历器（具体产品）
    private class MyIterator implements Iterator<T>{
        private int next;
        @Override
        public boolean hasNext() {
            return next<size;
        }
        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            return (T) defaultList[next++];
        }
    }
}
```

* 链表实现
```java
//方便演示而实现的简陋的单向链表list
public class LinkList<T> implements List<T>{
    private int size;   //存放的元素个数,会默认初始化为0
    private Node<T> first;    //首节点，默认初始化为null
    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }
    @Override
    public boolean add(T t) {
        if(size==0){
            first = new Node<T>(t,null);
            size++;
            return true;
        }
        Node<T> node = first;
        while(node.next!=null)
            node = node.next;
        node.next = new Node<T>(t,null);
        size++;
        return true;
    }
    //链表节点
    private static class Node<T>{
        T data;
        Node<T> next;
        Node(T data,Node<T> next){
            this.data = data;
            this.next = next;
        }
    }
    //遍历器
    private class MyIterator implements Iterator<T>{
        private Node<T> next; //下一个节点
        MyIterator(){
            next = first;
        }
        @Override
        public boolean hasNext() {
            return next != null;
        }
        @Override
        public T next() {
            T data = next.data;
            next = next.next;
            return data;
        }
    }
}
```
 使用上述代码（模式的使用）：
 ```java
public class TestUse {
    public static void main(String args[]){
        //分别定义两种结构
        List<Integer> array = new ArrayList<Integer>();
        List<Integer> link = new LinkList<Integer>();
        //添加数据
        for(int i = 1;i < 8; i++){
            array.add(i);
            link.add(i);
        }
        //获得迭代器
        Iterator<Integer> ai = array.iterator();
        Iterator<Integer> li = link.iterator();
        //遍历并输出
        while(ai.hasNext())
            System.out.print(ai.next());
        System.out.println();
        while(li.hasNext())
            System.out.print(li.next());
    }
}
```
控制台会输出：
```shell
1234567
1234567
```
这就是工厂方法模式，其中遍历器也算是一种迭代器设计模式，后面会介绍。这里只是其中一种应用的举例，当一个接口的一系列实现需要另外的对象对其进行相同操作时，我们就可以这样用：在这个接口中定义返回另外一个对象的方法（工厂方法），然后再在这个接口的实现中，返回对其操作的对象。

上面这个例子会在迭代器模式中给出完整的实现代码。

一抽象产品类派生出多个具体产品类；一抽象工厂类派生出多个具体工厂类；每个具体工厂类只能创建一个具体产品类的实例。 即定义一个创建对象的接口（即抽象工厂类），让其子类（具体工厂类）决定实例化哪一个类（具体产品类）。“一对一”的关系。

与简单工厂间的取舍：工厂方法模式和简单工厂模式在定义上的不同是很明显的。工厂方法模式的核心是一个抽象工厂类,而不像简单工厂模式, 把核心放在一个实类上。工厂方法模式可以允许很多实的工厂类从抽象工厂类继承下来, 从而可以在实际上成为多个简单工厂模式的综合,从而推广了简单工厂模式。 反过来讲,简单工厂模式是由工厂方法模式退化而来。设想如果我们非常确定一个系统只需要一个实的工厂类, 那么就不妨把抽象工厂类合并到实的工厂类中去。而这样一来,我们就退化到简单工厂模式了。

可以看出工厂方法的加入，使得对象的数量成倍增长。当产品种类非常多时，会出现大量的与之对应的工厂对象，这不是我们所希望的。

如果再分得详细一点，一个工厂可能不只是生产手机（如小米除了手机，连电饭锅都有），但有得工厂智能生成低端的产品，而大一点的工厂可能通常是生成更高端的产品。所以一个工厂是不够用了，这时，就应该使用抽象工厂来解决这个问题。

### 3. 抽象工厂方法模式（Abstract Factory Pattern）
别名：配套（Another Name：Kit）
上述生成魅族产品的例子中，我们只生产了手机，但是它不止有手机一种产品，可能还有其他的，比如耳机，为了还可以生成耳机，我们需要对上例进行扩展。

我们先给出上面生成手机的例子的扩展后的抽象工厂模式代码，以比较这几种模式：
```java
//抽象工厂模式
public class AbstractoryFactory {
    public static void main(String[] args) {
        IFactory bigfactory = new BigFactory();
        IFactory smallfactory = new BigFactory();
        bigfactory.produceApple().run();
        bigfactory.produceHeadset().play();
        smallfactory.produceApple().run();
        smallfactory.produceHeadset().play();
    }
}
//抽象产品*2
interface Headset{
    void play();
}
//抽象产品
public interface Apple {
    void run();
}
//具体产品*2*2
public class MacBook implements Apple{
    @Override
    public void run() {
        System.out.println("Produce a macbook.");
    }
}
public class IPhone implements Apple{
    @Override
    public void run() {
        System.out.println("Produce an iphone.");
    }
}

public class Sony implements HeadSet {
    @Override
    public void play() {
        System.out.println("Play with Sony head set.");
    }
}
public class Beats implements HeadSet {
    @Override
    public void play() {
        System.out.println("Play with Beats head set.");
    }
}
//抽象工厂
interface IFactory {
    Apple produceApple() ;
    HeadSet produceHeadset();
}
//具体工厂*2
class BigFactory implements IFactory {
    @Override
    public Apple produceApple() {
        return new MacBook();
    }

    @Override
    public HeadSet produceHeadset() {
        return new Sony();
    }
}

//具体工厂*2
class SmallFactory implements IFactory {
    @Override
    public Apple produceApple() {
        return new IPhone();
    }

    @Override
    public HeadSet produceHeadset() {
        return new Beats();
    }
}
```

 在抽象工厂模式中，抽象产品 (AbstractProduct) 可能是一个或多个，从而构成一个或多个产品族(Product Family)。 在只有一个产品族的情况下，抽象工厂模式实际上退化到工厂方法模式（不如上例减去耳机这种产品，就回到工厂方法模式了）。

这样举例子其实很空洞，这里只是为了比较三种模式，给出抽象的例子才更容易看出区别。

那么上例中实际应用就是生产迭代器的例子，这里也对齐扩展来介绍抽象工厂模式。Iterator迭代器是Collection专属的，但是现在我们希望能生产Map的迭代器，我们都知道，Map不是继承自Collection的，遍历的方式是不一样的，这就相当于2个产品族，接下来我们就要来实现它。

为了演示我们如果实现这个同时能生产Map和Collection的迭代器，我会将例子一步步贴出来：

首先是抽象产品，用来描述迭代器的公共接口：
```java
//抽象产品
public interface IIterator<T> {
    boolean hasNext();
    Object next();
}
```
然后是抽象工厂，用来返回不同迭代器：
```java
//抽象工厂
public interface IIteratorFactory<T> {
    IIterator<T> iteratorMap(Map<T,Object> m);
    IIterator<T> iteratorCollection(Collection<T> c);
}
```

接下来是具体产品。
Collection的迭代器（具体产品）：

```java
//具体产品，Collection迭代器(用到了代理模式)
public class IteratorCollection<T> implements IIterator<T>{
    Iterator<T> iterator;
    public IteratorCollection(Collection<T> c){
        iterator = c.iterator();
    }
    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }
    @Override
    public T next() {
        return iterator.next();
    }
}
```
Map的迭代器（具体产品）：

```java
//具体产品，Map迭代器(用到了代理模式)
public class IteratorMap<T> implements IIterator<T>{
    Iterator<Map.Entry<T, Object>> iterator;
    public IteratorMap(Map<T,Object> m){
        iterator = m.entrySet().iterator();
    }
    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }
    @Override
    public Object next() {
        return iterator.next().getValue();
    }
}
```

完成具体产品设计后，我们就要实现具体的工厂了：
```java
//具体工厂
public class IteratorFactory<T> implements IIteratorFactory<T>{
    @Override
    public IteratorMap<T> iteratorMap(Map<T,Object> m) {
        return new IteratorMap<T>(m);
    }
    @Override
    public IteratorCollection<T> iteratorCollection(Collection<T> c) {
        return new IteratorCollection<T>(c);
    }
}
```
至此，这个小框架就完成了，我们可以使用它来遍历Collection（List，Set，Queue都是集成自它）和Map：

```java
//测试使用
public class TestUse {
    public static void main(String args[]){
        IIteratorFactory<Integer> factory = new IteratorFactory<>();
        Collection<Integer> collection = new ArrayList<Integer>();
        Map<Integer, Object> map = new LinkedHashMap<>();
        for(int i=0;i<10;i++){
            collection.add(i);
            map.put(i, i);
        }
        IIterator<Integer> iteratorCollection = factory.iteratorCollection(collection);
        IIterator<Integer> iteratorMap = factory.iteratorMap(map);
        while(iteratorCollection.hasNext())
            System.out.print(iteratorCollection.next());
        System.out.println();
        while(iteratorMap.hasNext())
            System.out.print(iteratorMap.next());
    }
}
```
输出

```shell
0123456789
0123456789
```

实际情况下，我们可能不应该这么做，以为Collection面向一种对象的容器，Map是面向两种对象的关联容器，但是此例使用抽象工厂模式确实实现了不同容器的统一遍历方式。

如果一个容器持有的大量对象，他们都直接或间接集成自某一个类，使用访问者模式遍历也是一种很好的方式，具体在后面的访问者模式中会详细介绍。

工厂模式主要就涉及上面介绍的三种：
* 简单工厂模式是由一个具体的类去创建其他类的实例，父类是相同的，父类是具体的。
* 工厂方法模式是有一个抽象的父类定义公共接口，子类负责生成具体的对象，这样做的目的是将类的实例化操作延迟到子类中完成。
* 抽象工厂模式提供一个创建一系列相关或相互依赖对象的接口，而无须指定他们具体的类。它针对的是有多个产品的等级结构。而工厂方法模式针对的是一个产品的等级结构。

### 4. 生成器模式（Builder Pattern）
将一个复杂对象的构建与它的表示分离，使同样的构建过程可以创建不同的表示。

* 何时使用
    * 当系统准备为用户提供一个内部结构复杂的对象，而且在构造方法中编写创建该对象的代码无法满足用户需求时，就可以使用生成器模式老构造这样的对象。
    * 当某些系统要求对象的构造过程必须独立于创建该对象的类时。
    * 当有多个构造函数的参数的时候
* 优点
    * 生成器模式将对象的构造过程封装在具体的生成器中，用户使用不同的具体生成器就可以得到该对象的不同表示。
    * 生成器模式将对象的构造过程从创建该对象的类中分离出来，使用户无须了解该对象的具体组件。
    * 可以更加精细有效的控制对象的构造过程。生成器将对象的构造过程分解成若干步骤，这就是程序可以更加精细，有效的控制整个对象的构造。
    * 生成器模式将对象的构造过程与创建该对象类解耦，是对象的创建更加灵活有弹性。
    * 当增加新的具体的生成器是，不必修改指挥者的代码，即该模式满足开-闭原则。

模式的重心在于分离构建算法和具体的构造实现，从而使构建算法可以重用。

```java
public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder {
        //Required parameters
        private final int servingSize;
        private final int servings;

        //Optional parameters - init to default values
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohyfrate = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val) {
            calories = val;
            return this;
        }

        public Builder fat(int val) {
            fat = val;
            return this;
        }

        public Builder sodium(int val) {
            sodium = val;
            return this;
        }

        public Builder carbohyfrate(int val) {
            carbohyfrate = val;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    private NutritionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.calories;
    }

    public static void main(String[] args) {
        NutritionFacts cocaCola = new Builder(240, 8).calories(100).sodium(35).carbohyfrate(27).build();
    }
}
 ```

### 5. 原型模式(Prototype Pattern)

#### 介绍
用原型实例指定创建对象的种类，并且通过复制这些原型创建新的对象。
#### 何时使用
* 程序需要从一个对象出发，得到若干个和其状态相同，并可独立变化其状态的对象时。
* 当对象的创建需要独立于它的构造过程和表示时。
* 一个类创建实例状态不是很多，那么就可以将这个类的一个实例定义为原型，那么通过该实例复制该原型得到新的实例可能比重新使用类的构造方法创建新实例更方便
#### 优点
* 当创建类的新实例的代价更大时，使用原型模式复制一个已有的实例可以提高创建新实例的效率。
* 可以动态的保存当前对象的状态。在运行时，可以随时使用对象流保存当前对象的一个复制品。
* 可以在运行时创建新的对象，而无须创建一系列类和集成结构。
* 可以动态的添加、删除原型的复制品。
#### 实现
* 简单的原型模式
    简单的原型模式就是在clone()实现时，new一个新的实例，然后为成员变量赋值后返回。
    ```java
    //具体原型
    public class SimplePrototype implements Prototype,Cloneable {
        int value;
        //clone()实现
        @Override
        public Object cloneSelf() {
            SimplePrototype self = new SimplePrototype();
            self.value = value;
            return self;
        }
        //使用
        public static void main(String args[]){
            SimplePrototype simplePrototype = new SimplePrototype();
            simplePrototype.value = 500;
            SimplePrototype simplePrototypeClone = (SimplePrototype) simplePrototype.cloneSelf();
            System.out.println(simplePrototypeClone.value);
        }
    }
    //抽象原型
    interface Prototype{
        Object cloneSelf();//克隆自身的方法
    }
    //客户端使用
    class Client{
        SimplePrototype prototype;
        public Client(SimplePrototype prototype){
            this.prototype = prototype;
        }
        public Object getPrototype(){
            return prototype.cloneSelf();
        }
    }
    ```
* Java原生支持
     Java中所有类都直接或间接继承自Object类，Object类中已有clone()方法：”protected native Object clone() throws CloneNotSupportedException;“，可以看到权限是protected的，所以仅有子类可以访问这个方法，但我们可以在子类中重写这个方法，将访问权限上调到public，然后方法体里面return super.clone()。

    我们能看到这个Object方法是可能会抛出异常的，我们必须实现Cloneable接口，才可以使用这个方法，否则会抛出“java.lang.CloneNotSupportedException”的异常。这个Cloneable接口其实是空的，实现它的目的在于让JVM知道这个对象是可以可复制的，否则clone()时就会发生异常。下面看演示代码：
    ```java
    //使用 java 自带的支持
    public class APITestUse {
        public static void main(String args[]) throws CloneNotSupportedException{
            MyObject myObject = new MyObject();
            myObject.i = 500;
            MyObject myObjectClone = (MyObject) myObject.clone();
            System.out.println(myObjectClone.i);
        }
    }
    //一个可以复制的对象
    class MyObject implements Cloneable{
        int i;
        public Object clone() throws CloneNotSupportedException{
            return super.clone();
        }
    }//结果会输出 500
    ```
    调用这个方法时，成员变量会自动被复制。所以如果需要使用原型模式，Java原生支持就已经很好用了。

    除了以上的原生支持，java中还有一种序列化，只需要对象实现Serializable接口。这样，我们可以将对象写入到流中，可以保存到文件，也可以通过网络发送到其他地方：
    ```java
    //使用Serializable支持克隆
    public class SerializablePrototype implements Serializable {
        private static final long serialVersionUID = 1L;
        private int i;
        private transient int notClone;//transient关键字的成员不会被序列化
        public int getI() {
            return i;
        }
        public void setI(int i) {
            this.i = i;
        }
        public int getNotClone() {
            return notClone;
        }
        public void setNotClone(int notClone) {
            this.notClone = notClone;
        }
        public void writeToFile(String path) throws Exception{
            FileOutputStream outStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
            objectOutputStream.writeObject(this);
            outStream.close();
        }
        public SerializablePrototype ReadFromFile(String path) throws Exception{
            File file = new File(path);
            if(!file.exists())
                file.createNewFile();
            FileInputStream inStream = new FileInputStream(path);
            ObjectInputStream objectOutputStream = new ObjectInputStream(inStream);
            Object o= objectOutputStream.readObject();
            inStream.close();
            return (SerializablePrototype) o;
        }
        public static void main(String args[]) throws Exception{
            String path = "D:\\HUST\\AutumnRecruit2018\\code\\AutumnRecruitProject\\resources\\test";
            SerializablePrototype prototype = new SerializablePrototype();
            prototype.setI(123);
            prototype.setNotClone(456);
            prototype.writeToFile(path);
            SerializablePrototype prototypeClone = new SerializablePrototype();
            prototypeClone = prototype.ReadFromFile(path);
            System.out.println(prototypeClone.getI() + " " + prototypeClone.getNotClone());
        }
    }//输出：123 0
    ```
    我们来分析上例：这个对象有3个成员变量，而其中一个是有transient关键字申明的，一个是序列化id，一个是普通变量，在main方法中，想创建了对象，并设置值，然后写入到文件，再从文件读出来，最后输出读出来的对象的变量，普通变量是可以正常输出的（序列化id也可以，只是此处没有输出来而已），而transient申明的变量为0了，那就证明这个变量没有被保存到文件中，因为这个关键字声明的变量在序列化时会被忽略，而是后来创建时被自动初始化为0了（java中类的成员变量是基本数据类型时，如果没有初值，就会被自动初始化为0）。

## 行为型模式

### 1. 责任链模式(Chain of Responsibility Pattern)
#### 介绍
使很多个对象都有机会处理请求，从而避免请求的发送者和接收者之间的耦合关系。将这些对象连成一条链，并沿着这条链传递该请求，直到有一个对象处理它为止。
#### 何时使用
* 有许多对象可以处理用户请求，希望程序在运行期间自动确定处理用户的那个对象。
* 希望用户不必明确指定接收者的情况下，想多个接受者的一个提交请求
* 程序希望动态的指定可处理用户请求的对象集合

#### 优点
* 低耦合
* 可以动态的添加删除处理者或重新指派处理者的职责
* 可以动态改变处理者之间的先后顺序
#### 实现

通常来说，一个纯粹的责任链是先传给第一个处理，如果处理过了，这个请求处理就此结束，如果没有处理，再传给下一个处理者。

比如我们有一个数学公式，有一个整数输入，要求小于0时返回绝对值，其次，小于10的时候返回他的二次幂，否则，返回他本身：

首先我们要定义一个接口（处理者），来描述他们共有的行为：

```java
public interface Handler {
    int handleRequest(int n);
    void setHandler(Handler next);
}
```
```java
public class Handler1 implements Handler {
    Handler next;
    @Override
    public int handleRequest(int n) {
        if (n < 0) {
            return -n;
        } else {
            if (next != null) {
                return next.handleRequest(n);
            } else {
                throw new NullPointerException("Next 不能为空");
            }
        }
    }

    @Override
    public void setHandler(Handler next) {
        this.next = next;
    }
}
```
```java
public class Handler2 implements Handler {
    Handler next;
    @Override
    public int handleRequest(int n) {
        if (n < 10) {
            return n * n;
        } else {
            if (next != null) {
                return next.handleRequest(n);
            } else {
                throw new NullPointerException("Next 不能为空");
            }
        }
    }

    @Override
    public void setHandler(Handler next) {
        this.next = next;
    }
}
```
```java
public class Handler3 implements Handler{
    Handler next;
    @Override
    public int handleRequest(int n) {
        if (n < Integer.MAX_VALUE) {
            return n;
        } else {
            if (next != null) {
                return next.handleRequest(n);
            } else {
                throw new NullPointerException("Next 不能为空");
            }
        }
    }

    @Override
    public void setHandler(Handler next) {
        this.next = next;
    }
}
```
```java
public class TestUse {
    public static void main(String args[]){
        Handler h1,h2,h3;
        h1 = new Handler1();
        h2 = new Handler2();
        h3 = new Handler3();
        h1.setHandler(h2);
        h2.setHandler(h3);
        System.out.println(h1.handleRequest(-1));
        System.out.println(h1.handleRequest(5));
        System.out.println(h1.handleRequest(9999));
    }
}
```
此处责任链中的具体处理者的顺序是不能重设的，否则可能会引发错误，但更多的情况是完全可以随意更改他们的位置的，就上例中，只要把if中的条件重新设置（各自独立，不相互依赖），就可以了。

我们写java web程序的时候，通常会编写一些过滤器（Filter），然后配置到web.xml中，这其实就是责任链模式的一种实践。而使用Log4j记录日志，配置级别的时候，也同样用到了责任链模式。

我们使用责任链模式的时候，不一定非得某一处理者处理后就得终止请求的传递，如果有其他需求，我们依然可以继续传递这个请求到下一个具体的处理者。
### 2. 命令模式(Command Pattern)
#### 介绍
别名：动作(Action)、事物(Transaction)
将一个请求封装为一个对象，从而使用户可用不同的请求对客户进行参数化；对请求排队或记录请求日志，以及支持可撤销的操作。
#### 何时使用
* 程序需要在不同的时刻指定、排列和执行请求。
* 程序需要提供撤销操作。
* 程序需要支持宏操作。
#### 优点
* 在命令模式中，请求者（Invoker）不直接与接受者（Receiver）交互，及请求者（Invoker）不包含接受者（Receiver）的引用，因此彻底消除了彼此间的耦合。
* 命令模式满足“开-闭原则”。如果增加新的具体命令和该命令的接受者，不必修改调用者的代码，调用者就可以使用新的命令对象；反之，如果增加新的调用者，不必修改现有具体命令和接收者，新增加的调用者就可以使用已有的具体命令。
* 由于请求者的请求被封装到具体的命令中，那么就可以将具体命令保存到持久化的媒介中，在需要的时候，重新执行这个具体命令。因此，使用命令模式可以记录日志。
* 使用命令模式可以对请求者的“请求”进行排队。每个请求都各自对应一个具体命令，因此可以按一定顺序执行这些具体命令。

一个对象有多种操作，但是我们不希望调用者（请求者）直接使用，我们就额外添加一个对象，然后让调用者通过这个对象来使用那些操作。

比如，我们有一个类可以在磁盘上新建或是删除文件（接收者），但是我们不希望直接提供给别人（请求者）使用，所以我们就为它的各种操作创建对应的命令，下面我们用代码来实现这个需求：

#### 实现
接收者，可以在磁盘删除或新建文件：
```java
//接收者
public class MakeFile {
    //新建文件
    public void createFile(String name) throws IOException{
        File file = new File(name);
        file.createNewFile();
    }
    //删除文件
    public boolean deleteFile(String name){
        File file = new File(name);
        if(file.exists()&&file.isFile()){
            file.delete();
            return true;
        }
        return false;
    }
}
```
然后就是执行操作的接口：
```java
//命令接口
public interface Command {
    void execute(String name) throws Exception;
}
```
我们需要创建具体的命令，这里就是2个，新建和删除：
```java
//新建文件命令
public class CommandCreate implements Command {
    MakeFile makeFile;
    public CommandCreate(MakeFile makeFile) {
        this.makeFile = makeFile;
    }
    @Override
    public void execute(String name) throws Exception {
        makeFile.createFile(name);
    }
}
```
```java
//删文件命令
public class CommandDelete implements Command{
    MakeFile makeFile;
    public CommandDelete(MakeFile makeFile) {
        this.makeFile = makeFile;
    }
    @Override
    public void execute(String name) throws Exception {
        makeFile.deleteFile(name);
    }
}
```
最后就是请求者了：
```java
//请求者
public class Client {
    Command command;
    public Client setCommand(Command command){
        this.command = command;
        return this;
    }

    public void executeCommand(String name) throws Exception{
        if(command==null)
            throw new Exception("命令不能为空！");
        command.execute(name);
    }
}
```
```java
public class TestUse {
    public static void main(String args[]) throws Exception{
        //接收者
        MakeFile makeFile = new MakeFile();
        //命令
        CommandCreate create = new CommandCreate(makeFile);
        CommandDelete delete = new CommandDelete(makeFile);
        //请求者
        Client client = new Client();
        //执行命令
        client.setCommand(create).executeCommand("D:\\test1.txt");
        client.setCommand(create).executeCommand("D:\\test2.txt");
        client.setCommand(delete).executeCommand("D:\\test2.txt");
    }
}//执行完后在D盘会有一个test1.txt的文件，test2.txt本页创建了，不过又被删除了。。
```
这里只是简单的实现，诸如CommandCreate命令的操作，如果我们需要undo的，那么就需要在命令接口中添加undo()方法并在具体命令中实现即可（将操作保存到栈里即可，undo的时候出栈并撤销操作）。

命令模式不宜滥用，比如：使用这种模式，会多出来很多对象（命令）。

命令模式中还有一种具体命令叫宏命令，它会包含一些其他具体命令的引用，执行宏命令可以执行这个宏命令所包含的引用的命令，概念清楚后实现起来也是容易的：

比如输出文章的命令，有中文输出命令、英文输出命令和宏命令，宏命令包含了其他两个命令的引用（可以使用列表保存这些命令），如果执行宏命令，宏命令会一次执行它所包含引用的其他命令（迭代命令列表并执行即可）。

### 3. 解释器模式(Interpreter Pattern)
#### 介绍
给定一个语言，定义它的文法的一种表示，并定义一个解释器，这个解释器使用该表示来解释语言中的句子。
#### 何时使用
当有一个简单的语言需要解释执行，并且可以将该语言的每一个规则表示为一个类时，就可以使用解释器模式。
#### 优点
* 将每一个语法规则表示成一个类，方便与实现简单的语言。
* 由于使用类表示语法的规则，可以较容易改变或扩展语言的行为。
* 通过在类结构中加入新的方法，可以在解释的同时增加新的行为。
#### 角色
* 抽象表达式：该角色为一个接口，负责定义抽象的解释操作。
* 终结符表达式：实现抽象表达式接口的类。
* 非终结表达式：也是实现抽象表达式的类。
* 上下文（Context）：包含解释器之外的一些全局信息。
#### 步骤
* 解析语句中的动作标记。
* 将标记规约为动作。
* 执行动作。
#### 实现
这种模式一般会应用到一些特殊的问题上，使用这种模式一般需要了解形式语言中的基本知识。js内核就是一个强大的解释器。

简单的解释器模式，我们需要解释出来表达式的信息即可；而更深一层的，我们需要把表达式中的内容，翻译成我们程序运行的一部分来执行。

不提供例子。
### 4. 迭代器模式(Iterator Pattern)
#### 介绍
提供一种方法顺序访问一个聚合对象中的各个元素，而由不需要暴露该对象的内部细节。
#### 何时使用
* 让用户访问集合汇总的对象而不想暴露这个集合的实现时
* 对不同集合提供一个统一的遍历接口时
#### 优点
* 用户使用迭代器访问集合中的对象而不需要知道这个集合的具体实现
* 可以同时使用多个迭代器遍历一个集合
#### 实现
通常容器提供的迭代器时可以高速遍历它本身的，而使用其本身的机制（如LinkedList中使用get(i)方法遍历）遍历性能可能并不好。

其实这个在工厂方法模式给出的例子就足够解释这个模式的使用了，如需看具体代码实现，请移步工厂方法模式中的例子查看。

其中主要的角色是集合、具体集合、迭代器、具体迭代器。

迭代器其实很简单，下面我们就继续工厂方法模式中的例子，将它完善一下：

稍微增强的集合接口：
```java
//集合接口
public interface MyList<T> {
    MyIterator<T> iterator(); //返回一个遍历器
    boolean add(T t);       //添加元素到列表
    T get(int index);       //得到元素
    T remove();             //删除最后一个元素
    boolean remove(T element);  //删除指定元素
    T remove(int index);    //删除指定位置元素
    boolean set(int index,T element);   //修改指定位置值
    int size();
}
```
容量可以自动增长的数组实现的集合：
```java
public class MyArrayList<T> implements MyList<T>{
    private int size;               //存放的元素个数,会默认初始化为0
    private Object[] defaultList;   //使用数组存放元素
    private static final int defaultLength = 10;//默认长度
    public MyArrayList(){               //默认构造函数
        defaultList = new Object[defaultLength];
    }
    @Override
    public MyIterator<T> iterator() {
        return new Iterator();
    }
    //大小自动增长
    private void ensureCapacity(int capacity){
        int nowLength = defaultList.length;
        if(capacity >= nowLength){
            nowLength = nowLength + (nowLength>>1);
            if(nowLength<0)//溢出
                nowLength = Integer.MAX_VALUE;
            defaultList = Arrays.copyOf(defaultList, nowLength);
        }
    }
    //添加元素
    @Override
    public boolean add(T t) {
        ensureCapacity(size+1);
        defaultList[size++] = t;
        return true;
    }
    //获取元素
    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        if(index<0 || index>=size) return null;
        return (T) defaultList[index];
    }
    @Override
    public T remove() {
        return remove(size-1);
    }
    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        if(index<0||index>=size) return null;
        T element = (T) defaultList[index];
        if(index != size-1)
        System.arraycopy(defaultList, index+1, defaultList, index,size-index-1);
        size--;
        return element;
    }
    @Override
    public boolean remove(T element) {
        if(element==null){
            for(int i = 0 ; i<size;i++)
                if(defaultList[i]==null){
                    System.arraycopy(defaultList, i+1, defaultList, i,size-i-1);
                    size--;
                    return true;
                }
        }
        else{
            for(int i = 0 ; i<size;i++)
                if(defaultList[i].equals(element)){
                    System.arraycopy(defaultList, i+1, defaultList, i,size-i-1);
                    size--;
                    return true;
                }
        }
        return false;
    }
    @Override
    public boolean set(int index,T element) {
        if(index<0||index>=size) return false;
        defaultList[index] = element;
        return true;
    }
    @Override
    public int size() {
        return size;
    }
    //迭代器
    private class Iterator implements MyIterator<T>{
        private int next;
        @Override
        public boolean hasNext() {
            return next<size;
        }
        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            return (T) defaultList[next++];
        }
        @Override
        public T remove() {
            // TODO Auto-generated method stub
            return null;
        }
    }
}
```
链表实现的集合：
```java
public class MyLinkedList<T> implements MyList<T>{
    private int size;   //存放的元素个数,会默认初始化为0
    private Node<T> first;    //首节点，默认初始化为null
    @Override
    public MyIterator<T> iterator() {
        return new Iterator();
    }
    @Override
    public boolean add(T t) {
        if(size==0){
            first = new Node<T>(t,null);
            size++;
            return true;
        }
        Node<T> node = first;
        while(node.next!=null)
            node = node.next;
        node.next = new Node<T>(t,null);
        size++;
        return true;
    }
    @Override
    public T get(int index) {
        Node<T> node = first;
        while(--index>=0)
            node = node.next;
        return node.data;
    }
    @Override
    public T remove() {
        return remove(size-1);
    }
    @Override
    public T remove(int index) {
        if(index<0||index>=size) return null;
        Node<T> node = first;
        while(--index>0)
            node = node.next;
        T element = node.next.data;
        node.next = node.next.next;
        size--;
        return element;
    }
    @Override
    public boolean remove(T element) {
        if(element == null){
            if(first.data==null){
                first = first.next;
                size--;
                return true;
            }
            Node<T> node = first;
            do{
                if(node.next.data==null){
                    node.next = node.next.next;
                    size--;
                    return true;
                }
                node = node.next;
            }
            while(node.next!=null);
        }
        else{
            if(first.data.equals(element)){
                first = first.next;
                size--;
                return true;
            }
            Node<T> node = first;
            do{
                if(node.next.data.equals(element)){
                    node.next = node.next.next;
                    size--;
                    return true;
                }
                node = node.next;
            }
            while(node.next!=null);
        }
        return false;
    }
    @Override
    public boolean set(int index, T element) {
        if(index<0||index>=size) return false;
        Node<T> node = first;
        while(--index>0)
            node = node.next;
        node.data = element;
        return true;
    }
    @Override
    public int size() {
        return size;
    }
    //链表节点
    private static class Node<T>{
        T data;
        Node<T> next;
        Node(T data,Node<T> next){
            this.data = data;
            this.next = next;
        }
    }
    //遍历器
    private class Iterator implements MyIterator<T>{
        private Node<T> next; //下一个节点
        Iterator(){
            next = first;
        }
        @Override
        public boolean hasNext() {
            return next!=null;
        }
        @Override
        public T next() {
            T data = next.data;
            next = next.next;
            return data;
        }
        @Override
        public T remove() {
            // TODO Auto-generated method stub
            return null;
        }
    }
}
```
迭代器接口：
```java
public interface MyIterator<T> {
    boolean hasNext();  //是否还有下一个元素
    T next();           //得到下一个元素
    T remove();
}
```
```java
public class TestUse {
    public static void main(String args[]){
        //分别定义两种结构
        MyList<String> array = new MyArrayList<String>();
        MyList<String> link = new MyLinkedList<String>();
        //添加数据
        for(int i = 1;i < 15; i++){
            array.add(i+"");
            link.add(i+"");
        }
        //数组操作
        System.out.println(array.remove());
        System.out.println(array.get(5));
        System.out.println(array.remove(5));
        System.out.println(array.get(5));
        System.out.println(array.remove("7"));
        System.out.println(array.set(0, "00"));
        //使用迭代器
        MyIterator<String> ai = array.iterator();
        while(ai.hasNext())
            System.out.print(ai.next()+" ");
        System.out.println();
        System.out.println(link.remove());
        System.out.println(link.get(5));
        System.out.println(link.remove(5));
        System.out.println(link.get(5));
        System.out.println(link.remove("7"));
        System.out.println(link.set(0, "00"));
        //使用迭代器
        MyIterator<String> li = link.iterator();
        while(li.hasNext())
            System.out.print(li.next()+" ");
        System.out.println();
        System.out.println("a size=" + array.size());
        System.out.println("l size=" + link.size());
    }
}
```


### 5. 中介者模式(Mediator Pattern)
#### 介绍
用一个中介对象来封装一系列的对象交互。中介者使各对象不需要显示的相互引用，从而使其耦合松散，而且可以独立的改变他们之前的交互。
#### 何时使用

#### 优点
两个类直接关联，是很好实现的，但如果不希望两个类直接发生交互，那么就需要使用中介者模式了。

比如有两个类，他们都是做持久化的，一个负责将数据写入文件，一个负责将数据写入数据库。他们谁先接收到数据是不确定的，但是要确保其中一个接收到数据后，另外一个也必须完成这些数据的持久化。如果我们直接将两个类关联在一起，互相调用是可以实现的，但不利于后期扩展或维护（比如再添加一个持久化组建，则原有的组建可能都需要修改），此时，若添加一个中介者，来协调他们，事儿就好办多了：
#### 实现
数据持久化的接口：
```java
public interface IPersistent {
    void getData(Object data);
    void getData(Object data,Midiator midiator);
    void saveData();
}
```
分别实现持久化到文件和持久化到数据库的组件（具体同事）：
```java
public class PersistentFile implements IPersistent{
    private Object data;
    @Override
    public void getData(Object data, Midiator midiator) {
        getData(data);
        midiator.notifyOther(this, data);
    }
    @Override
    public void saveData() {
        System.out.println(data + " 已保存到文件");
    }
    @Override
    public void getData(Object data) {
        this.data = data;
        saveData();
    }
}
```
```java
public class PersistentDB implements IPersistent{
    private Object data;
    @Override
    public void getData(Object data, Midiator midiator) {
        getData(data);
        midiator.notifyOther(this, data);
    }
    @Override
    public void saveData() {
        System.out.println(data + " 已保存到数据库");
    }
    @Override
    public void getData(Object data) {
        this.data = data;
        saveData();
    }
}
```
```java
public class Midiator {
    PersistentDB persistentDB;//此处可以使用List来存放所有的同事
    PersistentFile persistentFile;
    public Midiator setPersistentDB(PersistentDB persistentDB) {
        this.persistentDB = persistentDB;
        return this;
    }
    public Midiator setPersistentFile(PersistentFile persistentFile) {
        this.persistentFile = persistentFile;
        return this;
    }
    public void notifyOther(IPersistent persistent,Object data){
        if(persistent instanceof PersistentDB)//如果同事都放在List中，此处遍历即可
            persistentFile.getData(data);
        if(persistent instanceof PersistentFile)
            persistentDB.getData(data);
    }
}
```
```java
public class TestUse {
    public static void main(String args[]){
        Object data = "数据";
        PersistentDB persistentDB = new PersistentDB();
        PersistentFile persistentFile = new PersistentFile();
        Midiator midiator = new Midiator();
        midiator.setPersistentDB(persistentDB).setPersistentFile(persistentFile);
        persistentDB.getData(data, midiator);
        persistentFile.getData(data, midiator);
    }
}//输出（省略了换行符）：数据 已保存到数据库数据 已保存到文件数据 已保存到文件数据 已保存到数据库
```
### 6. 备忘录模式(Memento Pattern)
#### 介绍
在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存该状态，这样就可以将该对象恢复到之前保存的状态。
#### 何时使用
* 必须保存一个对象在某一时刻的全部或部分状态，以便在需要时恢复该对象先前的状态。
* 一个对象不想通过提供public权限的，诸如getXXX()的方法让其他对象得到自己IDE内部状态。
#### 优点
* 备忘录模式使用备忘录可以把原先者的内部状态全部保存起来，使得“亲密”的对象可以访问备忘录中的数据。
* 备忘录模式强调了类设计单一责任的原则，即将状态的刻画和保存分开。
### 角色
* 备忘录(Memento)角色：将发起人（Originator）对象的内战状态存储起来。备忘录可以根据发起人对象的判断来决定存储多少发起人（Originator）对象的内部状态。备忘录可以保护其内容不被发起人（Originator）对象之外的任何对象所读取。
* 发起人（Originator）角色：创建一个含有当前的内部状态的备忘录对象。使用备忘录对象存储其内部状态。
* 负责人（Caretaker）角色：负责保存备忘录对象。不检查备忘录对象的内容。
#### 实现
```java
//简单的备忘录模式
public class SimpleMemento {
    public static void main(String[] args) throws Exception {
        Originator originator = new Originator();   //发起人，要被保存的对象,也是他创建要保存的信息的
        Caretaker caretaker = new Caretaker();      //辅助保存的对象
        originator.setState("stateOne");    //设置状态
        System.out.println(originator.getState());
        caretaker.saveMemento(originator.createMemento());      //保存状态
        originator.setState("stateTwo");    //修改状态
        System.out.println(originator.getState());
        originator.recoverMemento(caretaker.recoverMemento());  //恢复状态
        System.out.println(originator.getState());
    }
}
//发起人
class Originator {
    private String state;
    public Memento createMemento(){
        return new Memento(state);
    }
    public void recoverMemento(Memento memento){
        this.state = memento.getState();
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
}
//备忘录
class Memento {
    private String state;
    public Memento(String state){
        this.state = state;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
}
//负责人
class Caretaker {
    private Memento memento;
    public Memento recoverMemento() throws Exception{
        if(memento==null)
            throw new Exception("没有保存的状态");
        return this.memento;//恢复状态
    }
    public void saveMemento(Memento memento){
        this.memento = memento;//保存状态
    }
}
```
备忘录角色对任何对象都提供一个接口，备忘录角色的内部所存储的状态就对所有对象公开，因此是破坏封装性的。

按照定义中的要求，备忘录角色要保持完整的封装。最好的情况便是：备忘录角色只应该暴露操作内部存储属性的的接口给“备忘发起角色”。

如果上例中，我们把备忘录以发起人的私有内部类的方式实现的话，那它就只能被发起人访问了，这正好就符合备忘录模式的要求，但是我们的负责人是需要存放备忘录的引用的，于是，我们提供一个公共的接口，他是空的，我们用备忘录实现它，主要就是利用其中的类型信息，具体实现如下：
```java
//备忘录模式
public class BlackMemento {
    public static void main(String[] args) {
        BlankOriginator originator = new BlankOriginator(); //发起人
        BlackCaretaker caretaker = new BlackCaretaker();    //负责人
        originator.setState("stateOne");    //设置状态
        caretaker.saveMemento(originator.createMemento());  //保存信息
        originator.setState("stateTwo");    //修改状态
        originator.recoverMemento(caretaker.recoverMemento());//恢复状态
    }
}
interface MementoIF {}
//发起人
class BlankOriginator {
    private String state;
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public MementoIF createMemento(){
        return new Memento(state);
    }
    public void recoverMemento(MementoIF memento){
        this.setState(((Memento)memento).getState());
    }
    //以内部类实现备忘录角色
    private class Memento implements MementoIF{ 
        private String state;
        private Memento(String state){
            this.state = state;
        }
        private String getState() {
            return state;
        }
    }
}
//负责人
class BlackCaretaker {
    private MementoIF memento;
    public MementoIF recoverMemento(){
        return memento;
    }
    public void saveMemento(MementoIF memento){
        this.memento = memento;
    }
}
```
上面两个例子，演示的都是保存一个状态（不是指一个成员，而是只存了最近一次状态），即一个检查点，但是实际应用中，状态往往不止存储一次，我们将上面储存状态的变量改为一个栈（或队列，主要看需求）即可。比如：BlackCaretaker中的private MementoIF memento；改为LinkedList<MementoIF> mementos 实现，保存的时候压栈（入队），恢复的时候出栈（出队）。具体实现都已经描述很清楚了，代码就不贴了（文章本来就太长了）。

针对上例，如果发起人和负责人我们并不介意他们必须是独立的，就可以把他们融合到一起，实现就会更佳简单，代码也简洁：
```java
//自述历史备忘录
public class MementoSelf {
    public static void main(String[] args) {
        OriginatorCaretaker originatorCaretaker = new OriginatorCaretaker();//发起人，同时为负责人
        originatorCaretaker.changeState("stateOne");    //改变状态
        IMemento memento = originatorCaretaker.createMemento(); //保存状态
        originatorCaretaker.changeState("stateTwo");    //改变状态
        originatorCaretaker.recoverMemento(memento);    //恢复状态
    }
}
interface IMemento {}
//发起人兼负责人
class OriginatorCaretaker {
    public String state;
    public void changeState(String state){
        this.state = state;
    }
    //创造快照
    public Memento createMemento(){
        return new Memento(this);
    }
    //恢复状态
    public void recoverMemento(IMemento memento){
        Memento m = (Memento)memento;
        changeState(m.state);
    }
    //内部类实现备忘录
    private class Memento implements IMemento{
        private String state;
        private Memento(OriginatorCaretaker originatorCaretaker){
            this.state = originatorCaretaker.state;
        }   
    }
}
```
上例演示仅保存一个检查点。下面再给出一个实际的例子：

我们有个程序，供用户编辑文本，用户做出修改后，可以保存文本，保存修改后，可以依次恢复到保存前的多个状态中的一个，如果恢复后用户没有修改，还可以取消恢复（重做），下面就演示整个程序。

这个程序为了保证功能相对完整，写作演示可能有点长了：
```java
//文本编辑器
public class TextEditor {
    public static void main(String[] args) {
        //使用这个文本编辑器
        MyTextEditor editor = new MyTextEditor("这里是初始文本，可能为文件中读取的值。");
        System.out.println("开始修改文本：");
        editor.append("添加文字1");
        editor.delWords();      //删除最后一个
//      editor.delWords(2);     //删除最后2个    这两个方法是没有问题的，这里避免控制台输出太多，取消这两次修改
//      editor.delWords(1,5);   //删除前面5个
        System.out.println("开始恢复：");
        for(int i=0;i<10;i++) editor.recoverMemento();//恢复大于实际修改的次数不会出错，只会将文本设为o初始化状态
        System.out.println("开始重做：");
        for(int i=0;i<10;i++) editor.redo();     //重做大于实际恢复的次数不会出错，只会将文本设为最后状态
        System.out.println("再次恢复：");
        for(int i=0;i<10;i++) editor.recoverMemento();//恢复大于实际修改的次数不会出错，只会将文本设为o初始化状态
        System.out.println("再次重做：");
        for(int i=0;i<10;i++) editor.redo();     //重做大于实际恢复的次数不会出错，只会将文本设为最后状态
        System.out.println("再次恢复：");
        for(int i=0;i<10;i++) editor.recoverMemento();//恢复大于实际修改的次数不会出错，只会将文本设为o初始化状态
        editor.append("添加文字2");
        System.out.println("再次重做：");
        for(int i=0;i<10;i++) editor.redo();     //重做大于实际恢复的次数不会出错，只会将文本设为最后状态
    }
}
interface IMemento {}
//发起人兼负责人
class MyTextEditor {
    public StringBuffer text;
    private LinkedList<IMemento> mementos;    //保存快照
    private LinkedList<IMemento> undos;       //保存撤销的操作
    public MyTextEditor(){
        this("");
    }
    public MyTextEditor(String defaultStr){
        text = new StringBuffer(defaultStr);
        mementos = new LinkedList<IMemento>();
        undos = new LinkedList<IMemento>();
        print();
    }
    public void clearHistory(){
        mementos.clear();
        undos.clear();
    }
    public void append(String appendStr){
        if(appendStr==null||appendStr.length()==0) return;
        createMemento();
        text.append(appendStr);
        print();
        undos.clear();
    }
    //删除最后一个
    public void delWords(){
        delWords(1);
    }
    //删除最后n个
    public void delWords(int n){
        if(n<1||n>text.length()) return;
        delWords(text.length()-n+1,text.length());
    }
    //删除中间start到end的字符,第一个文字为第一个(而不是0)
    public void delWords(int start,int end){
        if(start<1 || end>text.length()+1) return;
        createMemento();
        text = text.delete(start-1, end);
        print();
    }
    public void reset(String text){
        this.text = new StringBuffer(text);
    }
    //新的快照
    public void createMemento(){
        mementos.push(new Memento(this));
    }
    //恢复状态
    public boolean recoverMemento(){
        Memento memento = (Memento) mementos.poll();
        if(memento==null) return false;
        undos.push(new Memento(this));
        reset(memento.state);
        print();
        return true;
    }
    //redo,redo的操作也可以恢复！
    public boolean redo(){
        Memento memento = (Memento) undos.poll();
        if(memento==null) return false;
        createMemento();
        reset(memento.state);
        print();
        return true;
    }
    //内部类实现备忘录
    private class Memento implements IMemento{
        private String state;
        private Memento(MyTextEditor editor){
            this.state = editor.text.toString();
        }   
    }
    void print(){
        System.out.println("当前文本：" + text);
    }
}
```
### 7. 观察者模式(Observer Pattern)
#### 介绍
定义对象间的一种一对多的依赖关系，当一个对象状态发生改变时，所有依赖它的对象都得到通知并被自动更新。
#### 何时使用
当一个对象的数据更新时，需要通知其他对象，而又不希望和被通知的对象形成紧耦合时
#### 优点
比如我们有个天气服务（主题），然后有多个使用它的客户端（观察者），包括android和iphone端app的服务（观察者），那么就可以使用这么模式。
#### 实现
```java
public class WeatherInfo {
    private long time;
    private String weather;
    public WeatherInfo(long time,String weather){
        this.time = time;
        this.weather = weather;
    }
    @Override
    public boolean equals(Object obj) {
        WeatherInfo info = (WeatherInfo) obj;
        return info.time==this.time&&info.weather.equals(this.weather);
    }

    public long getTime() {
        return time;
    }

    public String getWeather() {
        return weather;
    }
}
```
```java
public interface IWeatherService {
    void addClient(Client client);      //添加观察者
    boolean deleteClient(Client client);//删除观察者
    void notifyClients();               //通知
    void updateWeather(WeatherInfo info);//主题内容更新
}
```
```java
public enum WeatherService implements IWeatherService {
    INSTANCE;
    private LinkedList<WeatherInfo> weatherInfos = new LinkedList<WeatherInfo>();
    private LinkedHashSet<Client> clients = new LinkedHashSet<Client>(); //存放观察者
    @Override
    public void addClient(Client client) {
        clients.add(client);
    }

    @Override
    public boolean deleteClient(Client client) {
        return clients.remove(client);
    }

    @Override
    public void notifyClients() {
        Iterator<Client> iterator = clients.iterator();
        while (iterator.hasNext()) {
            iterator.next().getWeather(weatherInfos.peekFirst());
        }

    }

    @Override
    public void updateWeather(WeatherInfo info) {
        if (weatherInfos.size() > 0) {
            if (weatherInfos.peekFirst().equals(info)) {
                return;
            }
        }
        weatherInfos.push(info);
        if (clients.size() == 0) {
            return;
        }
        notifyClients();
    }
}
```
```java
//观察者
public interface Client {
    void getWeather(WeatherInfo info);
}
```
```java
public class ClientAndroidServer implements Client {
    private static String name = "Android Service";
    private WeatherInfo info;
    @Override
    public void getWeather(WeatherInfo info) {
        this.info = info;
        dealMsg();
    }
    private void dealMsg() {
        System.out.println(name + "收到天气：time=" + info.getTime() + "天气=" + info.getWeather());
    }
}
```
```java
public class ClientIphoneServer implements Client{
    private static String name = "iphone service";
    private WeatherInfo info;

    @Override
    public void getWeather(WeatherInfo info) {
        this.info = info;
        dealMsg();
    }

    private void dealMsg() {
        System.out.println(name + "收到天气：time=" + info.getTime() + "天气=" + info.getWeather());
    }
}

```
```java
public class TestUse {
    public static void main(String args[]){
        //创建主题
        WeatherService service = WeatherService.INSTANCE;
        //添加观察者
        service.addClient(new ClientAndroidServer());
        service.addClient(new ClientIphoneServer());
        //更新主题
        service.updateWeather(new WeatherInfo(System.currentTimeMillis(), "多云"));
        service.updateWeather(new WeatherInfo(System.currentTimeMillis()+1000*60*60*24, "多云转晴"));
        service.updateWeather(new WeatherInfo(System.currentTimeMillis()+1000*60*60*24*2, "晴"));
    }
}
```
### 8. 状态模式(State Pattern)
#### 介绍
允许一个对象在其内部状态改变时改变它的行为，对象看起来似乎修改了它的类。
#### 何时使用
* 一个对象的行为依赖于它的状态，并且它必须在运行时根据状态改变它的行为 。
* 需要编写大量的条件分支语句来决定一个操作的行为，而且这些条件恰好表示对象的一种状态。
#### 优点
* 使用一个类封装对象的一种状态，很容易增加新的状态
* 在状态模式中，环境（Context）中不必出现大量的条件判断语句。环境（Context）实例所呈现的状态变得更加清晰、容易理解。
* 使用状态模式可以让用户程序很方便地切换环境（Context）实例的状态。
* 使用状态模式不会让环境（Context）中的实例中出现内部状态不一致的情况。
* 当状态对象没有实例变量时，环境（Context）的各个实例可以共享一个状态对象。

用一句话来表述，状态模式把所研究的对象的行为包装在不同的状态对象里，每一个状态对象都属于一个抽象状态类的一个子类。状态模式的意图是让一个对象在其内部状态改变的时候，其行为也随之改变。

#### 实现
设想我们有一个程序，要保存数据的，按照数据（这里以String举例）的大小，使用不同的方式保存。如果数据很小，我们将其保存到Redis（缓存数据库）中，如果数据库不太小也不太大，我们将其保存到mysql中，如果数据非常大，我们直接将其写入到文件中。数据的大小就是一种状态，很适合使用状态模式：

```java
public interface ISaveData {
    void save(String data);
}
```
```java
public class SaveDataController {
    private ISaveData saveData;

    public void save(String data) {
        if (data.length() < 1 << 2) {
            saveData = SaveSmallData.INSANCE;
        } else if (data.length() < 1 << 4) {
            saveData = SaveMiddleData.INSTANCE;
        } else {
            saveData = SaveBigData.INSTANCE;
        }
        saveData.save(data);
    }
}
```
```java
public enum SaveSmallData implements ISaveData {
    INSANCE;
    @Override
    public void save(String data) {
        System.out.println("Save to redis:" + data);
    }
}
```
```java
public enum  SaveMiddleData implements ISaveData{
    INSTANCE;
    @Override
    public void save(String data) {
        System.out.println("Save to mysql:" + data);
    }
}
```
```java
public enum  SaveBigData implements ISaveData{
    INSTANCE;
    @Override
    public void save(String data) {
        System.out.println("Save to file:" + data);
    }
}
```
```java
public class TestUse {
    public static void main(String args[]){
        String smallData = "小数据";
        String middleData = "介于小数据和大数据之间的数据";
        String bifgData = "这里就假定这是一个很大很大很大的数据";
        SaveDataController saveDataController = new SaveDataController();
        saveDataController.save(smallData);
        saveDataController.save(middleData);
        saveDataController.save(bifgData);
    }
}
```
### 9. 策略模式(Strategy Pattern)
#### 介绍
定义一系列算法，把他们一个个封装起来，并且使他们可相互替换。本模式使得算法可独立于其他客户端而变化。
#### 何时使用
 策略模式是对算法的包装，是把使用算法的责任和算法本身分割开来，委派给不同的对象管理。策略模式通常把一个系列的算法包装到一系列的策略类里面，作为一个抽象策略类的子类。用一句话来说，就是：“准备一组算法，并将每一个算法封装起来，使得它们可以互换”。
#### 角色
* 策略（Strategy）：一个接口，定义了若干个算法（抽象方法）。
* 具体策略（ConcreteStrategy）：策略的实现。
* 上下文/环境（Context）：依赖于策略接口的类。
#### 优点
策略模式的重心不是如何实现算法，而是如何组织、调用这些算法，从而让程序结构更灵活，具有更好的维护性和扩展性。

策略模式一个很大的特点就是各个策略算法的平等性。对于一系列具体的策略算法，大家的地位是完全一样的，正因为这个平等性，才能实现算法之间可以相互替换。所有的策略算法在实现上也是相互独立的，相互之间是没有依赖的。所以可以这样描述这一系列策略算法：策略算法是相同行为的不同实现。

运行期间，策略模式在每一个时刻只能使用一个具体的策略实现对象，虽然可以动态地在不同的策略实现中切换，但是同时只能使用一个。

经常见到的是，所有的具体策略类都有一些公有的行为。这时候，就应当把这些公有的行为放到共同的抽象策略角色Strategy类里面。当然这时候抽象策略角色必须要用Java抽象类实现，而不能使用接口。 这其实也是典型的将代码向继承等级结构的上方集中的标准做法。

上次我们使用状态模式将数据按不同状态保存到不同地方，这里，我们使用策略模式来实现通过不同的策略来选择数据的保存方式。
#### 实现
```java
public interface ISaveData {
    void save(Object data);
}
```
```java
public class SaveToRedis implements ISaveData {
    @Override
    public void save(Object data) {
        System.out.println("Data:" + data + " save to redis.");
    }
}
```
```java
public class SaveToFile implements ISaveData{
    @Override
    public void save(Object data) {
        System.out.println("Data:" + data + " save to file.");
    }
}
```
```java
public class SaveClient {
    private ISaveData saveData;
    public SaveClient(ISaveData saveData) {
        this.saveData = saveData;
    }
    public void setSaveData(ISaveData saveData) {
        this.saveData = saveData;
    }
    public void save(Object data) {
        this.saveData.save(data);
    }
}
```
```java
public class TestUse {
    public static void main(String args[]){
        Object data = "数据";
        ISaveData saveData = new SaveToRedis();
        SaveClient client = new SaveClient(saveData);
        client.save(data);
        client.setSaveData(new SaveToFile());
        client.save(data);
    }
}
```
### 10. 模板方法模式(Template Method Pattern)
#### 介绍
定义一个操作中算法的骨架，而将一些步骤延迟到子类中。模板方法使子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。
#### 何时使用
* 设计者需要给出一个算法的固定步骤，并将某些步骤的具体实现留给子类来实现。
* 需要对代码进行重构，将各个子类公共行为提取出来集中到一个共同的父类中以避免代码重复。
#### 优点
* 可以通过在抽象摸吧能定义模板方法给出成熟的算法步骤，同时又不限制步骤的细节，具体模板实现算法细节不会改变整个算法的骨架。
* 在抽象模板模式中，可以通过钩子方法对某些步骤进行挂钩，具体模板通过钩子可以选择算法骨架中的某些步骤。

模板方法模式是所有模式中最为常见的几个模式之一，是基于继承的代码复用的基本技术。 模板方法模式需要开发抽象类和具体子类的设计师之间的协作。一个设计师负责给出一个算法的轮廓和骨架，另一些设计师则负责给出这个算法的各个逻辑步骤。代表这些具体逻辑步骤的方法称做基本方法(primitive method)；而将这些基本方法汇总起来的方法叫做模板方法(template method)，这个设计模式的名字就是从此而来。
#### 实现
抽象模板
```java
public abstract class AbstractTemplate {
    Object data;
    void dealData() {
        getData();
        calcData();
        printData();
    }
    abstract void getData();
    abstract void calcData();
    void printData() {
        System.out.println(data);
    }
}
```
具体模板
```java
public class Template extends AbstractTemplate {
    @Override
    void getData() {
        data = "data";
    }

    @Override
    void calcData() {
        data = (String)data + data;
    }
}
```
使用
```java
public class TestUse {
    public static void main(String[] args) {
        Template template = new Template();
        template.dealData();
    }
}
```
### 11. 访问者模式(Visitor Pattern)
#### 介绍
表示一个作用于某对象结构中的各个元素的操作。它可以在不改变各个元素的类的前提下定义作用于这些元素的新操作。
#### 何时使用
* 一个对象结构中，比如某个集合中，包含很多对象，想对集合中的对象增加一些新的操作。
* 需要对集合中的对象进行很多不同的并且不相关的操作，而不想修改对象的类，就可以使用访问者模式。访问者模式可以在Visitor类中集中定义一些关于集合中对象的操作。
#### 优点
* 可以在不改变一个集合中的元素的类的情况下，增加新的施加于该元素上的新操作。
* 可以将集合中各个元素的某些操作集中到访问者中，不仅便于集合的维护，也有利于集合中元素的复用。
#### 实现
访问者模式的目的是封装一些施加于某种数据结构元素之上的操作。一旦这些操作需要修改的话，接受这个操作的数据结构则可以保持不变。
##### 分派
在介绍访问者模式前，先介绍一下分派的概念。

变量被声明时的类型叫做变量的静态类型(Static Type)，而变量所引用的对象的真实类型又叫做变量的实际类型(Actual Type)，如：
```java
List<String> list = new ArrayList<String>();
```
这个list变量的静态类型是List，而它的实际类型是ArrayList。根据对象的类型而对方法进行的选择，就是分派(Dispatch)。分派又分为两种：静态分派和动态分派。

* 静态分派(Static Dispatch)发生在编译时期，分派根据静态类型信息发生。静态分派对于我们来说并不陌生，方法重载就是静态分派。

* 动态分派(Dynamic Dispatch)发生在运行时期，动态分派动态地置换掉某个方法。

```java
public class Dispatch {
    void print(FatherClass c){
        System.out.print("父类");
    }
    void print(ChildClass c){
        System.out.print("子类");
    }
    public static void main(String args[]){
        FatherClass child = new ChildClass();
        new Dispatch().print(child);
        child.print();
    }
}
class FatherClass{
    void print(){
        System.out.println("父类");
    }
}
class ChildClass extends FatherClass{
    void print(){
        System.out.print("子类");
    }
}//输出：父类子类
```
可以看到，重载的分派是根据静态类型进行的。
java的方法重写是根据实际类型来的（动态分派），编译器编译时并不知道其真实类型，而是运行时动态决定的。

一个对象又叫做它所包含的方法的接收者，java中的动态分派，要调用哪一个方法，是由这个对象的真实类型决定的。

如果能够根据参数和接收者来动态的决定调用某个方法，这就是动态的多分派语言，如果可以根据这两种方式来动态的决定方法调用，就是动态双重分派，但前面已经说了，java中重载是根据静态类型进行的，所以java只能动态的根据接收者来进行方法调用，即java是动态单分派语言，如果要实现双重分派，就必须通过设计模式来完成。

```java
public interface User {
    void accept(Visitor visitor);
}
```

```java
public class UserOrdinary implements User {
    private String estimation;
    public UserOrdinary(String estimation) {
        this.estimation = estimation;
    }
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getEstimation() {
        return estimation;
    }
}

```
```java
public class UserVIP implements User {
    private String estimation;
    public UserVIP(String estimation) {
        this.estimation = estimation;
    }
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getEstimation() {
        return estimation;
    }
}
```
```java
public interface Visitor {
    void visit(UserVIP userVIP);
    void visit(UserOrdinary userOrdinary);
}
```
```java
public class APPOwner implements Visitor {
    @Override
    public void visit(UserVIP userVIP) {
        String estimation = userVIP.getEstimation();
        if (estimation.length() > 5) {
            System.out.println("记录一条VIP用户反馈：" + estimation);
        }
    }

    @Override
    public void visit(UserOrdinary userOrdinary) {
        String estimation = userOrdinary.getEstimation();
        if (estimation.length() > 10) {
            System.out.println("记录一条普通用户反馈：" + estimation);
        }
    }
}
```
```java
public class TestUse {
    public static void main(String[] args) {
        Visitor appOwner = new APPOwner();
        ArrayList<User> users = new ArrayList<User>();
        users.add(new UserOrdinary("普通用户短反馈"));
        users.add(new UserOrdinary("这是一个普通用户的比较长的反馈"));
        users.add(new UserVIP("VIP用户的短反馈"));
        users.add(new UserVIP("VIP用户的比较长的反馈反馈"));
        Iterator<User> iterator =  users.iterator();
        while(iterator.hasNext()){
            iterator.next().accept(appOwner);
        }
    }
}
```

## 结构型模式
### 1. 适配器模式(Adapter Pattern)
#### 介绍
将一个类的接口转换成客户希望的另外一个接口。该模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。
#### 何时使用
一个程序想使用已经存在的类，但是该类所实现的接口和当前程序所使用的接口不一致时。
#### 优点
* 目标与被适配者解耦
* 满足开-闭原则
#### 实现
原理就是保留现有的类所提供的服务，修改其接口，从而达到客户端的期望。
你有一个播放器，只能播放MP3格式的音乐，但是现在需要它能播放flac格式的，我们不能直接使用这个播放器，但可以添加一个适配器来解决这个问题：
原来的播放器（被适配者）:
```java
public class Adaptee {
    void playMp3(Object src) {
        System.out.println("Paly mp3:" + src);
    }
}
```
用户想使用的功能（目标）：
```java
public interface Target {
    void playFlac(Object src);
}
```
对象适配器：
```java
public class ObjectAdapter implements Target {
    private Adaptee adaptee;
    public ObjectAdapter() {
        super();
        adaptee = new Adaptee();
    }

    @Override
    public void playFlac(Object src) {
        //对src做处理
        adaptee.playMp3(src);
    }
}
```
类适配器：
```java
public class ClassAdaptee extends Adaptee implements Target {
    @Override
    public void playFlac(Object src) {
        //对src做处理
        playMp3(src);
    }
}
```
```java
public class TestUse {
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        adaptee.playMp3("mp3");
        Target target = new ClassAdaptee();
        target.playFlac("flac");
        target = new ObjectAdapter();
        target.playFlac("flac");
    }
}
```
类适配器与对象适配器的区别就是类适配器需要继承被适配者，而Java的单继承的，所以通常情况下，使用对象适配器更好。
如果目标接口中的方法数与被适配器者中的数目相同，就是完全适配，若目标接口中的方法更多，则是剩余适配，反之，为不完全适配。


### 2. 组合模式(Composite Pattern)
#### 介绍
将对象组合成树形结构以表示“部分-整体”的层次结构。Composite使用户对单个对象和组合对象的使用具有一致性。
#### 何时使用
* 当想表示对象的部分-整体层次结构。
* 希望用户用一致的方式处理个体对象和组合对象。
#### 优点
* 组合模式中包含个体对象和组合对象，并形成树形结构，使用户可以方便地处理个体对象和组合对象。
* 组合对象和个体对象实现了相同的接口，用户一般无须区分个体对象和组合对象。
* 当增加新的Composite节点和Leaf节点时，用户的重要代码不需要做出修改。

组合模式有时候又叫做部分-整体模式，它使我们树型结构的问题中，模糊了简单元素和复杂元素的概念 ，客户程序可以向处理简单元素一样来处理复杂元素,从而使得客户程序与复杂元素的内部结构解耦。组合模式让你可以优化处理递 归或分级数据结构。有许多关于分级数据结构的例子，使得组合模式非常有用武之地。关于分级数据结构的一个普遍性的例子是电脑的文件系统。下面我们就以这个例子来介绍组合模式（虽然我们直接使用Tree这种数据结构也能直接描述）。
#### 实现
```java
public interface Component {
    void addFile(Component file);
    Component addFolder(Component folder);
    void removeFile(Component file);
    void removeFolder(Component folder);
    List<Component> getFiles();
    List<Component> getFolders();
    List<Component> getAll();
    Iterator<Component> iterator();
    void display();
}
```
```java
public class Folder implements Component {
    private String name;
    private List<Component> files;
    private List<Component> folders;
    public Folder(String name) {
        this.name = name;
        files = new ArrayList<Component>();
        folders = new ArrayList<Component>();
    }
    @Override
    public void addFile(Component file) {
        files.add(file);
    }
    @Override
    public Component addFolder(Component folder) {
        folders.add(folder);
        return this;
    }
    @Override
    public void removeFile(Component file) {
        files.remove(file);
    }
    @Override
    public void removeFolder(Component folder) {
        folders.remove(folder);
    }
    @Override
    public List<Component> getFiles() {
        return files;
    }
    @Override
    public List<Component> getFolders() {
        return folders;
    }

    @Override
    public List<Component> getAll() {
        List<Component> all = new ArrayList<Component>(folders);
        all.addAll(files);
        return all;
    }
    @Override
    public Iterator<Component> iterator() {
        List<Component> all = new ArrayList<Component>();
        add(all, this);
        return all.iterator();
    }
    private void add(List<Component> all, Component component) {
        if (component == null) {
            return;
        }
        all.add(component);
        Iterator<Component> iterator = component.getFolders().iterator();
        while (iterator.hasNext()) {
            add(all, iterator.next());
        }
        all.addAll(component.getFiles());
    }
    @Override
    public void display() {
        System.out.println(name);
    }
}
```
```java
public class File implements Component{
    private String name;
    public File(String name) { this.name = name; }

    @Override
    public void addFile(Component file) { }

    @Override
    public Component addFolder(Component folder) { return null; }

    @Override
    public void removeFile(Component file) { }

    @Override
    public void removeFolder(Component folder) { }

    @Override
    public List<Component> getFiles() { return null; }

    @Override
    public List<Component> getFolders() { return null; }

    @Override
    public List<Component> getAll() { return null; }

    @Override
    public Iterator<Component> iterator() { return null; }

    @Override
    public void display() {
        System.out.println(this.name);
    }
}
```
```java
public class TestUse {
    public static void main(String[] args) {
        Component root = new Folder("root");
        Component folder1 = new Folder("java");
        Component folder2 = new Folder("c#");
        Component file1 = new File("info.txt");
        root.addFolder(folder1).addFolder(folder2).addFile(file1); //一级目录
        folder1.addFile(new File("info.java"));
        Iterator<Component> iterator = root.iterator();
        while (iterator.hasNext()) {
            Component component = iterator.next();
            if (component instanceof Folder) {
                System.out.print("Folder:");
            } else {
                System.out.print("File:");
            }
            component.display();
        }

    }
}
```

### 3. 代理模式(Proxy Pattern)
#### 介绍
为其它对象提供一种代理以控制对这个对象的访问。
#### 何时使用
* 程序可能不希望用户直接访问该对象，而是提供一个特殊的对象以控制对当前对象的访问。
* 如果一个对象（例如很大的图像）需要很长时间才能完成加载。
* 如果对象位于远程主机上，需要为用户提供访问该远程对象的能力。
#### 优点
* 代理模式可以屏蔽用户真正请求的对象，是用户程序和正在的对象解耦。
* 使用代理来担当那些创建耗时的对象的替身。

一个用户不想或者不能够直接引用一个对象（或者设计者不希望用户直接访问该对象），而代理对象可以在客户端和目标对象之间起到中介的作用。而且这个代理对象中，我们可以做更多的操作。
#### 实现
```java
public interface AbstractObject {
    void method1();
    int method2();
    void method3();
}
```
```java
public class TargetObject implements AbstractObject {
    @Override
    public void method1() {
        System.out.println("Method 1");
    }

    @Override
    public int method2() {
        System.out.println("Method 2");
        return 0;
    }

    @Override
    public void method3() {
        System.out.println("Method 3");
    }
}
```
```java
public class ProxyObject implements AbstractObject {
    AbstractObject object = new TargetObject();
    @Override
    public void method1() {
        object.method1();
    }

    @Override
    public int method2() {
        return object.method2();
    }

    @Override
    public void method3() {
        System.out.println("Before");
        object.method3();
        System.out.println("After");
    }
}
```
```java
public class TestUse {
    public static void main(String[] args) {
        AbstractObject object = new ProxyObject();
        object.method1();
        object.method2();
        object.method3();
    }
}
```

```java
public class Queue<T> implements Iterable {
    private LinkedList<T> queue = new LinkedList<T>();
    public void enqueue(T t) {
        queue.offer(t);
    }
    public T dequeue() {
        return queue.poll();
    }
    public T peek() {
        return queue.peek();
    }
    @Override
    public Iterator iterator() {
        return null;
    }
}
```
```java
public class Stack<T> implements Iterable {
    private LinkedList<T> stack = new LinkedList<T>();
    public T pop() {
        return stack.pop();
    }
    public T peek() {
        return stack.peek();
    }
    public void push(T t) {
        stack.push(t);
    }

    @Override
    public Iterator iterator() {
        return stack.iterator();
    }
}
```
```java

```

### 4. 享元模式(Plyweight Pattern)
#### 介绍
运用共享技术有效地支持大量细粒度的对象。
#### 何时使用
* 一个应用程序使用大量的对象，这些对象之间部分属性本质上是相同的，这时应使用享元来封装相同的部分。
* 对象的多数状态都可变为外部状态，就可以考虑将这样的对象作为系统中发的享元来使用。
#### 优点
* 使用享元可以节省内存的开销，特别适合处理大量细粒度对象，这些对象的许多属性值是相同的，而且一旦创建则不允许修改。
* 享元模式中的享元可以使用方法的参数接收外部状态中的数据，但外部状态数据不会干扰到享元中的内部数据，这就使享元可以在不同的环境中被共享。

在JAVA语言中，String类型就是使用了享元模式。String对象是final类型，对象一旦创建就不可改变。在JAVA中字符串常量都是存在常量池中的，JAVA会确保一个字符串常量在常量池中只有一个拷贝。String str="string"，其中"str"就是一个字符串常量。
#### 角色
* 享元接口（Plyweight）：定义了对外公开的获取其内部数据和接收外部数据的方法。
* 具体享元（Concrete Plyweight）：享元接口的实现。
* 享元工厂（Plyweight Factory）：该类的实例负责创建和管理享元对象，用户或其他对象必须请求他以获取一个享元对象。
#### 实现
```java
public class SimpleFlyWeight {
    public static void main(String[] args) {
        FlyweightFactory factory  = new FlyweightFactory();
        IFlyWeight flyWeight1, flyWeight2, flyWeight3, flyWeight4;
        flyWeight1 = factory.getFlyweight("value1");
        flyWeight2 = factory.getFlyweight("value2");
        flyWeight3 = factory.getFlyweight("value3");
        flyWeight4 = factory.getFlyweight("value4");
        flyWeight1.doSomething();
        flyWeight2.doSomething();
        flyWeight3.doSomething();
        flyWeight4.doSomething();
        System.out.println(factory.size());
    }
}

interface IFlyWeight {
    void doSomething();
}

class FlyWeight implements IFlyWeight {
    private String value;
    public FlyWeight(String value){
        this.value = value;
    }

    @Override
    public void doSomething() {
        System.out.println(value);
    }
}

class FlyweightFactory {
    HashMap<String, IFlyWeight> flyweights = new HashMap<String, IFlyWeight>();
    IFlyWeight getFlyweight(String value) {
        IFlyWeight flyWeight = flyweights.get(value);
        if (flyWeight == null) {
            flyWeight = new FlyWeight(value);
            flyweights.put(value, flyWeight);
        }
        return flyWeight;
    }
    public int size() {
        return flyweights.size();
    }
}
```
### 5. 外观模式
#### 介绍

#### 何时使用

#### 优点

#### 实现

### 6. 桥接模式(Bridge Pattern)
#### 介绍

#### 何时使用

#### 优点

#### 实现

### 7. 装饰模式(Decorator Pattern)
#### 介绍

#### 何时使用

#### 优点

#### 实现




## 参考
* [Java中的23种设计模式详解](http://blog.anxpp.com/index.php/archives/489/)
* [Effective Java]