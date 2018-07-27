



# 简历篇
## 请自我介绍
## 请介绍项目
# 基础篇
## 基本功
### 面向对象的特征
### final, finally, finalize 的区别
* final
    * 修饰类
    不能被继承，成员函数默认为final
    * 修饰函数
    不能被子类修改重写，private函数默认为final
    * 变量
    基本类型，值不能被修改；引用对象类型，引用不能改为别的对象的引用，但是值可以被修改。
* finally
    和try，catch一起，无论有没有抛出异常都会调用finally的代码块
* finalize
    jvm对对象进行回收的时候可以调用，如果在内存充足的情况下，一个对象可能很久都不会被回收，所以一般是为了回收调用非java代码占用的内存，如JNI
### int 和 Integer 有什么区别
* int是基本类型，Integer是封装的对象
* Integer需要实例化才能使用
* Integer指向的是new的对象的引用，保存在堆中；int直接存储数据值，放在常量池中。
* 示例代码
    ```java
    public class IntegerTest {
        public static void main(String[] args) {
            int a = 10;
            Integer integer0 = 10;
            System.out.println(a == integer0); //true


            Integer integer1 = 10;
            System.out.println(integer0 == integer1); //true

            //非new生成的Integer0变量指向的是常量池中的对象，new生成的Integer2指向堆中的对象
            Integer integer2 = new Integer(2);
            System.out.println(integer0 == integer2); // false

            //引用不同
            Integer integer3 = new Integer(3);
            System.out.println(integer2 == integer3); //false
            System.out.println(integer2.intValue() == integer3.intValue()); //false

            //自动拆箱会调用valueOf函数，Integer会将-128~127范围的int值进行缓存
            Integer integer4 = 255;
            Integer integer5 = 255;
            System.out.println(integer4 == integer5);
            integer4 = 124;
            integer5 = 124;
            System.out.println(integer4 == integer5);
        }
    }
    ```
### 重载和重写的区别
* 重写
    子类与父类多态的表现，子类中与父类函数名、参数、返回值都相同的函数，表示对父类该函数的重写，如果需要父类的功能可以使用super调用该函数。
    * 参数列表和返回值必须完全相同
    * **访问权限必须大于等于父类的(public>protected>default>private)**
    * 重写方法不能检查被重写方法更大范围的异常，如父类的成员函数检查IOException，子类重写的函数就不能检查Exception
* 重载
    * 类间多态的表现，函数名相同，参数类型或者数量不同，返回值也可以不
    同。
    * 权限修饰符可以不痛
    * 检查异常没有范围限制
### 抽象类和接口有什么区别
* 抽象类可以被实例化，接口不行
* 抽象类成员函数可以是abstract也可以不是，接口都是
* 一个类只能继承一个父类，但是可以继承多个接口
* **抽象类可以不实现接口中的函数,一般的类不行(除了default修饰的函数)**
* **接口中变量为public static final，抽象类可以为public, protected或者private**
* jdk1.8之前抽象类方法默认权限为protected，1.8默认为default；

说一下Java中的4中修饰符的访问权限

访问权限 |  类 |  包 | 子类 | 其他包|
-- | -- | -- | -- | -- | 
public  | ∨ |  ∨ |  ∨ | ∨|
protected  |  ∨ |  ∨  | ∨  |   ×|
default  |  ∨ |  ∨  |  ×  |   ×|
private  |  ∨ |  ×  |   ×  |   ×|

### **说说反射的用途及实现**
* 功能
    * 运行时确定对象的类
    * 运行时构建类的对象
    * 运行时获取类的成员函数
    * 运行时调用类的函数(甚至是private)
* 用途
    * 开发通用框架
    可以通过配置文件让框架加载指定的类,调用不同的方法
    * IDE自动补全和提示
