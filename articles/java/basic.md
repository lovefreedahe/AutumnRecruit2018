<script type="text/javascript" async src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-MML-AM_CHTML"> </script>

### 解决Hash冲突
* [链表法](https://www.cs.usfca.edu/~galles/visualization/OpenHash.html)  
将所有hash地址为i的元素构成一个称为同义词链的单链表，并将单链表的头指针存在hash表的第i个元素中。因为查找、插入和删除主要在同义词链中进行，所以链表法比较适合经常进行插入和删除的情况。
* [开放地址法](https://www.cs.usfca.edu/~galles/visualization/ClosedHash.html)  
 formula1:$$n!=x$$
* 再哈希法