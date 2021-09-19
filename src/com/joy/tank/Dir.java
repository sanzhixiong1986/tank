package com.joy.tank;

import java.util.Random;

/**
 * @author joy
 * @date 2021/9/13
 */
public enum Dir {
    L, U, R, D;

    private static Random random = new Random();
    public static Dir RamDir(){
        return values()[random.nextInt(values().length)];
    }
}