* 忽略权限检查，导致安全问题
* 使用
    * 获得class对象  
        * Class.forName("")
        JDBC加载数据库驱动
        Class.forName(driver);
        * 直接获取某个类的class  
        Class<?> c1 = int.class;
        Class<?> c2 = Integer.class;
        * 调用对象的getClass()方法  
        String str = "";
        Class<?> strC = str.getClass();
    * isInstance()  
        ```java
        public native boolean isInstance(Object obj);
        ```
    * 创建对象
        * 使用反射得到的类  
        ```java
        Class<?> c  = String.calss;
        Object str = c.getInstance();
        ```
        * 使用构造函数
        ```java
        //获取String所对应的Class对象
        Class<?> c = String.class;
        //获取String类带一个String参数的构造器
        Constructor constructor = c.getConstructor(String.class);
        //根据构造器创建实例
        Object obj = constructor.newInstance("23333");
        System.out.println(obj);
        ```
### 说说自定义注解的场景及实现

### HTTP 请求的 GET 与 POST 方式的区别
* GET有长度限制，POST没有
* GET参数放在请求url中，post参数在request body中，所以GET不够安全
* GET请求一次，header和data一起发送，返回200；post先发送header，收到100后在发送data
### session 与 cookie 区别
* session保存在服务器端，cookie保存在客户端
* session用来跟踪client的信息, cookie用来保存用户信息
* 单个cookie一般大小为4kb,单个网站的数量也会有限制
* cookie安全性一般，可以进行cookie欺骗，所以重要信息放在session中
* session比较消耗服务器资源，适当使用cookie可以减轻服务器负担
* session ID保存在cookie中，禁用cookie，session也会失效

### session 分布式处理

### JDBC 流程
### MVC 设计思想
### equals 与 == 的区别
* == 
    * 基本类型，比较值；引用类型，比较引用地址。
    * 指针操作，比较变量(栈)中保存的其在堆中的地址
* equals
    * 比较对象内容。
    * 所有类继承自java.lang.Object，适用于所有对象
    * 特性
        * 自反性
        * 传递性
        * 非空性
        * 一致性
        * 对称性
## 集合
### List,Set 和Map区别
* List和Set都继承自Collection接口
* List
    * 有序
    * 多个值可以为null
    * 允许对象重复
    * ArrayList,LinkedList和Vector
* Set
    * 无序,TreeSet通过Comparable和Comparator接口保证元素有序
    * 还能有一个null
    * 对象不能重复
    * TreeSet,HashSet(基于HashMap),LinkedHashSet
        * TreeSet
        红黑树，元素有序(Comparable和Comparator)
        
* Map
    * 是一个接口
    * 保存的是键值对
    * HashMap, ConcurrentHashMap,TreeMap

### Arraylist , LinkedList 和Vector区别
* ArrayList
    * 数组
    * 访问快，插入和删除慢
* LinkedList
    * 链表
    * 访问慢，插入和删除快
* Vector
    * 线程安全的ArrayList
### HashSet 和 HashMap 区别
* HashSet
    * 实现Set接口
    * 通过HashMap的key保存对象
    * 不允许对象重复，重写hashCode和equals()判断对象是否重复
    * 根据对象内容计算hashcode，hashcode可能相同
* HashMap
    * 实现Map接口
    * 保存键值对
    * 不允许key重复
    * 根据key计算hashcode
### HashMap , ConcurrentHashMap 和HashTable的区别
* 三者都继承自Map接口,HashTable继承自Dictionary类
* HashMap
    * 数组，链表，红黑树
    * key，value都可以为null
    * key不能重复
    * 线程不安全
* ConcurrentHashMap
    * 线程安全的HashMap
    * 性能优于HashTable
* HashTable
    * key和value都不能为null
    * 在函数体加synchronize，性能比ConcurrentHashMap差
### HashMap 的工作原理及代码实现

### ConcurrentHashMap 的工作原理及代码实现

## 线程
### 创建线程的方式及实现
* Runnable接口
    ```java
    class ThreadTest implements Runnable {

        @Override
        public void run() {

        }
    }
    Thread thread = new Thread(new ThreadTest);
    thread.start();
    ```
