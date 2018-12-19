package com.gupao.homework.spring.custom.prc;

import java.lang.reflect.Method;

/**
 * @author: DreamLee
 * @date: Created on 20:05 2018/11/11
 * @description:
 * @modified:
 */
public interface MyInvocationHandler {

    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;

}
