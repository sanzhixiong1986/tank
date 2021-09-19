package com.joy.tank;

import java.awt.*;

/**
 * @author joy
 * @date 2021/9/14
 * 子弹类
 */
public class Bullte extends AbstractGameObject{
    private int x, y;
    private Dir dir;
    private static final int SPEED = 6;
    private Group group;
    private boolean live = true;


    public Bullte(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public void paint(Graphics g) {
        switch (dir) {
            case L:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case R:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case U:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }
        move();
    }

    private void move() {
        switch (dir) {
            case L:
                x -= SPEED;
                break;
            case R:
                x += SPEED;
                break;
            case U:
                y -= SPEED;
                break;
            case D:
                y += SPEED;
                break;
        }
        checkLine();
    }

    //检测边界
    public void checkLine() {
        if (x < 0 || x > TankFrame.INSTANCE.GAME_WIDTH || y < 0 || y > TankFrame.INSTANCE.GAME_HEIGHT) {
            this.setLive(false);
        }
    }

    //子弹与tank发生碰撞
    public void collidesWidthTank(EnmyTank tank) {
        if (!tank.isLive()) return;
        if (tank.getGroup() == this.group) return;
        //获得两个框
        Rectangle rectBullte = new Rectangle(x, y, ResourceMgr.bulletU.getWidth(), ResourceMgr.bulletU.getHeight());
        Rectangle rectTank = new Rectangle(tank.getX(), tank.getY(),
                ResourceMgr.goodTankU.getWidth(), ResourceMgr.goodTankU.getHeight());
        //判断是否相交
        if (rectBullte.intersects(rectTank)) {
            this.die();
            tank.die();
        }
    }

    private void die() {
        this.setLive(false);
    }
}
