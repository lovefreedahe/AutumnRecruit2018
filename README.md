<!-- TOC -->

- [1 准备计划](#1-准备计划)
    - [1.1 目标](#11-目标)
- [2 内容](#2-内容)
    - [2.1 算法](#21-算法)
    - [2.2 操作系统](#22-操作系统)
    - [2.3 计算机网络](#23-计算机网络)
    - [2.4 Java](#24-java)
        - [Java基础](#java基础)
        - [设计模式](#设计模式)
        - [面试](#面试)
    - [2.5 项目](#25-项目)
        - [分布式](#分布式)
        - [其他](#其他)
    - [2.6 工具](#26-工具)
    - [2.7 深度学习](#27-深度学习)
    - [2.8 区块链](#28-区块链)
    - [2.9 面试总结](#29-面试总结)
    - [2.10 总复习](#210-总复习)
- [3 招聘信息](#3-招聘信息)
    - [目前已经公布的公司招聘计划](#目前已经公布的公司招聘计划)
    - [笔试面试时间](#笔试面试时间)
    - [2017年互联网公司秋季招聘时间参考](#2017年互联网公司秋季招聘时间参考)
    - [复习计划](#复习计划)

<!-- /TOC -->

## 1 准备计划
### 1.1 目标
工作准备找与大数据处理有关的职位，研究生期间基本上做的也是相关工作。为此需要数量掌握的技能如上面目录中的内容。其中深度学习和区块链仅作为兴趣了解。本文部分内容引用了另一位作者在Github上整理的复习内容，更加全面，对此表示特别感谢！[CyC2018/Interview-Notebook](https://github.com/CyC2018/Interview-Notebook)

## 2 内容
### 2.1 算法
> [剑指offer题解(java版)](articles/algorithm/剑指offer.md)

> 企业笔试题目

> [算法面试经验](articles/algorithm/interview.md)

> [排序算法介绍和比较](articles/algorithm/sort.md)

### 2.2 操作系统

[操作系统](articles/operation/operation_system.md)

### 2.3 计算机网络
> [计算机网络面经汇总](articles/network/interview.md)

> [http](articles/network/http.md)

> [基础知识](articles/network/network.md)

### 2.4 Java
#### Java基础

* [基础](articles/java/basic.md)
* [数据结构](articles/java/datastructure.md)
* [java虚拟机](articles/java/jvm.md)
* [java并发编程](articles/java/concurrency.md)
* [Effective Java](articles/java/effectivejava.md)  

#### 设计模式
[java中的23种设计模式](articles/java/JavaDesignPattern.md)

#### 面试
[interview](articles/java/interview.md)

### 2.5 项目
#### 分布式

> [Distribution](articles/distribution/distribution.md)

> [Zookeeper](articles/distribution/Zookeeper.md)

> [Kafka](articles/distribution/Kafka.md)

> [Hbase](articles/distribution/Hbase.md)

> [Storm](articles/distribution/storm/Storm.md)

#### 其他
> [Apache Lucene](articles/lucene.md)

### 2.6 工具
* [git](tools/git.md)

* docker 

* [linux](articles/linux/linux.md)

### 2.7 深度学习

1. 卷积特征图大小计算方式
    * 公式
    ( input_size + 2*padding - kernel_size ) / stride = output_size

    * 题目
    输入图片大小为200×200，依次经过一层卷积（kernel size 5×5，padding 1，stride 2），pooling（kernel size 3×3，padding 0，stride 1），又一层卷积（kernel size 3×3，padding 1，stride 1）之后，输出特征图大小为：
    卷积向下取整，池化向上取整。
    (200 - 5 + 2 * 1) / 2 + 1 = 99.5~99
    (99 - 3 + 2 * 0) / 1 + 1 = 97
    (97 - 3 + 2 * 1) / 1 + 1 = 97


### 2.8 区块链


### 2.9 面试总结
* [面试题目汇总](articles/interview/total.md)
* [面试题目汇总答案](articles/interview/total_answer.md)
* [个人面试总结](articles/interview/interview.md)
* [面试准备](articles/interview/summary.md)
* [每日复习总结](articles/interview/everyday.md)

### 2.10 总复习
[总复习](articles/复习.md)

## 3 招聘信息

### 目前已经公布的公司招聘计划

公司 | 招聘类别 | 截止日期 | 投递链接 | 其他 
-- | -- | -- | -- | --
拼多多 | 学霸批 |  8.3 | [拼多多](https://mp.weixin.qq.com/s?__biz=MzI3MzQzMDEwNw==&mid=2247484279&idx=1&sn=ea88bdbcc4c2a7d063c6f13e2be2041f&chksm=eb222043dc55a955e42b1bbe07a1b7ef1fde0421f3873bd3fae1cfa872c505b6a4afbb59efcc&mpshare=1&scene=23&srcid=0702ucJuM0Mv4pxz5v56dNYL#rd) | 
阿里巴巴 | 内推 | 7.27 | [阿里巴巴](https://www.jianshu.com/p/50ec6fd13e9c?utm_campaign=hugo&utm_medium=reader_share&utm_content=note&utm_source=weixin-timeline&from=timeline&isappinstalled=0) | -- |
京东 | 提前批 | 随时交简历，如通过筛选会在8月30日前随时发起面试。7月中旬正常校招网申也记得申请哦（campus.jd.com），提前批失败不影响正常校招。 |  | 简历投递地址：liuyumeng3@jd.com | 
好未来 | 2019校招 |  | [好未来java工程师](http://job.100tal.com/jobxq?jobId=510212779) | |
~~Oppo~~ | 2019校招 | | [Oppp职位](http://oppotqp.zhaopin.com/) | 宣讲会：7月12日 华中科技大学 光学与电子信息学院613|
深信服 | 2019校招 | |[大数据工程师](http://hr.sangfor.com/graduate/graduate_position.html) | |
唯品会 | 2019秋招 | hadoop平台开发工程师(上海) | [招聘地址](http://campus.vip.com/rec_1.html) | |
Google | 2019校招 | 笔试时间:UTC D轮: 7月29号 E轮: 8月26号 下午1点-4点| [详细介绍地址](https://mp.weixin.qq.com/s/4jKL7p-cnWmu80esBzIzyg)|  |
华为 | 2019校招tiqianpi | 截止时间8.10 | [官方地址](http://career.huawei.com/reccampportal/campus4_index.html#campus4/pages/joblist/jobDetail.html?jobId=53176&d=1531799732689&type=2&jobFamClsCode=JFC1) | |
网易 | 2019校招 | 内推：7.16-8.8 | [官方地址](https://campus.163.com/app/index) | 内推码:V3Q1127 笔试时间17年为8.13 | 
顺丰科技 | 2019校招 | 内推截止时间没说(说7.30开始面试) | [申请地址]() | 内推码(任填一个)：376493,372448,374647|
同程艺龙 | 2019秋招 | 10.31 | http://join.ly.com/ | |
珍爱网 | 秋招 | 10.12 | http://zhenai.zhiye.com/Campus ||
百度深研 | 内推 | 百度深研提前批校招，招聘岗位C++后台，Java大数据，机器学习，简历请发送到: zhaorongcun@baidu.com，简历命名格式：姓名+岗位+学校+电话 邮件标题格式：应聘岗位+姓名+学校| |  Java研发工程师 |
百度智能云 | 内推 | 职位名称：基础平台软件研发工程师/运维研发工程师 联系方式： hujiahuan@baidu.com 简历格式：学校_学历_专业_姓名，如xx大学_硕士_计算机软件与理论_张三 工作地点：北京西北旺百度科技园 | | |


### 笔试面试时间
公司 | 类别 | 时间
-- | -- | -- |
完美世界 | 笔试 | 8.28



### 2017年互联网公司秋季招聘时间参考

1. 阿里巴巴 内推时间：7月18日*8月18日 网申时间：7月18日*9月6日
2. 腾讯网申时间：7月14日*9月2日
3. 百度 内推时间：7月12日*7月30日 网申时间：9月14日截止
4. 网易有道 网申时间：8月1日（全岗）
5. 滴滴 网申时间：8月31日*10月30日
6. 京东 网申时间：8月1日*9月4日
7. IBM 网申时间：8月29日*10月20日
8. 今日头条 网申时间：8月8日*9月30日
9. 搜狐 网申时间：8月25日起
10. 携程 内推时间：7月27日*8月29日 网申时间：8月22日*10月9日
11. 新美大 内推时间：9月7日之前 网申时间：8月22日*10月9日
12. 58同城 网申时间：11月开始
13. 小米 网申时间：9月5日*9月27日
14. 新浪 网申时间：9月起
15. 爱奇艺 网申时间：8月10日*9月30日
16. 去哪儿网 网申时间：8月1日*10月9日


### 复习计划

2018.7.8
* 简历内容复习
    * Storm, HBase, Lucene, Kafka, 分布式
    * 计算机网络
    * 操作系统
    * 深度学习
* 算法
* 基础知识
* Linux
* [面试准备](articles/prepare.md)

2018.7.14
* HBase
* Zookeeper
* 数据结构
* java并发

2018.7.15
* java并发
* 设计模式

2018.7.19
* ~~priority queue~~
* 排序算法
* b树 b+树
* 计算机网络和面试题目内容
* 请求网页发生了什么

2018.7.20
* ~~计算机网络+面试题目~~
* ~~b树 b+树~~
* ~~排序算法~~
* 面试项目
* 操作系统
* ~~Huffman~~

2018.7.24
* [面试知识点整理](http://blog.720ui.com/2018/java_interview_final/?hmsr=toutiao.io&utm_medium=toutiao.io&utm_source=toutiao.io#%E7%AE%80%E5%8E%86%E7%AF%87)

* 用过的框架复习

* 算法刷题

2018.7.25
项目知识点复习
* 重点
    * java
    * Storm
    * HBase
    * Zookeeper 版本
    * Kafka
    * docker
    * Apache Lucene(倒排索引)
    * linux
* 了解
    * websocket
    * FFmpeg
    * python,Clojure

2018.7.25
* concurrenthashmap
    CAS + synchronize
* NIO
* 红黑树
* LSH局部敏感hash

2018.7.28
* linux知识点整理 包括指令和shell
* 大数据面试题目整理
* 基础知识复习
* 红黑树
* 常用算法 熟

2018.7.29

类别 | 是否完成 |
-- | -- |
计算机网络 | 否 |
数据结构 | 否 |
java基础 ||
java虚拟机 | 否 |
并发 | 否 |
排序算法 | 否 |
Linux | |
分布式框架 |  | 
分布式实际问题 |  |

路线规划
Java语言基础 -> 分布式计算框架理解和应用  
计算机网络, 网络协议, 操作系统(linux)同步, 刷题


2018.8.5
* 并发 线程池
* 算法
* 笔试刷题
* 设计模式


2018.8.6
* 并发 线程池
* 算法
* 笔试刷题
* 设计模式
* 树 红黑树

2018.8.10
* 并发
* 排序
* 计算机网络 计算题
* IP地址和子网划分
* 设计模式 每一个的概念
* hadoop
* linux
* 面试之前模拟面试可能问到的问题(设计模式，并发等)

2018.8.11
* 树
* 并发 线程池
* 计算机网络
* 算法 排序 以及其他典型算法
* 设计模式
* linux

2018.8.12 
* 并发
* 树
* 计算机网络
* 算法 排序 其他典型算法 dfs bfs
* 设计模式
* mysql

2018.8.20
* 计算机网络
* java
* 设计模式
* 算法
* 数据结构
* 数据库
* 操作系统

2018.8.21
* 计算机网络
* JVM
* 分布式
* 数据结构
* java基础
* 算法
* 数据库

2018.8.23
* 简历英文版
* java知识复习
* 算法
* MySQL(B,B+,红黑树)
* 老虎证券
    Java开发工程师
    【你要做的】
    金融业务系统开发
    1. 负责产品服务端的开发工作
    2. 深入理解业务需求，提供具体问题的解决方案
    3. 负责相关模块的开发和改进，保证系统性能和稳定性
    【你要具备的】
    1. Java基础扎实，**熟练掌握常用设计模式**，**对多线程并发编程等常用技术有深刻理解**；
    2. 熟悉常用的开源框架和开源服务(Spring, MyBatis, Netty, MySQL,Redis等) 
    3. **熟悉JVM，对JVM调优、性能优化有经验者优先**
    4. 熟悉 Linux体系结构，Shell、Python 等脚本语言优先

2018.8.24
* 计算机网络

2018.8.29
* java基础查缺补漏
* 大数据框架 之间的关系  深入了解
* 算法
* 计算机网络
* 操作系统
* 动手练习