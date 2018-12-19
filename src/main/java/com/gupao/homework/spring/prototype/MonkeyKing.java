package com.gupao.homework.spring.prototype;

import java.io.*;
import java.util.Date;

/**
 * @author: DreamLee
 * @date: Created on 22:48 2018/11/16
 * @description:
 * @modified:
 */
public class MonkeyKing implements Cloneable,Serializable {

    private int hight;
    private int weight;
    private Date birthday;
    private IronStick weapon;

    public MonkeyKing(int hight, int weight, Date birthday, IronStick weapon) {
        this.hight = hight;
        this.weight = weight;
        this.birthday = birthday;
        this.weapon  = weapon;
    }

    public int getHight() {
        return hight;
    }

    public void setHight(int hight) {
        this.hight = hight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public IronStick getWeapon() {
        return weapon;
    }

    public void setWeapon(IronStick weapon) {
        this.weapon = weapon;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public MonkeyKing change() {
        MonkeyKing copy = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            copy = (MonkeyKing) ois.readObject();
//            copy = (MonkeyKing) this.clone();
//            copy.birthday = new Date();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return copy;
    }
}
