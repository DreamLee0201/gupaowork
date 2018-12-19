package com.gupao.homework.spring.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: DreamLee
 * @date: Created on 10:30 2018/11/10
 * @description:
 * @modified:
 */
public class MatchMaker implements InvocationHandler {

    private Person target;

    public Object getInstance(Person target) {
        this.target = target;
        Class<? extends Person> targetClass = target.getClass();
        Object o = Proxy.newProxyInstance(targetClass.getClassLoader(), targetClass.getInterfaces(), this);
        return o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("I am matchmaker!");
        this.target.findLove();
        System.out.println("成了就办事");
        return null;
    }
}
