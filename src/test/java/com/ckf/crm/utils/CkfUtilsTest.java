package com.ckf.crm.utils;


import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;


/**
 * @author 安详的苦丁茶
 * @version 1.0
 * @date 2020/3/20 21:54
 */

public class CkfUtilsTest {


    /**
     * 随机加盐
     */
    @Test
    public void randomSalt() {
        System.out.println("随机生成的盐：" + ShiroUtils.randomSalt());
    }


    /**
     * 密码md5 加盐加密  根据加的盐生成加密的密码
     */
    @Test
    public void encryptPassword() {
        //这里的盐是数据库里的盐 先加盐拿到盐 再对密码加密
        /*System.out.println("使用md5加密两次：" + SecurityUtils.encryptPassword("520520","ckf105623e4e5201e5299095cd89d9e05f574b15838fa0938be"));*/

        /*第二种写法  用户名一定要库的一致*/
        System.out.println("使用md5加密两次：" + ShiroUtils.encryptPassword("123456", "admin" + "df3f84aa7b71"));
        /*System.out.println(new SimpleHash("md5","123456","111111",2));*/
    }


    /**
     * 密码加密测试
     */
    @Test
    public void contextLoads() {

        String hashAlgorithmName = "MD5"; //加密方式
        Object crdentials = "1122";       //密码原值
        Object salt = "12";               //盐值
        int hashIterations = 2;           //加密次数

        Object result = new SimpleHash(hashAlgorithmName, crdentials, salt, hashIterations);
        System.out.println("加密加盐后的密码：" + result);

    }

}