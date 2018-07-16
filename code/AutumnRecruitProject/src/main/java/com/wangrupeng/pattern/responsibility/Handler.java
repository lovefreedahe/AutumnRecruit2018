package com.wangrupeng.pattern.responsibility;

public interface Handler {
    int handleRequest(int n);
    void setHandler(Handler next);
}
