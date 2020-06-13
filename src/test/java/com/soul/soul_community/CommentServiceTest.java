package com.soul.soul_community;

import com.soul.soul_community.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName CommentServiceTest
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/3/10 16:48
 * @Version 1.0
 **/
@SpringBootTest
public class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Test
    public void Test(){
        commentService.getCommentByUserId(1);
    }
}
