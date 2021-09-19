package com.joy.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @author joy
 * @date 2021/9/15
 * 配置文件读取
 */
public class ProperMgr {
    public static ProperMgr INSTANCE = new ProperMgr();
    private Properties prop;

    private ProperMgr() {
        prop = new Properties();
        try {
            prop.load(ProperMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据key获得value
     * @param key
     * @return
     */
    public String getKey(String key) {
        return (String) prop.get(key);
    }
}
