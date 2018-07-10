# linux面试题目 

## CSDN
1. 硬链接和软连接区别
2. kill用法，某个进程杀不掉的原因（进入内核态，忽略kill信号）
3. linux用过的命令
4. 系统管理命令（如查看内存使用、网络情况）
5. 管道的使用 |
6. grep的使用，一定要掌握，每次都会问在文件中查找
7. shell脚本
8. find命令
9. awk使用


## 百度内推一面
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
