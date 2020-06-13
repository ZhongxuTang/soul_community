package com.soul.soul_community.service;

import com.soul.soul_community.entity.User;

import java.util.List;

/**
 * @ClassName UserService
 * @Deacription UserService接口
 * @Author Lemonsoul
 * @Date 2020/2/13 20:32
 * @Version 1.0
 **/

public interface UserService {

    /**
     * 根据邮箱查找用户实体
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     * 根据用户id来查询用户实体
     * @param userId
     * @return
     */
    User findByUserId(Integer userId);

    /**
     * 保存或修改用户信息
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户经验
     * @param userId
     */
    void updateUserExperience(Integer userId,Integer experience);

    /**
     * 获取用户数量
     * @return
     */
    Long getUserCount();

    /**
     * 获取所有用户
     * @return
     */
    List<User> getAllUser();

    /**
     * 更新用户所属文章数量
     * @param userId
     */
    void updateUserArticleCount(Integer articleCount,Integer userId);

    /**
     * 更新用户问答数量
     * @param userId
     */
    void updateUserQuestionCount(Integer questionCount,Integer userId);

}
