# 用户权限

* 添加用户
    ```bash
    $ sudo adduser username
    ```

* 给用户添加sudo权限
    ```bash
    $ sudo  usermod -aG sudo username
    ```

# Linux输入输出重定向

名称 | 代码 | 操作符 | Java中表示 | Linux下文件描述符(Debian为例)
---|---|---|---|---|
标准输入(stdin)|0|<或<<|System.in|/dev/stdin->/proc/self/fd/0->/dev/pts/0
标准输出(stdout)|1|>,>>,1>或1>>|System.out|/dev/stdout->proc/self/fd/1->/dev/pts/0
标准错误输出(stderr)|2|2>或2>>|System.err|/dev/stderr->/proc/self/fs/2->/dev/pts/0

使用>或>>,默认为标准输出1重定向, 所以 > file 就是 1> file 的省写，1 与 > 之间不能有空格。比如 ls -l > a.txt 就是 ls -l 1> a.txt。数字 0, 1, 2 与它后面的操作符 > 或 < 等 总是一个整体。  
2>&1  表示把 标准错误输出 重定向到 标准输出, 这在控制台下看到的效果 2>&1 和 1>&2 可能是一样的，因为标准输出或标准错误输出的目的地默认都为屏幕。2>&1 是最常见到的写，这样就是把错误信息与标准输出都收集到一块了。&> file 表示把 标准输出 和 标准错误输出 都重定向到文件 file 中。>& file  把标准输出和标准错误输出都重定向到文件 file, 由于 > 默认为重定向标准输出，所以和 1>& file 是一个意思。  
把错误也重定向到标准输出上，一般是用  >file 2>&1, 即分两步，先 标准输出 重定向到 file 中，然后 标准错误输出 定向到 标准输出  
```shell
ls xxx > /dev/null 2>&1
```
同样第一个 > 操作的默认是 1 所以写成 ls xxx 1> /dev/null 2>&1 是一样的  
\>/dev/null 的使用也是惯招，它表示一个空设备，相当于一个黑洞，用来忽略掉输出
顺序要注意，假设 xxx 是不存在的，所以 ls xxx 命令能产生一个错误输出  
```shell
$ ls -l xxx > /dev/null 2>&1
$ 
$ ls xxx 2>&1 > /dev/null
ls: xxx: No such file or directory
```
2>&1, 或  1>&2 中的 & 可以理解为像是 C 中的取地址操作，用于重定向到另一个文件描述符上去。

\> /dev/null 可以关闭输出，还有另一种方式能做到，即 &-
```shell
$ ls xxx 2>&-
```
关闭标准输入也无差异，用 < &-

输出重定向的一个应用例子，有些命令的输出是在 stderr 上，而 grep 只作用在标准输出
```shell
$ java -version | grep version
java version "1.8.0_31"
Java(TM) SE Runtime Environment (build 1.8.0_31-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.31-b07, mixed mode)
```
上面无论 grep 什么都抓不到只想要显示 的行，这时候可以把错误输出导向到标准输出, 然后再 grep  
```shell
$ java -version 2>&1 | grep version
java version "1.8.0_31"
```
输出重定向
指令|说明
---|---|
Command > filename	|把标准输出重定向到一个新文件中
Command >> filename	|把标准输出重定向到一个文件(追加)
Command > filename 2>&1	|把标准输出和错误一起重定向到一个文件中
Command 2> filename|	把标准错误重定向到一个文件中
Command 2>> filename	|把标准错误重定向到一个文件中(追加)
Command >> filename 2>&1|	把标准输出和错误一起重定向到一个文件中(追加)

输入重定向

指令|说明
---|---
Command < filename|	Command 命令以 filename 文件作为标准输入
Command < filename > filename2|	Command 信不信以 filename 文件作为标准输入，以 filename 2 作为标准输出
Command << delimiter|	从标准输入中读入，以  delimiter 为结束符。这就是 Bash 的 HereDoc 用法

绑定重定向
指令|说明
---|---
Command  >&m	|把标准输出重定向到文件描述符 m 中，如 ls >&1 
Command m>&n	|把往文件描述符 m 的输出重定向到文件描述符 n 上，2>71。再如上面的完整写法是 1>&m
Command <&-	|关闭标准输入
Command 2>&-|	关闭标准错误输出，和 2>/dev/null 有类似功效

# du和df的区别
* df:列出文件系统的整体磁盘使用量；  
    df参数：
        -a：列出所有的文件系统，包括系统特有的/proc等文件系统

        -k：以KB的容量显示各文件系统

        -m：以MB的容量显示各文件系统

        -h：以人们较易阅读的GB,MB,KB等格式自行显示

        -H：以M=1000K替代M=1024K的进位方式

        -T：连同该分区的文件系统名称（例如ext3）也列出

        -i：不用硬盘容量，而以inode的数量来显示
    

* du:评估文件系统的磁盘使用量（常用于评估目录所占容量）
    du参数：
        -a : 列出所有的文件与目录容量，因为默认仅统计目录下面的文件量而已；

        -h : 以人们较易读的容量格式（G/M）显示；

        -s : 列出总量，而不列出每个个别的目录占用了容量；

        -S : 不包括子目录下的总计，与-s有点差别；

        -k : 以KB列出容量显示；

        -m : 以MB列出容量显示。
