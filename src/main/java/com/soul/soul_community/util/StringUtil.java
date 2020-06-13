package com.soul.soul_community.util;

import cn.hutool.core.util.StrUtil;
import javafx.beans.binding.NumberExpression;
import org.jsoup.Jsoup;

/**
 * @ClassName StringUtil
 * @Deacription 字符串操作
 * @Author Lemonsoul
 * @Date 2020/2/14 14:51
 * @Version 1.0
 **/

public class StringUtil {

    /**
     * 判断字符串为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        if (str == null || str.trim().equals("")){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 判断字符串不为空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str){
        if (str != null && !str.trim().equals("")){
            return true;
        }else {
            return false;
        }
    }

    //获取字符串中的数字
    public static int[] getNumByString(String str){


        String[] ss = StrUtil.removeSuffix(StrUtil.removePrefix(str,"["),"]").split(",");
        int[] num = new int[ss.length];
        for (int i = 0;i < ss.length;i++){
            try {
                num[i] = Integer.parseInt(ss[i].replace("\"",""));
            }catch (NumberFormatException expression){
                expression.printStackTrace();
            }
        }

        return num;
    }




    public static String formatHTML(String html){
        String res = Jsoup.parse(html).text().replaceAll("\r|\n", "");
        if(res.length()<=100){
            return res;
        }
        return res.substring(0,100);
    }
}
