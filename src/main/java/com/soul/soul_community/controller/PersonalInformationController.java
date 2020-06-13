package com.soul.soul_community.controller;

import com.soul.soul_community.entity.Article;
import com.soul.soul_community.entity.Follow;
import com.soul.soul_community.entity.Question;
import com.soul.soul_community.service.ArticleService;
import com.soul.soul_community.service.FollowService;
import com.soul.soul_community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @ClassName PersonalInformationController
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/3/29 14:18
 * @Version 1.0
 **/
@Controller
@RequestMapping("/personalInformation")
public class PersonalInformationController {

    @Autowired
    ArticleService articleService;

    @Autowired
    FollowService followService;

    @Autowired
    QuestionService questionService;

    @RequestMapping("/followUser")
    public ModelAndView followUser(@RequestParam Map<String,String> resMap){
        ModelAndView mv = new ModelAndView();
        List<Follow> follows = followService.getFollowByUserId(Integer.parseInt(resMap.get("userId")));
        mv.addObject("follows",follows);
        mv.setViewName("/personalInformation/followList");
        return mv;
    }

    @RequestMapping("/myQuestion")
    public ModelAndView myQuestion(@RequestParam Map<String,String> resMap){
        ModelAndView mv = new ModelAndView();
        List<Question> questions = questionService.getQuestionByUserId(Integer.parseInt(resMap.get("userId")));
        mv.addObject("questions",questions);
        mv.setViewName("/model/questionList");
        return mv;
    }

    @RequestMapping("/myArticle")
    public ModelAndView myArticle(@RequestParam Map<String,String> resMap){
        ModelAndView mv = new ModelAndView();
        List<Article> article = articleService.getArticlesByUserId(Integer.parseInt(resMap.get("userId")));
        mv.addObject("article",article);
        mv.setViewName("/model/articleList");
        return mv;
    }
}
