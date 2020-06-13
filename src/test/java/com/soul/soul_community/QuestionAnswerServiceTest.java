package com.soul.soul_community;

import com.soul.soul_community.service.QuestionAnswerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName QuestionAnswerServiceTest
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/4/10 11:24
 * @Version 1.0
 **/
@SpringBootTest
public class QuestionAnswerServiceTest {

    @Autowired
    QuestionAnswerService questionAnswerService;

    @Test
    public void getAnswer(){
        System.out.println(questionAnswerService.getAnswerByQuestionId(1).toString());;
    }
}
