package com.gupao.homework.spring;

import com.gupao.homework.spring.prototype.IronStick;
import com.gupao.homework.spring.prototype.MonkeyKing;
import org.junit.Test;

import java.util.Date;

/**
 * @author: DreamLee
 * @date: Created on 23:01 2018/11/16
 * @description:
 * @modified:
 */
public class MainTest {

    @Test
    public void testPrototype() {
        MonkeyKing monkeyKing = new MonkeyKing(130,50, new Date(), new IronStick());
        MonkeyKing copy = monkeyKing.change();
        System.out.println("真假美猴王是同一个对象吗？" + (monkeyKing == copy));
        System.out.print("真假美猴王是同一天出生吗？" + (monkeyKing.getBirthday() == copy.getBirthday()));
        System.out.println("/t" + monkeyKing.getBirthday().getTime() + "/t" + copy.getBirthday().getTime());
        System.out.println("真假美猴王的金箍棒是同一个对象吗？" + (monkeyKing.getWeapon() == copy.getWeapon()));
    }



}
