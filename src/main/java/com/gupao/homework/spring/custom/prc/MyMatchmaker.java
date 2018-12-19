package com.gupao.homework.spring.custom.prc;

import java.lang.reflect.Method;

/**
 * @author: DreamLee
 * @date: Created on 20:07 2018/11/11
 * @description:
 * @modified:
 */
public class MyMatchmaker implements MyInvocationHandler {

    private Person target;

    public Object getInstance(Person target) throws Exception {
        this.target = target;
        Class<? extends Person> clazz = target.getClass();
        return MyProxy.newProxyInstance(new MyClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("say something before invoke");
        method.invoke(target, args);
        System.out.println("say something after invoke");
        return null;
    }

}
