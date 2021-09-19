package com.joy.tank;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * @author joy
 * @date 2021/9/14
 */
public class EnmyTank extends AbstractGameObject {
    private static final int SPEED = 5;
    private int x, y;
    //坦克的方向
    private Dir dir;
    //增加一个移动状态
    private boolean live = true;
    private boolean moving = true;
    private boolean bL, bU, bR, bD;
    private Group group;
    private static final int width = 60;
    private int oldX, oldY;
    private int _width, _height;

    public EnmyTank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        //第一次获得x，y的数据
        this.oldX = x;
        this.oldY = y;

        _width = ResourceMgr.goodTankU.getWidth();
        _height = ResourceMgr.goodTankU.getHeight();
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void paint(Graphics g) {
        if (!this.isLive()) return;

        switch (dir) {
            case L:
                g.drawImage(ResourceMgr.badTankL, x, y, null);
                break;
            case R:
                g.drawImage(ResourceMgr.badTankR, x, y, null);
                break;
            case U:
                g.drawImage(ResourceMgr.badTankU, x, y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.badTankD, x, y, null);
                break;
        }
        move();
    }

    private void move() {
        if (!this.moving) return;

        oldX = x;
        oldY = y;

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

        //检测
        checkLine();
        //随机方向
        romDir();
        //随机子弹
        if (r.nextInt(100) > 93) {
            fire();
        }
    }

    private Random r = new Random();

    private void romDir() {
        if (r.nextInt(100) > 93)
            this.dir = Dir.RamDir();
    }

    private void fire() {
        int bx = x + ResourceMgr.goodTankU.getWidth() / 2 - ResourceMgr.bulletU.getWidth() / 2;
        int by = y + ResourceMgr.goodTankU.getHeight() / 2 - ResourceMgr.bulletU.getHeight() / 2;
        TankFrame.INSTANCE.getGm().add(new Bullte(bx, by, dir, group));
    }

    public void checkLine() {
        if (x < 0 || x > TankFrame.INSTANCE.GAME_WIDTH - _width || y < 30 || y > TankFrame.INSTANCE.GAME_HEIGHT - _height) {
            this.x = oldX;
            this.y = oldY;
        }
    }

    public void die() {
        TankFrame.INSTANCE.getGm().add(new Explode(x, y));
        this.setLive(false);
    }
}
