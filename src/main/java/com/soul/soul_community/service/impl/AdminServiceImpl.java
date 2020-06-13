package com.soul.soul_community.service.impl;

import com.soul.soul_community.entity.Admin;
import com.soul.soul_community.repository.AdminRepository;
import com.soul.soul_community.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName AdminServiceImpl
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/5/1 3:43
 * @Version 1.0
 **/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;


    @Override
    public Admin getAdminByUserName(String userName) {
        return adminRepository.getAdminByUserName(userName);
    }
}
