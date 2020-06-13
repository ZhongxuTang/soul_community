package com.soul.soul_community.controller;

import com.soul.soul_community.entity.Article;
import com.soul.soul_community.entity.ArticleType;
import com.soul.soul_community.entity.Question;
import com.soul.soul_community.lucene.ArticleIndex;
import com.soul.soul_community.lucene.QuestionIndex;
import com.soul.soul_community.service.ArticleService;
import com.soul.soul_community.service.ArticleTypeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SearchController
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/4/15 9:41
 * @Version 1.0
 **/
@Slf4j
@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    ArticleIndex articleIndex;

    @Autowired
    ArticleTypeService articleTypeService;

    @Autowired
    ArticleService articleService;

    @Autowired
    QuestionIndex questionIndex;

    @RequestMapping("/article")
    public ModelAndView searchArticle(@RequestParam Map<String,String> resMap) throws IOException, ParseException {
        ModelAndView mv = new ModelAndView();
        log.info(resMap.toString());
        List<Article> article = articleIndex.searchArticle(resMap.get("searchContext"));
        List<ArticleType> articleType = articleTypeService.listAll();
        List<Article> recommendArticle = articleService.getRecommentArticle();

        log.info("结果正常");
        mv.addObject("article",article);
        mv.addObject("articleType",articleType);
        mv.addObject("recommendArticles",recommendArticle);
        mv.addObject("pageNum",1);
        mv.addObject("pageCount",1);
        mv.addObject("thisType","搜索结果");
        mv.setViewName("/model/searchRes");
        return mv;
    }

    @RequestMapping("/question")
    public ModelAndView searchQuestion(@RequestParam Map<String,String> resMap) throws IOException, ParseException{
        ModelAndView mv = new ModelAndView();

        List<Question> questions = questionIndex.searchQuestion(resMap.get("searchContext"));
        List<ArticleType> articleType = articleTypeService.listAll();
        mv.addObject("questions",questions);
        mv.addObject("articleType",articleType);
        mv.addObject("pageNum",1);
        mv.addObject("pageCount",1);
        mv.addObject("thisType","搜索结果");
        mv.setViewName("/model/searchResQuestion");
        return mv;
    }
}
