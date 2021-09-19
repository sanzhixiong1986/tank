package com.joy.tank.collides;

import com.joy.tank.AbstractGameObject;
import com.joy.tank.Bullte;
import com.joy.tank.EnmyTank;

/**
 * @author joy
 * @date 2021/9/15
 */
public class BullteTankCollides implements ICollides {
    @Override
    public void collides(AbstractGameObject g1, AbstractGameObject g2) {
        if (g1 instanceof Bullte && g2 instanceof EnmyTank) {
            ((Bullte) g1).collidesWidthTank((EnmyTank) g2);
        } else if (g1 instanceof EnmyTank && g2 instanceof Bullte) {
            collides(g2, g1);
        }
    }
}
