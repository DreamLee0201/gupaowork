package com.gupao.homework.spring.proxy.jdk;

import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author: DreamLee
 * @date: Created on 10:52 2018/11/10
 * @description:
 * @modified:
 */
public class Test {

    public static void main(String[] args) {
        Person instance = (Person) new MatchMaker().getInstance(new Jim());
        instance.findLove();

        try {
            byte[] data = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Person.class});
            FileOutputStream fileOutputStream = new FileOutputStream("F:/work/$Proxy0.class");
            fileOutputStream.write(data);
            fileOutputStream.close();
            System.out.println("Finish");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
