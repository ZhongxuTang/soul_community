package com.soul.soul_community.controller;

import com.soul.soul_community.entity.Article;
import com.soul.soul_community.entity.ArticleType;
import com.soul.soul_community.entity.Notice;
import com.soul.soul_community.entity.Question;
import com.soul.soul_community.service.ArticleService;
import com.soul.soul_community.service.ArticleTypeService;
import com.soul.soul_community.service.NoticeService;
import com.soul.soul_community.service.QuestionService;
import com.soul.soul_community.util.AddressUtil;
import com.soul.soul_community.util.Consts;
import com.soul.soul_community.util.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName IndexController
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/1/25 23:04
 * @Version 1.0
 **/
@Slf4j
@Controller
public class IndexController {

    ModelAndView mv = new ModelAndView();

    @Autowired
    ArticleService articleService;

    @Autowired
    QuestionService questionService;

    @Autowired
    ArticleTypeService articleTypeService;

    @Autowired
    NoticeService noticeService;

    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request){
        mv.setViewName("/index");
        List<Article> article = articleService.listAll(1,6);
        List<ArticleType> articleType = articleTypeService.listAll();
        Long pageCount = MathUtil.pageCount(articleService.getArticleCount());
        List<Article> recommendArticle = articleService.getRecommentArticle();
        Notice notice = noticeService.getNewNotice();
        mv.addObject("article",article);
        mv.addObject("recommendArticles",recommendArticle);
        mv.addObject("articleType",articleType);
        mv.addObject("pageNum",1);
        mv.addObject("pageCount",pageCount);
        mv.addObject("notice",notice);
        log.info(AddressUtil.getIpAddress(request));
        return mv;
    }

    @RequestMapping("/articleAll")
    public ModelAndView ArticleMenu(){
        List<Article> article = articleService.listAll(1,6);
        Long pageCount = MathUtil.pageCount(articleService.getArticleCount());
        mv.addObject("article",article);
        mv.addObject("pageCount",pageCount);
        mv.setViewName("/model/articleList.ftl");
        return mv;
    }

    @RequestMapping("/question")
    public ModelAndView selectQuestion(){
        List<ArticleType> articleType = articleTypeService.listAll();
        List<Question> questions = questionService.questionListAll(1,6);
        Long pageCount = MathUtil.pageCount(questionService.getQuestionCount());
        mv.addObject("questions",questions);
        mv.addObject("articleType",articleType);
        mv.addObject("pageNum",1);
        mv.addObject("pageCount",pageCount);
        log.info("页面数"+pageCount.toString());
        mv.addObject("class","所有问答");
        mv.setViewName("/question");
        return mv;
    }

    @RequestMapping("/edit_md")
    public ModelAndView openEditMd(HttpSession session){
        if (session.getAttribute(Consts.CURRENT_USER) == null){
            mv.setViewName("/");
        }else {
            mv.setViewName("/edit_md");
        }
        return mv;
    }

/*
    @RequestMapping(value = {"","/"})
    public ModelAndView index(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();

        User user = (User) request.getSession().getAttribute("user");
        if (ObjectUtils.isEmpty(user)){
            mv.setViewName("redirect:/login");
        }else{
            mv.setViewName("index");
            mv.addObject(user);
            System.out.println(user.getUsername());
        }

        return mv;

    }*/
}
