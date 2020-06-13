package com.soul.soul_community.service.impl;

import com.soul.soul_community.entity.FeedBack;
import com.soul.soul_community.repository.FeedBackRepository;
import com.soul.soul_community.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName FeedBackServiceImpl
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/5/1 16:52
 * @Version 1.0
 **/
@Service
public class FeedBackServiceImpl implements FeedBackService {

    @Autowired
    FeedBackRepository feedBackRepository;

    @Override
    public void saveFeedBack(FeedBack feedBack) {
        feedBackRepository.save(feedBack);
    }
}
