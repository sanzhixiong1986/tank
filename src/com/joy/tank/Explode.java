package com.joy.tank;

import java.awt.*;

/**
 * @author joy
 * @date 2021/9/14
 */
public class Explode extends AbstractGameObject{

    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int x, y;
    private int setp = 0;
    private boolean isLive = true;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;

        //播放声音
        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public void paint(Graphics g) {

        if (!isLive) return;

        g.drawImage(ResourceMgr.explodes[setp++], x, y, null);
        if (setp >= ResourceMgr.explodes.length) {
            this.over();
        }
    }

    private void over() {
        this.isLive = false;
    }
}
