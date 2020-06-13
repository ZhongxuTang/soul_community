package com.soul.soul_community;

import com.soul.soul_community.entity.FeedBack;
import com.soul.soul_community.service.FeedBackService;
import com.soul.soul_community.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @ClassName FeedBackTest
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/5/1 16:54
 * @Version 1.0
 **/
@SpringBootTest
public class FeedBackTest {

    @Autowired
    UserService userService;

    @Autowired
    FeedBackService feedBackService;

    @Test
    void feedbackT(){

        FeedBack feedBack = new FeedBack("1123714881@qq.com","测试反馈",new Date(),userService.findByUserId(1));
        feedBackService.saveFeedBack(feedBack);

    }
}
