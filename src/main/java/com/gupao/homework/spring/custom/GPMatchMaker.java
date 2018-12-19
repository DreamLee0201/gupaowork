package com.gupao.homework.spring.custom;

import com.gupao.homework.spring.proxy.jdk.Person;

import java.lang.reflect.Method;

/**
 * @author: DreamLee
 * @date: Created on 23:23 2018/11/10
 * @description:
 * @modified:
 */
public class GPMatchMaker implements GPInvocationHandler {

    private Person target;

    public Object getInstance(Person target) throws Exception {
        this.target = target;
        Class<? extends Person> clazz = target.getClass();
        System.out.println("被代理对象的class是：" + clazz);
        return GPProxy.newProxyInstance(new GPClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("I am matchmaker!");
        method.invoke(target, args);
        System.out.println("成了就办事");
        return null;
    }
}
