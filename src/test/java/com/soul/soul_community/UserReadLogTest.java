package com.soul.soul_community;

import com.soul.soul_community.service.UserReadLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName UserReadLogTest
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/4/14 20:51
 * @Version 1.0
 **/
@SpringBootTest
public class UserReadLogTest {

    @Autowired
    UserReadLogService userReadLogService;

    @Test
    void ipTest(){
        System.out.println(userReadLogService.theFirstRead(1,"192.168.48.1"));
    }
}
