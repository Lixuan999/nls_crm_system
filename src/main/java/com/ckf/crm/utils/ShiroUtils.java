package com.ckf.crm.utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;


/**
 * @author xuan
 * @version 1.0
 * @date 2020/3/25 17:22
 */
public class ShiroUtils {

    /**
     * 使用原生密码+盐 通过md5加密生成密码  的方法
     *
     * @param password        原生密码
     * @param credentialsSalt 加强盐  （userName +salt）
     * @return
     */
    public static String encryptPassword(String password, String credentialsSalt) {
        return new Md5Hash(password, credentialsSalt, 2).toHex().toString();
    }

    /**
     * 生成随机盐  (插入记录的时候，可以调用)
     */
    public static String randomSalt() {
        // 一个Byte占两个字节，此处生成的3字节，字符串长度为6
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        String hex = secureRandom.nextBytes(6).toHex();
        return hex;
    }

}
