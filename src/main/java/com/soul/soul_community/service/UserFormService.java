package com.soul.soul_community.service;

/**
 * @ClassName UserFormService
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/2/10 15:44
 * @Version 1.0
 **/

public interface UserFormService {

    /**
     * 获取验证邮件
     * @param userEmail
     * @return
     */
    String getMail(String userEmail);
}
