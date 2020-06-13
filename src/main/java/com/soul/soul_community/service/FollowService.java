package com.soul.soul_community.service;

import com.soul.soul_community.entity.Follow;

import java.util.List;

public interface FollowService {

    /**
     * 增加关注
     * @param follow
     */
    void saveFollow(Follow follow);

    /**
     * 根据用户id判断是否已关注此用户
     * @param userId
     * @return
     */
    Follow getFollowStatus(Integer userId,Integer followedUserId);

    /**
     * 获取当前用户关注的用户
     * @param userId
     * @return
     */
    List<Follow> getFollowByUserId(Integer userId);
}
