package com.soul.soul_community;

import com.soul.soul_community.entity.User;
import com.soul.soul_community.entity.UserLog;
import com.soul.soul_community.repository.UserRepository;
import com.soul.soul_community.service.ArticleService;
import com.soul.soul_community.service.UserLogService;
import com.soul.soul_community.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ClassName UserTest
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/4/20 20:16
 * @Version 1.0
 **/
@SpringBootTest
public class UserTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserLogService userLogService;

    @Autowired
    ArticleService articleService;

    @Test
    public void userTest(){
        userService.updateUserExperience(1,10);
    }

    @Test
    void gradeTest(){
        //System.out.println(userService.getUserCount());
        User user = userService.findByUserId(7);
        user.setSeal(true);
        userService.saveUser(user);
    }

    @Test
    void userLog(){
        List<UserLog> userLogList = userLogService.getUserLogByUserId(1,1,6);
        for (UserLog userLog:userLogList
             ) {
            System.out.println("当前用户"+userLog.getUser().toString());
        }
    }

    @Test
    void userArticleCount(){
        userService.updateUserArticleCount(articleService.getArticleCountByUserId(1),1);
    }
}
