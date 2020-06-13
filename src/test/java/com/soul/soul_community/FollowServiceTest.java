package com.soul.soul_community;

import com.soul.soul_community.entity.Follow;
import com.soul.soul_community.entity.User;
import com.soul.soul_community.service.FollowService;
import com.soul.soul_community.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @ClassName FollowServiceTest
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/3/28 15:52
 * @Version 1.0
 **/
@SpringBootTest
public class FollowServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    FollowService followService;

    @Test
    public void followTest(){
        Follow follow = new Follow();
        User user = new User();
        user.setUserId(1);
        follow.setCurrentUserId(user.getUserId());
        follow.setFollowedUserId(userService.findByUserId(3));
        follow.setFollowTime(new Date());
        followService.saveFollow(follow);
    }

    @Test
    public void getFollowStatus(){
        Follow follow = followService.getFollowStatus(1,8);
        if (follow == null){
            System.out.println("结果为null");
        }
        System.out.println(follow);
    }
}
