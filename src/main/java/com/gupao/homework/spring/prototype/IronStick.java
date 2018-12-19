package com.gupao.homework.spring.prototype;

import java.io.Serializable;

/**
 * @author: DreamLee
 * @date: Created on 22:51 2018/11/16
 * @description:
 * @modified:
 */
public class IronStick implements Serializable {

    private float h;
    private float r;

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public void grow() {
        h *= 2;
        r *= 2;
    }

    public void shrink() {
        h /= 2;
        r /= 2;
    }
}
