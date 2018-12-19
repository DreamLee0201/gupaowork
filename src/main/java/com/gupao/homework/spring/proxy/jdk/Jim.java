package com.gupao.homework.spring.proxy.jdk;

/**
 * @author: DreamLee
 * @date: Created on 10:27 2018/11/10
 * @description:
 * @modified:
 */
public class Jim implements Person {

    private String name = "Jim";

    @Override
    public void findLove() {
        System.out.println("My name is " + this.name + "! I want find my soul mate!");
    }
}
