# 简介
## 相关术语
**RDBMS(Relational Database Management System)** 关系型管理数据库  
**LAMP(Linux、Apache、MySQL、PHP或Perl、Python)**  
**NoSQL(Not only SQL)** 非关系型数据库

## 背景知识
> 一致性模型 (详见[distribution](./distribution.md)中CAP介绍)
* **严格一致性** 数据的变化是原子的，已经改变即时生效，这是一致性的最高形式
* **顺序一致性** 每个客户端看到的数据依照他们操作执行的顺序而变化
* **因果一致性** 客户端以因果关系顺序观察到数据的变化
* **最终一致性** 在没有更新数据的一段时间里，系统将通过广播保证副本之间的数据一致性
* **弱一致性** 在没有做出保证的情况下，所有的更新会通过广播的形式传递，展现给不同客户端的数据顺序可能不一样  
> **CAP定理**  一个分布式系统只能实现一致性、可用性和分区容忍性（或分区容错性）中的两个。

# 结构
## 背景
**GFS(Google File System)** 适合存储少量的大文件，不适合存储成千上万的小文件。文件的元数据信息会存储在主节点的内存中，文件越多主节点的压力越大。
**RDBMS** 在大规模处理中存在缺点

## 表、行、列和单元格  
HBase最基本的单位是列(column)。一列或多列形成一行(row)，并由唯一的行键(row key)来确定存储。反过来，一个表(table)中有若干行，其中每列有**多个版本**，在每一个单元格(cell)中存储了不同的值。  
所有行键按照**字典序**进行排序存储

<div align="center"><img  src="../resources/images/hbase/hbase_row_column.jpg" width="600"></div>

以下两图表示单元格被写入的时间戳tn可视化了时间组件，圣墟显示了这些值被插入的不同时间。
<div align="center"><img src="../resources/images/hbase/httpatomoreillycomsourceoreillyimages889236.png" width="600"></div>
<div align="center"><img src="../resources/images/hbase/httpatomoreillycomsourceoreillyimages889238.png" width="600"></div>


## 自动分区
HBase扩展和负载均衡的基本单元成为**region**，region本质上是以行键排序的
连续存储的区间。如果region太大，系统会把他们进行拆分，相反的，就把多个region进行合并，以减少存储文件数量。  
一张表初始的时候只有一个region，用户开始向表中插入数据时，系统会检查这个region的大小，确保其不超过配置的最大值。如果超过了这个限制，系统会在中间键(middle key)处将这个region拆分成两个大致相等的子region。  
每一个region只能有一个服务器加载，每一台服务器可以加载多个region。如下图：   
<div align="center"><img src="../resources/images/hbase/httpatomoreillycomsourceoreillyimages889240.png" width="600"></div>

## 存储
* 数据存储在存储文件(store file)中，成为HFile，HFile中存储的是经过排序的键值映射结构。文件内部有连续的块组成，块的索引信息存储在文件尾部。当打开HFile并加载到内存中时，索引信息会优先加载到内存中，每个块默认大小是64K，可以配置。  
* 可以通过HFile的块索引进行二分查找，确定可能包含给定键的块，然后读取磁盘找到实际要找的键。  
* 每次更新数据的时候，都会先将数据记录在提交日志(commit log)中，在HBase中这叫做(write-ahead log, WAL)，然后才会将这些数据写入内存中的memstore中。一旦内存中保存的写入数据累计大小超过了一个给定的最大值(可配置)，系统就会将这些数据移出内存作为HFile刷写到磁盘(HDFS)中。数据flush结束之后，会丢弃对应的WAL，只保留未持久化到磁盘中的日志。  
* 在系统将数据移除memstore写入磁盘的过程中，可以不必阻塞系统的读写，通过滚动内存中的memstore就能达到这个目的。即用新的memstore获取更新的数据，旧的memstore刷写成HFile。(memstore中的数据已经按照行键排序完成了，所以HFile不必在进行排序操作)
* 因为存储文件是不可被改变的，所以删除某一条数据是通过添加删除标记(delete marker, 又称墓碑标记)。在检索过程中，这些删除标记掩盖了实际值，客户端读不到实际值。
* 最终的结果是memstore和磁盘文件两部分数据结果的合并。

