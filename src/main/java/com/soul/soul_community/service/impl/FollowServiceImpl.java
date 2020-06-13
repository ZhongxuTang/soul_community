package com.soul.soul_community.service.impl;

import com.soul.soul_community.entity.Follow;
import com.soul.soul_community.repository.FollowRepository;
import com.soul.soul_community.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName FollowServiceImpl
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/3/28 15:49
 * @Version 1.0
 **/
@Service
@Transactional
public class FollowServiceImpl implements FollowService {

    @Autowired
    FollowRepository followRepository;

    @Override
    public void saveFollow(Follow follow) {
        followRepository.save(follow);
    }

    @Override
    public Follow getFollowStatus(Integer userId, Integer followedUserId) {
        return followRepository.getFollowStatus(userId,followedUserId);
    }

    @Override
    public List<Follow> getFollowByUserId(Integer userId) {
        System.out.println(followRepository.getFollowByUserId(userId));
        return followRepository.getFollowByUserId(userId);
    }


}
