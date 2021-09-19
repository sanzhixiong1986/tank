package com.joy.tank;

/**
 * @author joy
 * @date 2021/9/13
 */
public class Main {
    public static void main(String[] args) {
        TankFrame.INSTANCE.setVisible(true);
        new Thread(() -> new Audio("audio/war1.wav").loop()).start();
        //动起来
        while (true) {
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TankFrame.INSTANCE.repaint();
        }
    }
}
