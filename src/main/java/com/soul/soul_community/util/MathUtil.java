package com.soul.soul_community.util;

/**
 * @ClassName MathUtil
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/3/5 20:50
 * @Version 1.0
 **/

public class MathUtil {

    public static Long pageCount(Long articCount){
        if (articCount % 6 == 0){
            return articCount/6;
        }else {
            return (articCount/6)+1;
        }

    }
}
