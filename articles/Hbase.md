# 简介
## 相关术语
**RDBMS(Relational Database Management System)** 关系型管理数据库  
**LAMP(Linux、Apache、MySQL、PHP或Perl、Python)**  
**NoSQL(Not only SQL)** 非关系型数据库

## 背景知识
> 一致性模型  
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
* 每次更新数据的时候，都会先将数据记录在提交日志(commit log)中，在HBase中这叫做(write-ahead log, WAL)，然后才会将这些数据写入内存中的memstore中。一旦内存中保存的写入数据累计大小超过了一个给定的最大值(可配置)，系统就会将这些数据移出内存作为HFile刷写到磁盘(HDFS)中。数据flush结束之后，会丢弃对应的WAL，只保留未持久化到内存中的日志。  
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

# 参考书籍  
* 《HBase权威指南》  
    <img src="../resources/images/hbase/hbase_definitive.jpg" height="400" width="300">

* 《HBase应用架构》   
    <img src="../resources/images/hbase/hbase_arch.jpg" height="400" width = 300>