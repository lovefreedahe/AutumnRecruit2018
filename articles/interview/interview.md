# 面试经历

## 百度提前批 2018.7.9 
### 内容
1. 介绍项目
2. 项目为什么是用Kafka而不是RPC
    Kafka是一种消息队列(MQ)。总之，同步RPC，异步MQ。
    MQ比较适合 
    - 消息的发送者和消费者需要解耦的情况 
    - 发送者并不明确谁是消费者 
    - 发送者并不关心谁来消费消息 
    - 各个消费者可以从不同的角度入手处理消息 
    - 消费者的处理结果也不返回给发送者 
    - 消息的发送和处理是异步的 
    - 消息的关注者不止一个 
3. 算法
    两个大文件(A,B)中分别存储按行着大量字符串，写一个算法输出两个文件中相同的字符串。需要考虑内存不足的情况。
    * 内存充足  
    顺序读取文件A中的字符串，并求hash，存储在HashMap中。然后一次读取文件B中的字符串求hash，看HashMap是否存在对应的字段，如果存在就输出该字符串。
    时间复杂度O(n + m)
    * 内存不足  
    对文件A中的字符串进行排序（可以使用hash），然后分割成n个小文件，文件顺序存储下来，文件大小依据可使用的内存大小分配，然后对B也进行同样的操作，然后根据大小顺序进行比较各部分小文件。
    时间复杂度O(nlogn +mlogm)
4. linux
    linux如何查看大文件的信息
    cat, more, less, tail, head, 
    ```shell
    # more /etc/profile
    ```

    ```shell
    cat 对于内容极大的文件来说，可以通过管道|传送到more 工具，然后一页一页的查看
    # cat /etc/fstab /etc/profile | more 
    ```
    ```shell
    # head -n 10 /etc/profile 
    ```

    ```shell
    # tail -n 5 /etc/profile 
    ```

### 总结
这一次面试开始有点紧张，感觉并没有达到比较好的状态，在介绍项目上总体比较顺畅，但是后面的算法和linux知识，开始没有思路有点慌，结果感觉给面试官留了不好印象。


## OPPO java后台
1、自我介绍
2、项目（zk、HBase的key设计）
3、设计模式
4、并发?J.U.C有没有用过
5、ArrayList LinkedList
6、HashMap实现、ConcurrentHashMap
7、自学数量掌握某一个工具的经历

## 蚂蚁金服
作者：牛客6507054号
链接：https://www.nowcoder.com/discuss/28229?type=2&order=3&pos=37&page=1
来源：牛客网

一、 电面：
* 自我介绍
* 项目情况：
    1. 对你来说影响最大的一个项目（该面试中有关项目问题都针对该项目展开）？
    2. 为什么会想做这个项目？这个项目的ideal是谁提出来的？
    3. 项目中如何实现的大数据的传输和存储
    4. 项目中哪一部分最难攻克？如何攻克？
* 基础知识考察：
    1. 模块化的好处
        * 解耦合
        业务模块解耦合之后，互相之间没有依赖，各自都是独立的模块，某个模块出了问题，也会更快的定位问题，不会对系统产生太大影响
        * 复用
        模块功能比较单一，可以在多个项目中使用。
        * 提升开发效率
        每个模块也是单独的一个项目，可以单独进行编译调试。业务分成多个模块，每个模块由特定团队负责。
    2. Http协议
    3. hashmap和concurrenthashmap区别及两者的优缺点
    4. 对MySQL的了解，和oracle的区别
    5. 对设计模式的看法和认知
    6. 有哪些设计模式
    7. 如何实现分布式缓存
    8. 多线程如何避免死锁
    9. 关于树的算法题-二叉树的锯齿形层次遍历：http://www.lintcode.com/zh-cn/problem/binary-tree-zigzag-level-order-traversal/
    10. Java的垃圾回收机制
    11. 对Runtime的了解
    12. 电面过程中非常注重基础知识的考察，面试前务必对基础知识内容进行复习和梳理。基础知识考察的内容一般会围绕项目内容进行展开，在前期对项目介绍进行准备时需适当换位，思考面试官的提问逻辑，避免给自己设下陷阱。

