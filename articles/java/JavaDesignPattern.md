
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
    - [工厂方法模式（Factory Method Pattern）](#工厂方法模式factory-method-pattern)
        - [简单工厂模式](#简单工厂模式)
        - [工厂方法模式](#工厂方法模式)

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

### 工厂方法模式（Factory Method Pattern）
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
如果一个一些对象（产品），已经确定了并不易改变和添加新的产品，那么久可以使用简单工厂模式。下面就是简单工厂的例子：
```java
//演示简单工厂
public class SimpleFactory {
    public static void main(String args[]) throws Exception{
        Factory factory = new Factory();
        factory.produce("PRO5").run();
        factory.produce("PRO6").run();
    }
}
//抽象产品
interface MeizuPhone{
    void run();
}
//具体产品X2
class PRO5 implements MeizuPhone{
    @Override
    public void run() {
        System.out.println("我是一台PRO5");
    }
}
class PRO6 implements MeizuPhone{
    @Override
    public void run() {
        System.out.println("我是一台PRO6");
    }
}
//工厂
class Factory{
    MeizuPhone produce(String product) throws Exception{
        if(product.equals("PRO5"))
            return new PRO5();
        else if(product.equals("PRO6"))
            return new PRO6();
        throw new Exception("No Such Class");
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
    public static void main(String args[]){
        IFactory bigfactory;
        bigfactory = new SmallFactory();
        bigfactory.produce().run();
        bigfactory = new BigFactory();
        bigfactory.produce().run();
    }
}
//抽象产品
interface MeizuPhone{
    void run();
}
//具体产品*2
class PRO5 implements MeizuPhone{
    @Override
    public void run() {
        System.out.println("我是一台PRO5");
    }
}
class MX5 implements MeizuPhone{
    @Override
    public void run() {
        System.out.println("我是一台MX5");
    }
}
interface IFactory{//抽象的工厂
    MeizuPhone produce();
}
//工厂*2
class BigFactory implements IFactory{
    @Override
    public MeizuPhone produce() {
        return new PRO5();
    }
}
class SmallFactory implements IFactory{
    @Override
    public MeizuPhone produce() {
        return new MX5();
    }
}
```
如果了解Java的集合框架，那么它就是一个很好的例子：
Java中的Collection接口的实现都能通过iterator()方法返回一个迭代器，而不同的实现的迭代器都在该实现中以内部类的方式对Iterator接口实现的，然后通过iterator()方法返回。那么，这个iterator()方法就是一种工厂方法。

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

* 数组的实现： 
```java
package com.anxpp.designpattern.factorymethod;
//方便演示而实现的简陋的数组list
public class ArrayList<T> implements List<T>{
    private int size;               //存放的元素个数,会默认初始化为0
    private Object[] defaultList;   //使用数组存放元素
    private static final int defaultLength = 10;//默认长度
    public ArrayList(){             //默认构造函数
        defaultList = new Object[defaultLength];
    }
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
package com.anxpp.designpattern.factorymethod;
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

#### 抽象工厂方法模式（Abstract Factory Pattern）
别名：配套（Another Name：Kit）
上述生成魅族产品的例子中，我们只生产了手机，但是它不止有手机一种产品，可能还有其他的，比如耳机，为了还可以生成耳机，我们需要对上例进行扩展。

我们先给出上面生成手机的例子的扩展后的抽象工厂模式代码，以比较这几种模式：
```java
//抽象工厂模式
public class AbstractFactory {
    public static void main(String args[]){
        IFactory bigfactory = new BigFactory();
        IFactory smallfactory = new BigFactory();
        bigfactory.producePhone().run();
        bigfactory.produceHeadset().play();
        smallfactory.producePhone().run();
        smallfactory.produceHeadset().play();
    }
}
//抽象产品*2
interface Headset{
    void play();
}
//抽象产品
interface MeizuPhone{
    void run();
}
//具体产品*2*2
class PRO5 implements MeizuPhone{
    @Override
    public void run() {
        System.out.println("我是一台PRO5");
    }
}
class MX5 implements MeizuPhone{
    @Override
    public void run() {
        System.out.println("我是一台MX5");
    }
}
class EP21 implements Headset{
    @Override
    public void play() {
        System.out.println("我是一副EP21");
    }
}
class EP30 implements Headset{
    @Override
    public void play() {
        System.out.println("我是一台EP30");
    }
}
//抽象工厂
interface IFactory{
    MeizuPhone producePhone();
    Headset produceHeadset();
}
//具体工厂*2
class BigFactory implements IFactory{
    @Override
    public MeizuPhone producePhone() {
        return new PRO5();
    }
    @Override
    public Headset produceHeadset() {
        return new EP30();
    }
}
//具体工厂*2
class SmallFactory implements IFactory{
    @Override
    public MeizuPhone producePhone() {
        return new MX5();
    }
    @Override
    public Headset produceHeadset() {
        return new EP21();
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

实际情况下，我们可能不应该这么做，以为Collection面向一种对象的容器，Map是面向两种对象的关联容器，但是此例使用抽象工厂模式确实实现了不同容器的 统一遍历方式。

如果一个容器持有的大量对象，他们都直接或间接集成自某一个类，使用访问者模式遍历也是一种很好的方式，具体在后面的访问者模式中会详细介绍。

工厂模式主要就涉及上面介绍的三种：
* 简单工厂模式是由一个具体的类去创建其他类的实例，父类是相同的，父类是具体的。
* 工厂方法模式是有一个抽象的父类定义公共接口，子类负责生成具体的对象，这样做的目的是将类的实例化操作延迟到子类中完成。
* 抽象工厂模式提供一个创建一系列相关或相互依赖对象的接口，而无须指定他们具体的类。它针对的是有多个产品的等级结构。而工厂方法模式针对的是一个产品的等级结构。

### 生成器模式（Builder Pattern）
将一个复杂对象的构建与它的表示分离，使同样的构建过程可以创建不同的表示。

* 何时使用
    * 当系统准备为用户提供一个内部结构复杂的对象，而且在构造方法中编写创建该对象的代码无法满足用户需求时，就可以使用生成器模式老构造这样的对象。
    * 当某些系统要求对象的构造过程必须独立于创建该对象的类时。
* 优点
    * 生成器模式将对象的构造过程封装在具体的生成器中，用户使用不同的具体生成器就可以得到该对象的不同表示。
    * 生成器模式将对象的构造过程从创建该对象的类中分离出来，使用户无须了解该对象的具体组件。
    * 可以更加精细有效的控制对象的构造过程。生成器将对象的构造过程分解成若干步骤，这就是程序可以更加精细，有效的控制整个对象的构造。
    * 生成器模式将对象的构造过程与创建该对象类解耦，是对象的创建更加灵活有弹性。
    * 当增加新的具体的生成器是，不必修改指挥者的代码，即该模式满足开-闭原则。

模式的重心在于分离构建算法和具体的构造实现，从而使构建算法可以重用。

比如我们要得到一个日期，可以有不同的格式，然后我们就使用不同的生成器来实现。

首先是这个类（产品）：
```java
//产品
public class MyDate {
    String date;
}
```

 然后就是抽象生成器，描述生成器的行为：
 ```java
//抽象生成器
public interface IDateBuilder {
    IDateBuilder buildDate(int y,int m,int d);
    String date();
}
 ```
 接下来是具体生成器，一个以“-”分割年月日，另一个使用空格：
 ```java
//具体生成器
public class DateBuilder1 implements IDateBuilder{
    private MyDate myDate;
    public DateBuilder1(MyDate myDate){
        this.myDate = myDate;
    }
    @Override
    public IDateBuilder buildDate(int y, int m, int d) {
        myDate.date = y+"-"+m+"-"+d;
        return this;
    }
    @Override
    public String date() {
        return myDate.date;
    }
}
 ```

### 5. 原型模式(Prototype Pattern)


## 行为型模式

### 1. 责任链模式(Chain of Responsibility Pattern)

### 2. 命令模式(Command Pattern)

### 3. 解释器模式(Interpreter Pattern)

### 4. 迭代器模式(Iterator Pattern)

### 5. 中介者模式(Mediator Pattern)

### 6. 备忘录模式

### 7. 观察者模式(Observer Pattern)

### 8. 状态模式(State Pattern)

### 9. 策略模式(Strategy Pattern)

### 10. 模板方法模式(Template Method Pattern)

### 11. 访问者模式(Visitor Pattern)

## 结构型模式
### 1. 适配器模式(Adapter Pattern)

### 2. 组合模式(Composite Pattern)

### 3. 代理模式(Proxy Pattern)

### 4. 享元模式(Plyweight Pattern)

### 5. 外观模式

### 6. 桥接模式(Bridge Pattern)

### 7. 装饰模式(Decorator Pattern)




## 参考
* [Java中的23种设计模式详解](http://blog.anxpp.com/index.php/archives/489/)