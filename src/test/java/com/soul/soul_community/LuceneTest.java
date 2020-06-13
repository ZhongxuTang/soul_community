package com.soul.soul_community;

import com.soul.soul_community.entity.Article;
import com.soul.soul_community.entity.Question;
import com.soul.soul_community.lucene.ArticleIndex;
import com.soul.soul_community.lucene.QuestionIndex;
import com.soul.soul_community.service.ArticleService;
import com.soul.soul_community.service.QuestionService;
import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName LuceneTest
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/4/14 22:16
 * @Version 1.0
 **/
@SpringBootTest
public class LuceneTest {

    @Autowired
    ArticleService articleService;

    @Autowired
    QuestionService questionService;

    @Autowired
    ArticleIndex articleIndex;

    @Autowired
    QuestionIndex questionIndex;


    @Test
    void luceneSeacrch() throws IOException, ParseException {

        List<Article> articleList = articleService.listAll(1,12);

        for (Article article:articleList) {
            articleIndex.addArticleIndex(article);
        }

        List<Article> articleLists = articleIndex.searchArticle("测试");
        System.out.println(articleLists.toString());
    }


    @Test
    void lunceneQuestion() throws IOException, ParseException {
        List<Question> questions = questionService.getQuestionByTime();
        for (Question q :questions
                ) {
            questionIndex.addQuestionIndex(q);
        }
        List<Question> questions1 = questionIndex.searchQuestion("测试");
        System.out.println(questions1.toString());
    }
}
