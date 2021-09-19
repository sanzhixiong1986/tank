package com.joy.tank;

import java.awt.*;
import java.io.Serializable;

/**
 * @author joy
 * @date 2021/9/15
 * 抽象物体
 */
public abstract class AbstractGameObject implements Serializable {
    //抽象方法
    public abstract void paint(Graphics g);

    public abstract boolean isLive();
}
