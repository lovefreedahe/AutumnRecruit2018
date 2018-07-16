package com.wangrupeng.pattern.responsibility;

public class Handler3 implements Handler{
    Handler next;
    @Override
    public int handleRequest(int n) {
        if (n < Integer.MAX_VALUE) {
            return n;
        } else {
            if (next != null) {
                return next.handleRequest(n);
            } else {
                throw new NullPointerException("Next 不能为空");
            }
        }
    }

    @Override
    public void setHandler(Handler next) {
        this.next = next;
    }
}
