package com.soul.soul_community.controller;

import com.soul.soul_community.entity.*;
import com.soul.soul_community.service.*;
import com.soul.soul_community.util.Consts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CommentController
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/3/10 16:52
 * @Version 1.0
 **/
@Slf4j
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;

    @Autowired
    QuestionAnswerService questionAnswerService;

    @Autowired
    QuestionService questionService;

    @ResponseBody
    @RequestMapping("/publishComment")
    public Map<String,Object> publishComment(HttpSession session, @RequestParam Map<String,String> resMap){
        if((Boolean) comment(session,resMap).get("success")){
            Comment comment = new Comment(new Date(),resMap.get("content"),(User) session.getAttribute(Consts.CURRENT_USER),
                    articleService.getArticleById(Integer.parseInt(resMap.get("articleId"))));
            commentService.saveComment(comment);
            Article article = articleService.getArticleById(Integer.parseInt(resMap.get("articleId")));
            article.setUserCommentQuantity(commentService.getCommentCountById(Integer.parseInt(resMap.get("articleId"))));
            userService.updateUserExperience(((User) session.getAttribute(Consts.CURRENT_USER)).getUserId(),5);
            return comment(session,resMap);
        }else {
            return comment(session,resMap);
        }
    }

    @ResponseBody
    @RequestMapping("/questionAnswer")
    public Map<String,Object> questionAnswer(HttpSession session, @RequestParam Map<String,String> resMap){
        if((Boolean) comment(session,resMap).get("success")){
            log.info(resMap.get("questionId"));
            QuestionAnswer questionAnswer = new QuestionAnswer(new Date(),resMap.get("content"),(User) session.getAttribute(Consts.CURRENT_USER),
                    questionService.getQuestionById(Integer.parseInt(resMap.get("questionId"))));
            log.info(questionAnswer.toString());
            questionAnswerService.saveAnswer(questionAnswer);
            Question question = questionService.getQuestionById(Integer.parseInt(resMap.get("questionId")));
            question.setQuestionAnswerQuantity(questionAnswerService.getQuestionAnswerCountById(Integer.parseInt(resMap.get("questionId"))));
            questionService.saveQuestion(question);
            userService.updateUserExperience(((User) session.getAttribute(Consts.CURRENT_USER)).getUserId(),5);
            return comment(session,resMap);
        }else {
            return comment(session,resMap);
        }
    }


    /**
     * 评论方法封装
     * @param session
     * @param resMap
     * @return
     */
    public Map<String,Object> comment(HttpSession session,Map<String,String> resMap){

        Map<String,Object> map = new HashMap<>();
        if (session.getAttribute(Consts.CURRENT_USER) == null){
            map.put("success",false);
            map.put("errorInfo","请先登录");
        }else {
            map.put("success",true);
            log.info(map.toString());

        }
        return map;
    }
}
