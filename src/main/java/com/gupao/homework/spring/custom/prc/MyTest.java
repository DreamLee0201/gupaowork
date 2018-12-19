package com.gupao.homework.spring.custom.prc;

/**
 * @author: DreamLee
 * @date: Created on 20:07 2018/11/11
 * @description:
 * @modified:
 */
public class MyTest {

    public static void main(String[] args) {
        try {
            Person instance = (Person) new MyMatchmaker().getInstance(new Jim());
            instance.findLove();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
