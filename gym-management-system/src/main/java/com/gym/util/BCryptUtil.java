package com.gym.util;

import org.mindrot.jbcrypt.BCrypt;
import java.util.Properties;
import java.io.InputStream;

public class BCryptUtil {
    private static int workload;
    
    static {
        try (InputStream input = BCryptUtil.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            workload = Integer.parseInt(prop.getProperty("hashing.strength"));
        } catch (Exception e) {
            workload = 10; // default
        }
    }
    
    public static String hashPassword(String plainText) {
        return BCrypt.hashpw(plainText, BCrypt.gensalt(workload));
    }
    
    public static boolean checkPassword(String plainText, String hashed) {
        return BCrypt.checkpw(plainText, hashed);
    }
}