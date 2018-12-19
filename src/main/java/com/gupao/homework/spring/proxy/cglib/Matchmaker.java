package com.gupao.homework.spring.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: DreamLee
 * @date: Created on 15:25 2018/11/11
 * @description:
 * @modified:
 */
public class Matchmaker implements MethodInterceptor {

    public Object getInstance(Object obj) throws Exception {
        Enhancer enhancer = new Enhancer();
        //把父类设置为谁？
        //这一步就是高数cglib，生成的子类需要继承那个类
        enhancer.setSuperclass(obj.getClass());
        //设置回调
        enhancer.setCallback(this);
        //第一步，生成源代码
        //第二步，编译成class文件
        //第三步，加载到JVM中，并返回被代理对象
        return enhancer.create();
    }

    //同样是做了字节码重组这样一件事情
    //对于使用API的用户来说，是无感知的
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("I am matchmaker!");
        //这个obj的引用是由CGLib给我们new出来的
        //cglib new出来以后的对象，是被代理对象的子类（继承了我们自己写的那个类）
        //OOP，在new子类之前，实际上默认先调用了我们super()方法的
        //new了子类的同时，必须先new出来父类，这就相当于是间接的持有了我们父类的引用
        //子类重写了父类的所有方法
        //我们改变子类对象的某些属性，是可以间接的操作父类的属性的
        methodProxy.invokeSuper(o, objects);
        System.out.println("成了就办事");
        return null;
    }
}
