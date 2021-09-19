package com.joy.tank;

import com.joy.tank.collides.ICollides;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author joy
 * @date 2021/9/16
 */
public class GameModel implements Serializable {

    private Tank myTank;
    private List<AbstractGameObject> abs;
    private List<ICollides> iCollides;

    public GameModel() {
        initCrateObject();
        initCrateCollide();
    }

    public void add(AbstractGameObject abo) {
        this.abs.add(abo);
    }

    private void initCrateObject() {

        myTank = new Tank(100, 100, Dir.R, Group.GOOD);
        abs = new ArrayList<>();

        //创建n个坦克读取配置文件
        int tankNum = Integer.parseInt(ProperMgr.INSTANCE.getKey("intGameTankNum"));
        for (int i = 0; i < tankNum; i++) {
            this.add(new EnmyTank(100 + 60 * i, 200, Dir.D, Group.BAD));
        }
    }

    private void initCrateCollide() {
        this.iCollides = new ArrayList<>();
        //测试
        String[] collodes = ProperMgr.INSTANCE.getKey("Collides").split(",");
        for (String names : collodes) {
            try {
                Class clazz = Class.forName("com.joy.tank.collides." + names);
                ICollides iCollidess = (ICollides) (clazz.getDeclaredConstructor().newInstance());//创建对象
                iCollides.add(iCollidess);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 画图
     *
     * @param g
     */
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("objets" + abs.size(), 10, 50);
        g.setColor(c);

        myTank.paint(g);
        for (int i = 0; i < abs.size(); i++) {
            //判断是否要删除
            if (!abs.get(i).isLive()) {
                abs.remove(i);
                break;
            }
            //首先获得后项的类
            AbstractGameObject go1 = abs.get(i);
            //优化后
            for (int j = 0; j < abs.size(); j++) {
                AbstractGameObject go2 = abs.get(j);
                for (ICollides ic : iCollides) {
                    ic.collides(go1, go2);
                }
            }
            this.abs.get(i).paint(g);
        }
    }

    public Tank getMyTank() {
        return myTank;
    }
}