* Callable接口
    * 相比较于Runnable，run函数有返回值，抛出异常，可以用FutureTast封装
    ```java
    class ThreadTest implements Callable<Integer> {

        @Override
        public Integer call() {
            return 123;
        }
    }
    FutureTask<Integer> futureTask = new FutureTask<>(new ThreadTest());
    Thread thread = new Thread(futureTask);
    thread.start();
    System.out.println(futureTask.get());
    ```
* Thread类
    ```java
    Thread thread = new Thread() {
        @Override
        public void run() {

        }
    };
    thread.start();
    ```
### sleep() 、join()、yield()有什么区别
* sleep()
当前线程休眠一定的时间，单位毫秒。
* join()
在当前线程中执行其他线程的join()，在该线程结束前，当前线程会被挂起，知道该线程运行结束。
* yield() 
表示当前线程已经完成生命周期中的重要部分，可以切换到其他线程，是，该函数只是对线程调度器的一个建议，建议具有相同优先级的线程可以运行。
### 说说 CountDownLatch 原理
### 说说 CyclicBarrier 原理
### 说说 Semaphore 原理
### 说说 Exchanger 原理
### 说说 CountDownLatch 与 CyclicBarrier 区别
### ThreadLocal 原理分析
### 讲讲线程池的实现原理
### 线程池的几种方式与使用场景
### 线程的生命周期
## 锁机制
### 说说线程安全问题
### volatile 实现原理
### synchronize 实现原理
### synchronized 与 lock 的区别
### CAS 乐观锁
### ABA 问题
### 乐观锁的业务场景及实现方式
# 核心篇
## 数据存储
### MySQL 索引使用的注意事项
### 说说反模式设计
### 说说分库与分表设计
### 分库与分表带来的分布式困境与应对之策
### 说说 SQL 优化之道
### MySQL 遇到的死锁问题
### 存储引擎的 InnoDB 与 MyISAM
### 数据库索引的原理
### 为什么要用 B-tree
### 聚集索引与非聚集索引的区别
### limit 20000 加载很慢怎么解决
### 选择合适的分布式主键方案
### 选择合适的数据存储方案
### ObjectId 规则
### 聊聊 MongoDB 使用场景
### 倒排索引
### 聊聊 ElasticSearch 使用场景
## 缓存使用
### Redis 有哪些类型
### Redis 内部结构
### Redis 内存淘汰机制
### 聊聊 Redis 使用场景
### Redis 持久化机制
### Redis 集群方案与实现
### Redis 为什么是单线程的
### 缓存崩溃
### 缓存降级
### 使用缓存的合理性问题
## 消息队列
### 消息队列的使用场景
### 消息的重发补偿解决思路
### 消息的幂等性解决思路（已解答，待补充）
### 消息的堆积解决思路
### 自己如何实现消息队列
### 如何保证消息的有序性
# 框架篇
## Spring
### BeanFactory 和 ApplicationContext 有什么区别
### Spring Bean 的生命周期
### Spring IOC 如何实现
### 说说 Spring AOP
### Spring AOP 实现原理
### 动态代理（cglib 与 JDK）
### Spring 事务实现方式
### Spring 事务底层原理
### 如何自定义注解实现功能
### Spring MVC 运行流程
### Spring MVC 启动流程
### Spring 的单例实现原理
### Spring 框架中用到了哪些设计模式
### Spring 其他产品（Srping Boot、Spring Cloud、Spring Secuirity、### Spring Data、Spring AMQP 等）
## Netty
### 为什么选择 Netty
### 说说业务中，Netty 的使用场景
### 原生的 NIO 在 JDK 1.7 版本存在 epoll bug
### 什么是TCP 粘包/拆包
### TCP粘包/拆包的解决办法
### Netty 线程模型
### 说说 Netty 的零拷贝
### Netty 内部执行流程
### Netty 重连实现
# 微服务篇
## 微服务
### 前后端分离是如何做的
### 如何解决跨域
### 微服务哪些框架
### 你怎么理解 RPC 框架
### 说说 RPC 的实现原理
### 说说 Dubbo 的实现原理
### 你怎么理解 RESTful
### 说说如何设计一个良好的 API
### 如何理解 RESTful API 的幂等性
### 如何保证接口的幂等性
### 说说 CAP 定理、 BASE 理论
### 怎么考虑数据一致性问题
### 说说最终一致性的实现方案
### 你怎么看待微服务
### 微服务与 SOA 的区别
### 如何拆分服务
### 微服务如何进行数据库管理
### 如何应对微服务的链式调用异常
### 对于快速追踪与定位问题
### 微服务的安全
## 分布式
### 谈谈业务中使用分布式的场景
### Session 分布式方案
### 分布式锁的场景
### 分布是锁的实现方案
### 分布式事务
### 集群与负载均衡的算法与实现
### 说说分库与分表设计
### 分库与分表带来的分布式困境与应对之策
### 安全问题
### 安全要素与 STRIDE 威胁
### 防范常见的 Web 攻击
### 服务端通信安全攻防
### HTTPS 原理剖析
### HTTPS 降级攻击
### 授权与认证
### 基于角色的访问控制
### 基于数据的访问控制
### 性能优化
### 性能指标有哪些
### 如何发现性能瓶颈
### 性能调优的常见手段
### 说说你在项目中如何进行性能调优
# 工程篇
## 需求分析
### 你如何对需求原型进行理解和拆分
### 说说你对功能性需求的理解
### 说说你对非功能性需求的理解
### 你针对产品提出哪些交互和改进意见
### 你如何理解用户痛点
## 设计能力
### 说说你在项目中使用过的 UML 图
### 你如何考虑组件化
### 你如何考虑服务化
### 你如何进行领域建模
### 你如何划分领域边界
### 说说你项目中的领域建模
### 说说概要设计
## 设计模式
### 你项目中有使用哪些设计模式
### 说说常用开源框架中设计模式使用分析
### 说说你对设计原则的理解
### 23种设计模式的设计理念
### 设计模式之间的异同，例如策略模式与状态模式的区别
### 设计模式之间的结合，例如策略模式+简单工厂模式的实践
### 设计模式的性能，例如单例模式哪种性能更好。
## 业务工程
### 你系统中的前后端分离是如何做的
### 说说你的开发流程
### 你和团队是如何沟通的
### 你如何进行代码评审
### 说说你对技术与业务的理解
### 说说你在项目中经常遇到的 Exception
### 说说你在项目中遇到感觉最难Bug，怎么解决的
### 说说你在项目中遇到印象最深困难，怎么解决的
### 你觉得你们项目还有哪些不足的地方
### 你是否遇到过 CPU 100% ，如何排查与解决
### 你是否遇到过 内存 OOM ，如何排查与解决
### 说说你对敏捷开发的实践
### 说说你对开发运维的实践
### 介绍下工作中的一个对自己最有价值的项目，以及在这个过程中的角色
## 软实力
### 说说你的亮点
### 说说你最近在看什么书
### 说说你觉得最有意义的技术书籍
### 说说个人发展方向方面的思考
### 说说你认为的服务端开发工程师应该具备哪些能力
### 说说你认为的架构师是什么样的，架构师主要做什么
### 说说你所理解的技术专家
# HR 篇
### 你为什么离开之前的公司
### 你为什么要进我们公司
### 说说职业规划
### 谈一谈你的一次失败经历
### 你觉得你最大的优点是什么
### 你觉得你最大的缺点是什么
### 你在工作之余做什么事情
### 你为什么认为你适合这个职位
### 你觉得自己那方面能力最急需提高
### 你来我们公司最希望得到什么
### 你对现在应聘的职位有什么了解
### 您还有什么想问的
### 你怎么看待自己的职涯
### 谈谈你的家庭情况
### 你有什么业余爱好
### 你计划在公司工作多久