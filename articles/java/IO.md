
# NIO
## select, poll, epoll
都是IO多路复用的机制,可以监听多个文件描述符的读写时间，一旦某个描述符就绪(一般就是读写事件发生了)，就能够将读写事件通知给关心的程序去处理。本质上都是同步IO
[大话 Select、Poll、Epoll](https://cloud.tencent.com/developer/article/1005481)