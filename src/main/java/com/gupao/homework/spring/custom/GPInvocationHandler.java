package com.gupao.homework.spring.custom;

import java.lang.reflect.Method;

/**
 * @author: DreamLee
 * @date: Created on 23:13 2018/11/10
 * @description:
 * @modified:
 */
public interface GPInvocationHandler {

    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;

}
