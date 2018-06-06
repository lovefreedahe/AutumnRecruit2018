### 解决Hash冲突
* [链表法](https://www.cs.usfca.edu/~galles/visualization/OpenHash.html)  
将所有hash地址为i的元素构成一个称为同义词链的单链表，并将单链表的头指针存在hash表的第i个元素中。因为查找、插入和删除主要在同义词链中进行，所以链表法比较适合经常进行插入和删除的情况。
* [开放地址法(再散列法)](https://www.cs.usfca.edu/~galles/visualization/ClosedHash.html)  
当关键字key的hash地址p=H(key)出现冲突时，以p为基础，产生另一个hash地址p1，如果p1仍然冲突，再以p为基础，产生另一个hash地址p2，... ，知道产生不冲突的hash地址pi，通用再散列函数形式：  
<div align="center"><img src="../../resources/images/java/eqn4431.png"></div>  
其中H(key)为hash函数，m为表长，

* 再哈希法