package com.wangrupeng.pattern.responsibility;

public class Handler1 implements Handler {
    Handler next;
    @Override
    public int handleRequest(int n) {
        if (n < 0) {
            return -n;
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
