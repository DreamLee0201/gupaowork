package com.gupao.homework.spring.custom;

import com.gupao.homework.spring.proxy.jdk.Jim;
import com.gupao.homework.spring.proxy.jdk.Person;

/**
 * @author: DreamLee
 * @date: Created on 10:52 2018/11/10
 * @description:
 * @modified:
 */
public class TestCustom {

    public static void main(String[] args) {
        Person instance = null;
        try {
            instance = (Person) new GPMatchMaker().getInstance(new Jim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        instance.findLove();

//        try {
//            byte[] data = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Person.class});
//            FileOutputStream fileOutputStream = new FileOutputStream("F:/work/$Proxy0.class");
//            fileOutputStream.write(data);
//            fileOutputStream.close();
//            System.out.println("Finish");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
