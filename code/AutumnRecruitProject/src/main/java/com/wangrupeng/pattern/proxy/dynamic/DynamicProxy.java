package com.wangrupeng.pattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {

    private Object subject;
    public DynamicProxy(Object realSubject) {
        this.subject = realSubject;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before say hello");
        System.out.println("Method: " + method);
        method.invoke(subject, args);
        System.out.println("After say hello");
        return null;
    }
}
