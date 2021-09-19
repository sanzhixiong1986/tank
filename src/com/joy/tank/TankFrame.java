package com.joy.tank;

import com.joy.tank.collides.BullteTankCollides;
import com.joy.tank.collides.ICollides;
import com.joy.tank.fire.Ifire;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author joy
 * @date 2021/9/13
 */
public class TankFrame extends Frame {


    public static final TankFrame INSTANCE = new TankFrame();
    public static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
    private GameModel gm = new GameModel();

    private TankFrame() {
        this.setTitle("tank war");
        this.setLocation(400, 100);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);

        //设置模式，观察者模式
        this.addKeyListener(new MyTankKeyListener());
    }

    @Override
    public void paint(Graphics g) {
        gm.paint(g);
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.black);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }


    private class MyTankKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            gm.getMyTank().keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_O) {
                //保存
                save();
            } else if (key == KeyEvent.VK_L) {
                load();
            }
            gm.getMyTank().KeyReleased(e);
        }
    }

    private void save() {
        ObjectOutputStream oos = null;

        try {
            File f = new File("./tank.data");
            FileOutputStream fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(gm);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null)
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }

    private void load() {
        ObjectInputStream ois = null;
        try {
            File f = new File("./tank.data");
            FileInputStream fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            this.gm = (GameModel) (ois.readObject());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public GameModel getGm() {
        return this.gm;
    }
}
