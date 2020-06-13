package com.soul.soul_community;

import com.soul.soul_community.controller.admin.AdminManagementController;
import com.soul.soul_community.entity.Article;
import com.soul.soul_community.entity.ArticleType;
import com.soul.soul_community.service.ArticleService;
import com.soul.soul_community.service.ArticleTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ArticleServiceTest
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/3/5 17:52
 * @Version 1.0
 **/
@SpringBootTest
public class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleTypeService articleTypeService;

    @Test
    public void articleTest(){
        List<Article> list = articleService.listAll(2,5);
        for (Article a: list
             ) {
            System.out.println(a.getArticleId());
        }
    }

    @Test
    public void articleCountTest(){
        System.out.println(articleService.getArticleCount());
    }

    @Test
    public void articleCountOneType(){
        Integer articleTypeId = articleService.getArticleTypeIdByArticleId(6);
        System.out.println(articleTypeId);
        Long articleCount = articleService.getArticleCountByArticleType(articleTypeId);
        System.out.println(articleCount);
        articleTypeService.updateArticleCountByOneType(articleCount,articleTypeId);
    }

    @Test
    public void articleTypeTest(){
        System.out.println(articleTypeService.getArticleTypeById(5));
    }


    @Test
    public void artcielCountByUser(){
        System.out.println(articleService.getArticleCountByUserId(6));
    }


    @Test
    void updateArticle(){

        //articleService.updateArticleById("测试新的逻辑",new Date(),"新的","新的","新的",6);
        ArticleType articleType = articleTypeService.getArticleTypeById(2);
        Article article = articleService.getArticleById(6);
        article.setArticleName("我日你妈");
        article.setArticleType(articleType);
        articleService.saveArticle(article);
    }

    @Test
    void deleteArticle(){

        articleService.deleteArticle(3);
    }
}
