package com.soul.soul_community.service;

import com.soul.soul_community.entity.UserLog;

import java.util.List;

public interface UserLogService {

    /**
     * 更具用户的id查询该用户的log
     * @param userId
     * @return
     */
    List<UserLog> getUserLogByUserId(Integer userId,Integer page, Integer pageSize);

    /**
     * 存储用户log
     * @param userLog
     */
    void saveUserLog(UserLog userLog);

    /**
     * 获取所有用户日志
     * @return
     */
    List<UserLog> getAllUserLog();
}
