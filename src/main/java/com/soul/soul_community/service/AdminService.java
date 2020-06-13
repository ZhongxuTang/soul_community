package com.soul.soul_community.service;

import com.soul.soul_community.entity.Admin;

public interface AdminService {

    /**
     * 根据用户名获取管理员
     * @param userName
     * @return
     */
    Admin getAdminByUserName(String userName);
}
