package com.gupao.homework.spring.factory;

/**
 * @author: DreamLee
 * @date: Created on 22:53 2018/11/12
 * @description: 实现统一管理，专业化管理，如果没有工厂模式，小作坊，没有执行标准的
 * @modified:
 */
public class SimpleFactory {

    public Car getCar(String name) {
        if ("BMW".equalsIgnoreCase(name)) {
            return new BMW();
        }
        if ("Audi".equalsIgnoreCase(name)) {
            return new Audi();
        }
        return null;
    }
}
