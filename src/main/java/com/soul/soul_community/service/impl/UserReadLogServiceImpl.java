package com.soul.soul_community.service.impl;

import com.soul.soul_community.repository.UserReadLogRepository;
import com.soul.soul_community.service.UserReadLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName UserReadLogServiceImpl
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/4/14 20:41
 * @Version 1.0
 **/
@Service
@Transactional
public class UserReadLogServiceImpl implements UserReadLogService {

    @Autowired
    UserReadLogRepository userReadLogRepository;

    @Override
    public boolean theFirstRead(Integer articleId, String userIP) {
        if (userReadLogRepository.getUserReadLogByArticleId(articleId,userIP) == null){
            return true;
        }
        return false;
    }
}