## 文件合并
memstore刷写会产生越来越多的HFile，通过管家机制将多个文件合并为一个较大的文件。
* minor合并(minor compaction)  
minor是将多个HFile合并为一个HFile，只是一个多路归并的过程。HFile都是经过归类的，合并会很快，只受磁盘IO的影响。

* major压缩合并(majar compaction)  
major是将一个region中一个列族的若干HFile重写为一个新HFile，与minor相比，major会扫描所有键值对，顺序重写全部数据，过滤掉有删除标记以及超过版本号限制的数据。

<div align="center"><img src="../resources/images/hbase/hbase-files.png" width="600"></div>


## 架构
> HBase3个主要组件：客户端库、一台主服务器(master)、多台region服务器。
<div align="center"><img src="../resources/images/hbase/httpatomoreillycomsourceoreillyimages889242.png" ></div>

* **Master server**  
负责跨region server的全局region的负载均衡，将繁忙的region server中的region移到负载较轻的region server中。不参与数据存储或检索服务，仅提供负载均衡和集群管理，因此是轻量级服务器。提供元数据的管理操作，如建表和创建列族。

* **Region server**   
负责region的读写请求，提供拆分超过配置大小region的接口。客户端直接与region server通信，处理所有数据相关操作。

### 树结构比较
* [从B树、B+树、B*树谈到R 树](https://www.cnblogs.com/hdk1993/p/5840599.html)  
    * B+树优点  
        * B+树能够通过主键对记录进行高效插入、查找和删除，并且提供高效的范围扫描功能。
        * B+树叶节点相互连接并且按主键有序，扫描时避免了耗时的遍历树操作。
        <div align="center"><img src="../resources/images/hbase/400px-Bplustree.png" ></div>  

    * B+树缺点  
        * 页表在磁盘中不一定是相邻的，范围查询可能跨页表的时候，效率很低。
        * 不适合太多修改的操作。


* LSM树(Log-Structured Merge Tree) 
    * 过程  
        * 数据首先存储在日志文件中，并且完全有序，当文件被修改，对应的更新会先保存在内存中加速查询。
        * 内存空间到一定大小，LSM会把数据有序的写到磁盘中，内存的老数据会被丢弃
        * 小文件多了之后，后台线程会自动将小文件 聚合成大文件。
        * 删除添加标记，并不是实际的从磁盘中删除。
    * 优点
        * 存储文件组织与B树类似，不过其为磁盘顺序读取做了优化，所有节点都是满的并且按页存储。系统将现有的页与内存刷写数据混合在一起进行管理，指导数据达到容量。  

        <div align="center"><img src="../resources/images/hbase/2mw8nky.jpg" ></div>  

        * 查询时先查找内存，在查找磁盘。
        * 查询次数在可预测的范围内，成本透明。
    * 缺点
        为了提高读性能牺牲了部分写性能。

> B树和LSM树的主要区别在于他们的结构如何利用硬件，特别是磁盘。

#### **查找与排序和合并的性能瓶颈**
* RDBMS中B树和B+树工作速度受制于磁盘寻道速度，每次查找需要访问磁盘log(N)次。
* LSM使用存储的连续传输能力，并以一定的传输速率排序和合并文件，需要执行log(updates)次操作。

## 客户端API
* 若需要频繁的修改某一行，put操作时设置rawlock，防止其他客户端访问这些行。
* put为RPC操作，不适合短时间发送大量请求。可以使用缓冲区(writer buffer)，缓冲区收集put操作，然后一次调用RPC。使用方法是将autoflush设为false。
    ```java
    table.setAutoFlush(false);
    ...
    flushCommits();
    //or
    setWriteBufferSize();
    ```

# 参考书籍  
* 《HBase权威指南》  
    <div align="center"><img src="../resources/images/hbase/hbase_definitive.jpg" height="400" width="300"></div>

* 《HBase应用架构》   
    <div align="center"><img src="../resources/images/hbase/hbase_arch.jpg" height="400" width = 300></div>