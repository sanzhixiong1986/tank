package com.joy.tank;

import com.joy.tank.fire.DefaultFire;
import com.joy.tank.fire.Ifire;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author joy
 * @date 2021/9/13
 */
public class Tank extends AbstractGameObject {
    private static final int SPEED = 5;
    private int x, y;
    //坦克的方向
    private Dir dir;
    //增加一个移动状态
    private boolean moving = false;
    private boolean live = true;
    private boolean bL, bU, bR, bD;
    private Group group;
    private int oldX, oldY;

    public Tank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.initFire();
        this.oldX = x;
        this.oldY = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
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

    /**
     * 绘画
     *
     * @param g
     */
    public void paint(Graphics g) {
        if (!this.isLive()) return;

        if (group.GOOD == Group.GOOD) {
            switch (dir) {
                case L:
                    g.drawImage(ResourceMgr.goodTankL, x, y, null);
                    break;
                case R:
                    g.drawImage(ResourceMgr.goodTankR, x, y, null);
                    break;
                case U:
                    g.drawImage(ResourceMgr.goodTankU, x, y, null);
                    break;
                case D:
                    g.drawImage(ResourceMgr.goodTankD, x, y, null);
                    break;
            }
        }

        if (group.BAD == Group.BAD) {
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
        }

        move();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_A:
                bL = true;
                break;
            case KeyEvent.VK_D:
                bR = true;
                break;
            case KeyEvent.VK_W:
                bU = true;
                break;
            case KeyEvent.VK_S:
                bD = true;
                break;
        }
        setMainDir();
    }

    private void setMainDir() {
        if (!bL && !bU && !bR && !bD) {
            moving = false;
        } else {
            moving = true;
            if (bL && !bU && !bR && !bD) {
                dir = Dir.L;
            }
            if (!bL && !bU && bR && !bD) {
                dir = Dir.R;
            }
            if (!bL && bU && !bR && !bD) {
                dir = Dir.U;
            }
            if (!bL && !bU && !bR && bD) {
                dir = Dir.D;
            }
        }
    }

    private void move() {

        this.oldX = x;
        this.oldY = y;

        if (!moving) return;
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

    public void KeyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_A:
                bL = false;
                break;
            case KeyEvent.VK_D:
                bR = false;
                break;
            case KeyEvent.VK_W:
                bU = false;
                break;
            case KeyEvent.VK_S:
                bD = false;
                break;
            case KeyEvent.VK_J:
                fire();
                break;
        }
        setMainDir();
    }

    private Ifire ifire = null;

    private void initFire() {
        ClassLoader loader = Tank.class.getClassLoader();//获得当前的class
        String className = ProperMgr.INSTANCE.getKey("tankFire");
        try {
            Class clazz = Class.forName("com.joy.tank.fire." + className);
            ifire = (Ifire) (clazz.getDeclaredConstructor().newInstance());//创建对象
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fire() {
        new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
        ifire.fire(this);
    }

    public void die() {
        this.setLive(false);
    }

    public void checkLine() {
        if (x < 0 || x > TankFrame.INSTANCE.GAME_WIDTH - 50 || y < 30 || y > TankFrame.INSTANCE.GAME_HEIGHT - 50) {
            this.x = oldX;
            this.y = oldY;
        }
    }
}
