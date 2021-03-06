# 网上整理的算法面经
## 基础

1. 剑指OFFER的各个题目是最常见的，即使不是原题也是题目的变体，因为面试不像笔试，一般不会出特别困难的题目，所以剑指OFFER上小而精的题目就非常适合。建议手刷一遍。PHP的同学可以参考专栏剑指OFFER

2. 二叉树相关（层次遍历、求深度、求两个节点距离、翻转二叉树、前中后序遍历）

3. 链表相关（插入节点、链表逆置、使用链表进行大数字的加减，双向链表实现队列、寻找链表中的环）
堆（大量数据中寻找最大N个数字几乎每次都会问，还有堆在插入时进行的调整）
4. 排序（八大排序，各自的时间复杂度、排序算法的稳定性。快排几乎每次都问）
5. 二分查找（一般会深入，如寻找数组总和为K的两个数字）
6. 两个栈实现队列。
7. 图（深度广度优先遍历、单源最短路径、最小生成树）
8. 动态规划问题。

## 深入

1. 红黑树性质
2. 分治法和动态规划的区别
3. 计算时间复杂度
4. 二叉树和哈希表查找的区别和时间复杂度
    二叉树是基于比较的排序，所以时间复杂度为 O(log n)，哈希查找是一个函数映射，所以能做到 O(1)

## 具体题目记录 
1. 一个文本文件中每一行中有一个URL，最多一万行，统计每一个URL的次数，输出到另外一个文件中，每一行前面是URL，后面是个数。 
2. 单链表的逆序 
3. 一个函数实现给定字符串，去除前面和后面的空格，比如“ ab cd ”，最后得到的结果是”ab cd”，不能改变字符串的地址。 
4. 对比cookie和session,有一个值错误则不正确 
5. 查找10的阶乘后面有几个0 
6. 字符串匹配 
7. 字符串移位，给出字符串abc##dfg##gh，实现将所有#移至字符串串头。输出####abcdfggh（个人认为可以用后向移位，减少移位次数） 
8. 给出一颗二叉树，两个叶节点，找到这两个叶节点互连通的一条最短路径。 
9. 两个日期计算天数差 
10. 100个有序数组合并 
11. 矩阵的最大子矩阵和 
12. 给定一棵多叉树，每个节点有一个编号，现在要对节点排序，要求对于每个节点，它的父节点排在它后面,如果一棵树有N个节点，那么肯定有N-1条边。输入的数据形式为：Map.Entry
13. hash算法在信息安全方面的应用：
* 文件检验
我们比較熟悉的校验算法有奇偶校验和CRC校验，这2种校验并没有抗数据篡改的能力，它们一定程度上能检測并纠正传输数据中的信道误码，但却不能防止对数据的恶意破坏。

MD5 Hash算法的”数字指纹”特性，使它成为眼下应用最广泛的一种文件完整性校验和(Checksum)算法，不少Unix系统有提供计算md5 checksum的命令。
* 数字签名
Hash 算法也是现代password体系中的一个重要组成部分。因为非对称算法的运算速度较慢，所以在数字签名协议中，单向散列函数扮演了一个重要的角色。 对 Hash 值，又称”数字摘要”进行数字签名，在统计上能够觉得与对文件本身进行数字签名是等效的。并且这种协议还有其它的长处。
* 鉴权协议
例如以下的鉴权协议又被称作挑战–认证模式：在传输信道是可被侦听，但不可被篡改的情况下，这是一种简单而安全的方法。
**文件hash值**
MD5-Hash-文件的数字文摘通过Hash函数计算得到。无论文件长度怎样，它的Hash函数计算结果是一个固定长度的数字。与加密算法不同，这一个Hash算法是一个不可逆的单向函数。採用安全性高的Hash算法，如MD5、SHA时，两个不同的文件差点儿不可能得到同样的Hash结果。因此，一旦文件被改动，就可检測出来。