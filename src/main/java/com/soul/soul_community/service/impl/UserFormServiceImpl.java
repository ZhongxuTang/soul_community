package com.soul.soul_community.service.impl;

import com.soul.soul_community.service.UserFormService;
import com.soul.soul_community.util.GenerateVerificationCode;
import com.soul.soul_community.util.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserFormServiceImpl
 * @Deacription 用户表单服务类
 * @Author Lemonsoul
 * @Date 2020/2/10 15:43
 * @Version 1.0
 **/

@Service
public class UserFormServiceImpl implements UserFormService {

    @Autowired
    private MailUtils mailUtils;

    @Autowired
    private GenerateVerificationCode gvn;

    /**
     * 获取验证邮件
     * @param userEmail
     * @return
     */
    @Override
    public String getMail(String userEmail) {
        String text = gvn.verCode();

        try{
            mailUtils.sendTextMail(userEmail,"邮箱验证",text);
        }catch (Exception e){
            return "验证码发送错误";
        }
        System.out.println("内容"+text);
        System.out.println("接受者"+userEmail);
        return text;
    }

}
