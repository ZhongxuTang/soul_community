package com.soul.soul_community.util;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @ClassName GenerateVerificationCode
 * @Deacription 生成验证码
 * @Author Lemonsoul
 * @Date 2020/2/11 15:28
 * @Version 1.0
 **/
@Component
public class GenerateVerificationCode {

    public String verCode(){
        Random random = new Random(1000);
        String result="";
        for (int i = 0;i < 6;i++){
            result+=random.nextInt(10);
        }
        return result;
    }
}
