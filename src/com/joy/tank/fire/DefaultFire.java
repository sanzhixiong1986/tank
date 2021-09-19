package com.joy.tank.fire;

import com.joy.tank.Bullte;
import com.joy.tank.ResourceMgr;
import com.joy.tank.Tank;
import com.joy.tank.TankFrame;

/**
 * @author joy
 * @date 2021/9/15
 */
public class DefaultFire implements Ifire {
    @Override
    public void fire(Tank t) {
        int bx = t.getX() + ResourceMgr.goodTankU.getWidth() / 2 - ResourceMgr.bulletU.getWidth() / 2;
        int by = t.getY() + ResourceMgr.goodTankU.getHeight() / 2 - ResourceMgr.bulletU.getHeight() / 2;
        TankFrame.INSTANCE.getGm().add(new Bullte(bx, by, t.getDir(), t.getGroup()));
    }
}