二、 现场面：
* 背景了解：
    1. 为什么要选择编程这条路
    2. 何时开始编程
    3. 如何进行自学
    4. 阅读过那些书籍
* 项目考察
    * 第一个项目：
        1. 项目最终实现效果
        2. 项目具体部分使用的设计模式并简述选择理由
        3. 项目有何需改进之处并初拟改进方案
    * 第二个项目：
        1. 项目运行过程中成员是否曾就某一点发生争执？作为Leader你是如何解决的？具体事例？
* 基础知识考察：
    1. 数据库的范式
    2. JVM内存模型及调优
    3. 浏览器的缓存机制
    4. 如何解决高并发问题？是否进行过相应程序的编写？
    5. LintCode 算法题 - 最小子串覆盖。原题链接：http://www.lintcode.com/zh-cn/problem/minimum-window-substring/
* 想问的问题
    技术面中项目占了相当一部分时间，项目中从技术到个人团队领导能力，从完成执行能力到思想灵活度，都是面试官希望得到的信息。因此，必须对自己的项目多方面展开准备，项目回顾的过程中需不断思考改进方案和具体措施。

算法题部分由于之前练习时做过且在 www.jiuzhang.com/solution 上研究过参考答案，此次应对较为轻松，面试官反应较好。在算法题考查过程中，正确率和代码效率都是面试官关注的重点，因此在Lintcode上刷题时必须强调每一个细节的改进，不能仅安于正确。上述参考网站中的标准答案工业风较强，适合实际工作，建议在刷题过程中充分利用。此外，面试前做两道算法题练手可使面试时写算法更为娴熟，如时间富裕可尝试。

三、 HR面：
1. 业余爱好
2. 为什么不参加竞赛？
3. 你觉得你在项目运行过程中作为组长是否最大限度发挥了组员的优势？具体事例？
4. 如何看待阿里在大数据方面的投入？对阿里的发展有什么建议？对蚂蚁金服的发展有什么看法？
5. 职业规划，今后想发展的工作方向
6. 家乡在哪里，是否愿意长期在杭州发展？
7. 薪酬问题及入职情况

适当藏拙很重要，因为作为面试者，我们很难判断什么不足对公司来说是无法容忍的，如果临场随性发挥，就容易出现语言漏洞，从而被作为切入点暴露自身缺陷。因此，面试前要仔细准备各种问题，对语言进行推敲，尽量将话题引向有利于自己的方向。

