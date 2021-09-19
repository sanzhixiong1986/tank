package com.joy.tank.fire;

import com.joy.tank.Tank;

import java.io.Serializable;

/**
 * @author joy
 * @date 2021/9/15
 * 基础类开火
 */
public interface Ifire extends Serializable {
    public void fire(Tank t);
}
