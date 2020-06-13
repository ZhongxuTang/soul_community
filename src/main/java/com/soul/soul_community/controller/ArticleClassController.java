package com.soul.soul_community.controller;

import com.soul.soul_community.entity.Article;
import com.soul.soul_community.entity.ArticleType;
import com.soul.soul_community.service.ArticleService;
import com.soul.soul_community.service.ArticleTypeService;
import com.soul.soul_community.util.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @ClassName ArticleClassController
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/3/18 14:16
 * @Version 1.0
 **/
@Slf4j
@Controller
@RequestMapping("/articleClass")
public class ArticleClassController {


    ModelAndView mv = new ModelAndView();

    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleTypeService articleTypeService;

    @RequestMapping("/oneClassArticle/{articleTypeId}")
    public ModelAndView oneClassArticle(@PathVariable(value = "articleTypeId",required = false) String articleTypeId){

        List<Article> article = articleService.OneTypeArticlelist(articleTypeId,1,6);
        List<ArticleType> articleType = articleTypeService.listAll();
        Long pageCount = MathUtil.pageCount(articleService.getArticleCountByArticleType(Integer.parseInt(articleTypeId)));
        String articleTypeName = articleTypeService.getArticleTypeNameById(Integer.parseInt(articleTypeId));
        log.info("当前类别页数"+pageCount.toString());
        mv.addObject("article",article);
        mv.addObject("articleType",articleType);
        mv.addObject("pageNum",1);
        mv.addObject("pageCount",pageCount);
        mv.addObject("thisType",articleTypeName);
        log.info(articleTypeId);
        log.info("测试"+articleTypeName);
        mv.setViewName("/oneClassArticle");
        return mv;
    }
}