## 面经汇总
[【史上最全面经】后台开发岗-java篇](https://www.nowcoder.com/discuss/84004)

## 同学百度面试题目
* 三次握手和四次挥手
    * 三次握手
        * 请求发起的主机(client)，根据目的主机(server)的ip和端口发送连接请求，具体为一个SYN的包
        * server收到这个请求，并处理，然后返回SYN和ACK包进行确认，表示server接收client的这个连接请求，并且同意连接。
        * 然后等待client发送SYN确认包，以确定client收到了server的确认包，三次握手完成
    * 四次挥手
        * client向server发送断开连接请求
        * server收到请求后回复client，表示收到该请求
        * server在所有包都发送完之后，给client发一个包，表示server所有数据都发送完了，server可以断开了
        * 在client确认都收到所有的包之后，给server发送一个确认包，确认断开连接
* storm，kafka，hdfs可靠性
    * storm 
    ack机制
    * kafka
        * 存储方面
            * Replication(副本)机制。每个partition有多个副本(replica),他们统称为Assigned Replicas(AR), 其中可以提供服务的脚ISR(in-sync replicas),其余的叫OSR(out-sync replica),在ISR中包含一个leader和多个follower，他们对当前partition进行备份。leader最先收到producer的消息，然后同步给其他replica，并写到日志里。每个副本都保存着当前消息存储的状态，如HW(high watermark)，LEO(log end offset)。LEO为当前replica最新保存到日志的消息位置，HW为所有replica的LEO的最小值，consumer最多能消费HW位置。
            
            * 当leader宕机后，controller会重新选举leader，由于consumer最多消费到HW位置，所以数据不会丢失。一般会选举LEO最高的replica为新leader，然后把数据同步到其他follower。
        * 传输方面
            * 可靠性配置
                producer配置request.required.acks
                * 1 只要leader确认就收到就发送下一条数据
                * 0 不需要确认
                * -1 只有等ISR中全部replia都确认才发送(当ISR只有一个replica的时候不能保证数据不会丢失)
            * at least once
                如果一条消息被commit后，由于replication机制，就不会丢失，如果网络中断，会进行retry，所以有可能消息会重复。
    * hdfs

* rowkey怎么设计
    * 散列原则
    不要将相差比较小、连续递增数值(timestamp)放在rowkey前面,这样会导致数据集中在一个RegionServer上，降低负载均衡的几率。最好将rowkey前面作为散列字段，这样可以让rowkey均匀分布，防止同一时间访问同一个RegionServer导致热问题。
    * 长度原则
        最好不要超过16个字节，rowkey最大长度为64kb，
        * 如果rowkey太长，会占用更多HFile空间，影响存储效率
        * 占用更多memstore中的空间，不能缓存更多数据
        * 长度为8的整数倍，目前操作系统基本上都是64位，内存8字节对齐，更好的发挥操作系统的特性
    * 唯一性原则
    
* hbase hive区别
* hash map底层
    * 1.8数组，链表，红黑树
* 红黑树

* final
    * 修饰类
    不能被继承，成员函数也会被指定为final
    * 方法
    不能被继承类修改含义，private默认为final
    * 变量
        * 基本类型 
        不能修改
        * 引用类型
        不能改变引用
* 重写和重载
    * 重写(Overriding)
    子类和父类的多态形式，子类有函数和父类的名称，参数，返回值都一样，则说这个函数被子类重写。
    重写的类如果需要父类的功能，可以使用super函数。
    * 重载(Overload)
    类中多态，对不同的参数实现相同的功能，函数名相同，参数类型或数量不同，返回值也可以不同。

* java线程安全
* linux进程查看

## 百度深研面试
* 项目介绍
* storm消息一致性算法
* storm和hadoop区别
    * 定义与架构   
    hadoop是海量数据分布式处理；storm是实时分布流式处理框架
    * 应用场景  
    hadoop主要用于批处理，较多用于数据挖掘和分析;storm分布式实时计算，主要用于实时性要求较高的场景
    * 数据计算方式
    hadoop是磁盘级运算，需要依赖HDFS和HBase；storm是内存级运算
    * 数据处理方面有哪些不同？
        * 数据来源
        hadoop使用的是HDFS中的数据,数据量比较大;storm的数据是实时增加的
        * 处理过程
        Hadoop是Map和Reduce；Storm的Spout和Bolt都可以处理数据，自定义逻辑
        * 是否结束
        Hadoop最后一定会结束；Storm没有结束状态，有新数据传入在重新开始
        * 处理速度
        hadoop是处理海量数据，比较慢；Storm只处理实时更新的数据，比较快。
        * 适用场景
        hadoop处理一批数据，对时效性要求不高，需要处理就提交一个JOB；Storm主要处理一条数据，失效性要求高。
        
* zookeeper ZAB 有哪些应用 还知道zookeeper的哪些
    * HBase HMaster选举 HRegionServer维护
* 求TopN 用Storm
    遇到这个问题的时候可以思考，如果用普通的算法怎么实现，可以通过维护一个最大堆，或者数据量很大的时候可以将数据分成多个小份，分别求topN，然后在合并结果。
    场景
    * 统计出搜索热度最高的词
    * 点击率最高的广告
    步骤
    * spout负责emit关键字--"word"
    * 下一级多个平行的bolt通过 fieldGrouping 分别统计TopN
    * 最后再由bolt进行汇总统计得到的信息
* final关键字
    * 类
    不能被继承
    * 函数
    不能被子类覆盖，**private默认为final**
    * 变量
    final修饰的变量叫常量，必须初始化，初始化后不能被修改。基本类型，不能修改值；引用类型，不能修改引用，但可以修改值。
* 排序算法  性能  主要应用场景

* linux shell统计脚本传入参数数量 
    ```bash
    #!/bin/bash

    # $0是脚本名称
    echo Script name: $0
    echo $# arguments
    # 注意[]里面的参数要加空格
    if [ $# -ne 2 ];
    then echo "illegal number of parameters"
    fi
    ```
* linux排序指令
    ```bash
    sort(选项)(参数)

    -b：忽略每行前面开始出的空格字符；
    -c：检查文件是否已经按照顺序排序；
    -d：排序时，处理英文字母、数字及空格字符外，忽略其他的字符；
    -f：排序时，将小写字母视为大写字母；
    -i：排序时，除了040至176之间的ASCII字符外，忽略其他的字符；
    -m：将几个排序号的文件进行合并；
    -M：将前面3个字母依照月份的缩写进行排序；
    -n：依照数值的大小排序；
    -o<输出文件>：将排序后的结果存入制定的文件；
    -r：以相反的顺序来排序；
    -t<分隔字符>：指定排序时所用的栏位分隔字符；
    +<起始栏位>-<结束栏位>：以指定的栏位来排序，范围由起始栏位到结束栏位的前一栏位。    
    ```

* TCP UDP区别
    * TCP一对一面向连接，UDP无连接，一对一，一对多，多对多都可以。
    * TCP是可靠的，有拥塞控制，流量控制等机制，UDP发送完就不管了，不可靠
    * **TCP慢，UDP快**
    * **TCP传输大量数据，UDP传输少量数据**
    
* TCP/IP协议 几层分别是什么
    * **网络接口层(物理层和数据链路层)**
    * IP层
    * 传输层
    * 应用层
* HashMap结构 是不是线程安全的 
    * 数组 链表 红黑树(1.7之前没有红黑树)
    * 不是线程安全的
* ConcurrentHashMap和HashTable线程安全怎么实现的
    * ConcurrentHashMap在1.7之前，采用分段锁；1.8采用CAS和synchronize机制
    * HashTable直接在函数前加synchronize
* 红黑树
    
* 有没有用过Spring  没有
* 线程池
    * 
* 进程与线程的区别
    * 地址空间和其他资源：
    进程是相互独立的应用，进程可以有多个线程，进程内的线程在其他进程中不可见
    * 通信：
    进程间通信IPC，线程间可以通过全局变量来通信---需要进程同步和互斥手段保持数据一致性。
    * 调度和切换
        线程的上下文切换比进程快得多
    * 多线程OS中，进程是不可执行的实体。


## 公司实际面试题目汇总
### 阿里
* [豆瓣](https://www.douban.com/note/618521432/)
    * 一面
        1. java的垃圾回收机制
        [参考答案](https://www.jianshu.com/p/5261a62e4d29)
        2. 如何实现java的代理
        3. java和C/C++的区别
        4. 异步加载和延迟加载的实现、兼容性问题
    * 二面
        1. GET和POST的区别
            * GET参数放在URL上，URL长度限制2048个字符，POST长度没有限制
            * GET只能发送ASCII字符，POST可以发二进制
            * GET安全性较差
            * GET请求会被浏览器缓存，刷新不会被重新提交，POST会重新提交
        2. Goagent怎么实现的
        3. 数据库事务隔离机制有什么特点
        4. java的灵活性体现在什么机制上
        反射机制，可以动态创建对象和编译。
        5. 设计模式有哪些
            * 创建型
                * 单例模式
                * 简单工厂模式
                * 静态工厂模式
                * 
            * 行为性
                * 迭代器模式
                * 观察者模式
                * 代理模式
                * 
            * 结构性
                * 桥接模式
        6. 实现多线程有多少种方式
            * Thread
            * Runnable
            * Callable
        7. HashMap与线程安全的问题
            ConcurrentHashMap和HashTable
        8. 怎么检测死循环  
            * JPS 或 ps -ef | grep java得到占用CPU过高的java进程的PID(假如为21425)
            * top -Hp $PID 查看CPU过高的线程ID(假如为21426), 并转换为十六进制53b2
            * jstack把对应PID的stack信息导出来jstack -l $PID < stack.txt
            * 在stack.txt中查找53b2,可以看到如下信息:
                ```bash
                "main" #1 prio=5 os_prio=0 tid=0x00007faf6800a000 nid=0x53b2 runnable [0x00007faf6e4ec000]
                    java.lang.Thread.State: RUNNABLE
                        at com.oceanai.HashMapTest.test(HashMapTest.java:29)
                        at com.oceanai.HashMapTest.main(HashMapTest.java:36)
                ```
* [南京秋招](https://www.aliyun.com/jiaocheng/312209.html)
    * 一面
        你觉得最优的排序算法是什么？
        快排。
        ```java
        import java.util.Stack;
        public class QuickSortTest {
            public void sort(int[] array) {
                quickSort(array, 0, array.length - 1);
            }

            public void sortNonRecursive(int[] array) {
                quickNonRecursive(array, 0, array.length - 1);
            }

            private void quickSort(int [] array, int start, int end) {
                if(start < end) {
                    int position = partition(array, start, end);
                    quickSort(array, start, position - 1);
                    quickSort(array, position + 1, end);
                }
            }

            private void quickNonRecursive(int[] array, int start, int end) {
                if(start < end) {
                    Stack<Record> stack = new Stack<>();
                    int position = partition(array, start, end);
                    if(start <= position - 1) {
                        stack.push(new Record(start, position - 1));
                    }
                    if(end >= position + 1) {
                        stack.push(new Record(position + 1, end));
                    }
                    while(!stack.isEmpty()) {
                        Record record = stack.pop();
                        position = partition(array, record.left, record.right);
                        if(record.left <= position - 1) {
                            stack.push(new Record(record.left, position - 1));
                        }
                        if(record.right >= position + 1) {
                            stack.push(new Record(position + 1, record.right));
                        }
                    }
                }
            }

            private int partition(int[] array, int start, int end) {
                int right = end;
                int mark = array[end];
                while(start < end) {
                    while(start < end && array[start] <= mark) {
                        ++start;
                    }
                    while(start < end && array[end] >= mark) {
                        --end;
                    }
                    if(start < end) {
                        swap(array, start, end);
                    }
                }
                if(right != end) {
                    swap(array, right, end);
                }
                return end;
            }

            private void swap(int[] array, int a, int b) {
                int temp = array[a];
                array[a] = array[b];
                array[b] = temp;
            }

            private class Record {
                int left;
                int right;
                private Record(int left, int right) {
                    this.left = left;
                    this.right = right;
                }
            }

            public static void main(String[] args) {
                QuickSortTest test = new QuickSortTest();
                int[] array = {34, 32, 43, 12, 11, 32, 22, 21, 32};
                test.sort(array);
                System.out.println("Quick sort with recurtion:");
                for(int i : array) {
                    System.out.print(i + " ");
                }
                System.out.println("Quick sort with non-recurtion");
                test.sortNonRecursive(array);
                for(int i : array) {
                    System.out.print(i + " ");
                }
            }
        }
        ```
    * 二面
        大数据

* [牛客网](https://www.nowcoder.com/discuss/76173?type=2&order=3&pos=112&page=1)
    * 三面
        1. 乐观锁和悲观锁，我说到了cas，然后问我java中有哪些地方用到了cas，然后我说concurrenthashmap，然后是咋用的，这个类是怎么保证线程安全的，他还说了一个put啥东西我没注意，就说不知道
            * 悲观锁：
            一个事务执行的某个操作对数据加了锁，其他事务只能等当前事务释放锁，才能够对此数据进行操作。
            * 乐观锁：
            认为所有事务在处理时不会相互影响，每个事务在读取数据后会判断是否有其他事务对此数据进行了修改，如果其他事务有更新的话就回滚当前事务。比如CAS。

        2. 序列化，远程过程调用
        [RPC和restful选择](https://www.zhihu.com/search?type=content&q=rpc%20restful)
        [RPC和消息队列的区别](https://www.zhihu.com/search?type=content&q=rpc%20%E6%B6%88%E6%81%AF%E9%98%9F%E5%88%97)
* [牛客网2菜鸟](https://www.nowcoder.com/discuss/76132?type=2&order=3&pos=114&page=1)
    * 一面
        1. ArrayList和LinkedList区别，ArrayList会不会越界
        ArrayList会越界
        * add(int index, E element) index如果大于当前的size，就会抛出异常。
        2. ArrayList和HashSet区别，HashSet数据是有序的吗
        * ArrayList允许存在重复元素，HashSet不允许重复
        3. volatile和synchronize
            * volatile告诉JVM当前变量在寄存器(工作内存)中的值是不准确的，需要从主存中读取，synchronize会锁定当前变量，只有当前线程可以访问，其它线程会阻塞。
            * volatile只能修饰变量，synchronize可以修饰变量、方法、类
            * volatile不保证原子性，只保证修改可见性；synchronize都可以保证
            * volatile不会阻塞线程
            * volatile修饰的变量编译时不会被优化，synchronize会被优化
        4. 数据库中乐观锁和悲观锁应用场景
        5. 排序算法的复杂度,快速排序非递归实现
            * O(n^2) 选择(不稳定)，插入(稳定)，冒泡(稳定)；O(nlogn) 快排(稳定)，堆(不稳定)，归并(稳定);O(n)~O(n^2) 希尔排序
        6. 海量数据过滤,黑名单过滤一个url，布隆过滤器
        hash
* [牛客网3天猫]
    * 一面
        项目
    * 二面
        1. equals实现方法，如果没看过，该怎么实现
        2. hashmap和hashtable比较
        3. 设计模式了解哪些
        4. 观察者模式还能说说吗
        5. TCP三次握手原理
        6. 有什么想问的
    * 三面
        1. HashMap,ArrayList原理
        2. Concurrent包里的类了解吗,原理是什么
        3. 项目中印象最深刻的是哪一个,然后针对项目提到的技术点提问
        4. 有什么想问的
    * 四面
        1. 自我介绍
        2. 系统如何提高并发性
        3. 学java多久了
        4. java基本类型有哪几种, 列举
        5. int和Integer区别
        6. 学过操作系统吗
        7. 操作系统的内存碎片怎么理解,有什么解决办法
        8. 有什么想问的
    * 五面
        1. 自我介绍
        2. 用什么语言比较多
        3. synchronize锁普通类和静态类的区别，ThreadLocal,Java8新特性
        4. 系统CPU占用比较高是什么原因 
        5. 3：15时针和分针夹角多少度
        6. 了解什么新的技术趋势
        7. 目前对什么技术了解
        8. redis看多源码吗
        9. redis为什么是单线程的
    * HR面
        1. 介绍下能体现你的技术水平的实践/学习经历
        2. 你认为最能学到东西的实践是哪个？然后针对提到的技术点开始追问：异步怎么处理数据一致性？数据库怎么实现分布式事务？2PC的脑裂问题你有什么想法？。。。
        3. 你的未来职业规划是怎么样的
        4. 你对应聘的岗位/部门了解吗
        5. 你有什么想问的

* [牛客网](https://www.nowcoder.com/discuss/74573?type=2&order=3&pos=368&page=1)
    * 一面
        1. 大量数据高并发访问如何优化
        2. 热点数据访问优化
        3. 频繁修改数据如何保证一致性NWR模型，CAP理论等
        4. 平时如何学习
        5. 有没有参与过开源项目
        6. java基本类型有哪些
        7. 实现多线程的三种方式，线程池
        8. 线程是不是开的越多越好？开多少合适？如何减少上下文切换开销，如何写个shell脚本获取上下文切换开销
        9. 乐观锁实现
        10. JVM内存模型，1.6,1.7,1.8有哪些不同
        11. GC算法，可达性分析
        12. 考虑对于老年代如何解决互联网应用中GC停顿的问题,怎么解决内存碎片的问题
        13. 约瑟夫问题O(n)的算法
        14. 看过什么框架源码

    * 二面
        1. 自我介绍
        2. 学过哪些课程
        3. 介绍项目
        4. 排序算法，qsort
        5. AVL树怎么构建,怎么调整
        6. 最短路径算法，迪杰斯特拉，堆优化，正确性分析,SPDFA
        7. 锁，sync,Lock(公平锁，非公平锁)读写锁，CAS,AQS
        8. java泛型的理解,实现和C++的不同
        9. 设计模式？你用过那些设计模式
        10. HashMap,HashTable，1.8优化，ConcurrentHashMap1.8优化
        11. 1.8 新特性
        12. 对IOC的理解
        13. 谈谈对数据库优化的理解？反范式合理冗余数据，合理建索引，使用覆盖索引，问需不需要结合项目说？不用，谈谈就可以。
        14. 介绍一下nio，他好在哪里，epoll实现 红黑树，和select poll的不同。介绍一下aio
        15. 
    * 三面
        1. 自我介绍
        2. 项目
        3. redis
        4. ES分词算法
        5. TF/IDF算法
        6. JVM调优，命令行工具jstack jmap
        7. 倒排索引
        8. A4纸写算法：n个球队，每个球队有一个自己的水平值，写一个随机函数，每次随机获取一个球队，要求球队的分布情况和他们的水平成正比
* [牛客网](https://www.nowcoder.com/discuss/74151?type=2&order=3&pos=415&page=1)
    * 二面
        1. 自我介绍，项目(会被问的很深)
        2. JVM类的加载机制
        3. java中的锁
        4. 反射,怎么实现
        5. 快排思想
        6. HashMap结构，红黑树插入
        7. 
### 美团
* [牛客网](https://www.nowcoder.com/discuss/74573?type=2&order=3&pos=368&page=1)
    * 一面
        1. 自我介绍

        2. 项目介绍

        3. es倒排索引

        4. es分词选型。ik分词器

        5. volatile关键字

        6. hashmap原理

        7. 数据库的锁

        8. 乐观锁悲观锁适用场景，怎么避免死锁

        9. 如何实现lru

        10. 聚簇索引和二级索引的加锁区别

## 百度
* [牛客网](https://www.nowcoder.com/discuss/74573?type=2&order=3&pos=368&page=1)
    * 一面
        1. 自我介绍
        2. arraylist和linkedlist的区别
        3. map
        4. bio和nio
        5. 处理粘包拆包问题
        6. 设计模式
        7. 单例模式的双重校验，为什么使用volatile关键字
        8. 对于spring的理解
        9. 说一说锁，原子变量怎么实现的
        10. 阻塞队列
        11. 说说线程池
        12. threadlocal实现，如何结局的内存泄漏问题
        13. 说说对于数据库设计优化的理解
        14. redis和elasticsearch
    * 二面
        1. 自我介绍
        2. 项目
        3. 两个文件都是10G，里面存着32位整数型，给8G内存，怎么求交集
        4. TF/IDF算法
        5. 一个表有id和mark两个字段，给定一个用户id，一个sql查出来他的排名，
        6. gc算法，内存模型
        7. AOP原理，好处
