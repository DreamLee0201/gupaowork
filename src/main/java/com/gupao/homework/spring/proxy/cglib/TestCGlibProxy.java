package com.gupao.homework.spring.proxy.cglib;

/**
 * @author: DreamLee
 * @date: Created on 15:33 2018/11/11
 * @description:
 * @modified:
 */
public class TestCGlibProxy {

    public static void main(String[] args) {

        //FF.FF.FF.FF
        //255.255.255.255
        //0.0.0.0

        //IP6
        //FFFF.FFFF.FFFF.FFFF.FFFF.FFFF
        //2^16-1.2^16-1.2^16-1.2^16-1.2^16-1.2^16-1
        //0.0.0.0.0.0

        //AOP 解耦(团队开发)
        //变相：三层架构(架构)
        //如果整个项目都是一个人全部完成

        //CGLib的动态代理是通过生成一个被带离对象的子类，然后重写父类的方法
        //生成以后的对象，可以强制转换为被代理对象（也就是用自己写的类）
        //子类引用赋值给父类

        try {
            Jimmy jimmy = (Jimmy) new Matchmaker().getInstance(new Jimmy());
            jimmy.findLove();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
