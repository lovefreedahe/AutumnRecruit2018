# 第一章
## 1.1 上下文切换
### 1.1.1 多线程一定比单线程快吗？
* 上下文切换不一定快
* 上下文切换需要耗时

循环次数 | 串行执行消耗时间 | 并发执行消耗时间 | 并发比串行快多少 |
-- | -- | -- | -- | -- |
1亿 | 130 | 77 | 约一倍 |
1千万 | 18 | 9 | 约一倍 |
1百万 | 5 | 5 | 差不多|
10万 | 4 | 3 | 慢 |
1万 | 0 | 1 | 慢 |

### 1.1.3 如何减少上下文切换
* 无锁并发编程
根据数据ID按照hash算法取模分段，不同的线程处理不同段的数据。
* CAS算法

* 使用最少线程
避免创建不必要的线程
* 协程
在单线程里实现多任务的调度，并在单线程里面维持多个任务间的切换

## 1.2 死锁

# 第二章
## 2.1 volatile的应用
