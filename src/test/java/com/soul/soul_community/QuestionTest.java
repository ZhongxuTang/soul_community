package com.soul.soul_community;

import com.soul.soul_community.entity.Question;
import com.soul.soul_community.service.ArticleTypeService;
import com.soul.soul_community.service.QuestionService;
import com.soul.soul_community.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @ClassName QuestionTest
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/4/5 18:29
 * @Version 1.0
 **/
@SpringBootTest
public class QuestionTest {

    @Autowired
    QuestionService questionService;

    @Autowired
    UserService userService;

    @Autowired
    ArticleTypeService articleTypeService;

    @Test
    public void getAllQuestion(){
        System.out.println(questionService.questionListAll(1,2).toString());
    }

    @Test
    public void saveQuestion(){
        Question question = new Question();
        question.setUser(userService.findByUserId(1));
        question.setArticleType(articleTypeService.getArticleTypeById(1));
        question.setQuestionTitle("我提问一下");
        question.setQuestionContent("我的提问结束了........");
        question.setQuestionPostDate(new Date());
        questionService.saveQuestion(question);
    }

    @Test
    public void getQuestionCount(){
        Integer count = questionService.getQuestionCountByUserId(2);
        System.out.println(count);
    }

    @Test
    public void getQuestionCountByType(){
        Long count = questionService.getQuestionCountByArticleTypeId(1);
        articleTypeService.updateQuestionCountByOneType(count,1);
    }
}
