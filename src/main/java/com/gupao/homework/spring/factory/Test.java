package com.gupao.homework.spring.factory;

/**
 * @author: DreamLee
 * @date: Created on 22:54 2018/11/12
 * @description:
 * @modified:
 */
public class Test {

    public static void main(String[] args) {
        Car car = new SimpleFactory().getCar("BMW");
        System.out.println(car.getBrand());
    }

}
