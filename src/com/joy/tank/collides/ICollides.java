package com.joy.tank.collides;

import com.joy.tank.AbstractGameObject;

import java.io.Serializable;

/**
 * @author joy
 * @date 2021/9/15
 * 碰撞起的接口
 */
public interface ICollides extends Serializable {
    /**
     * 碰撞的两个物体
     *
     * @param g1
     * @param g2
     */
    public void collides(AbstractGameObject g1, AbstractGameObject g2);
}
