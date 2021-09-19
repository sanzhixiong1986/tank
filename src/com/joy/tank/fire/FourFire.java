package com.joy.tank.fire;

import com.joy.tank.*;

/**
 * @author joy
 * @date 2021/9/15
 */
public class FourFire implements Ifire {
    @Override
    public void fire(Tank t) {
        int bx = t.getX() + ResourceMgr.goodTankU.getWidth() / 2 - ResourceMgr.bulletU.getWidth() / 2;
        int by = t.getY() + ResourceMgr.goodTankU.getHeight() / 2 - ResourceMgr.bulletU.getHeight() / 2;

        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            TankFrame.INSTANCE.getGm().add(new Bullte(bx, by, dir, t.getGroup()));
        }
    }
}
