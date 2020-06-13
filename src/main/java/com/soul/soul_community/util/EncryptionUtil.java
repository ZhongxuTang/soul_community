package com.soul.soul_community.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @ClassName EncryptionUtil
 * @Deacription 加密工具类
 * @Author Lemonsoul
 * @Date 2020/2/13 20:58
 * @Version 1.0
 **/

public class EncryptionUtil {

    public final static String SALT = "code";

    /**
     * MD5加密
     * @return
     */
    public static String md5(String str,String salt){
        return new Md5Hash(str,salt).toString();
    }


  /*  public static void main(String[] args) {
        String password = "123456";
        System.out.println(EncryptionUtil.md5(password,SALT));
    }*/
}
