package com.soul.soul_community.service.impl;

import com.soul.soul_community.entity.Article;
import com.soul.soul_community.repository.ArticleRepository;
import com.soul.soul_community.service.ArticleService;
import com.soul.soul_community.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * @ClassName TextServiceImpl
 * @Deacription 文章实现类
 * @Author Lemonsoul
 * @Date 2020/2/17 18:13
 * @Version 1.0
 **/
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public List<Article> articleList(String userName, String s_bcreateTextTime, String s_ecreateTextTime, Integer page, Integer pageSize, Sort.Direction direction, String... properties) {
        Page<Article> textPage = articleRepository.findAll(new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {





                return getPredicate(root, criteriaBuilder, s_bcreateTextTime, s_ecreateTextTime, userName);
            }
        },PageRequest.of(page-1,pageSize,direction,properties));

        return textPage.getContent();
    }

    @Override
    public Long getCount(String userName, String s_bcreateTextTime, String s_ecreateTextTime) {
        Long count = articleRepository.count(new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return getPredicate(root, criteriaBuilder, s_bcreateTextTime, s_ecreateTextTime, userName);
            }
        });
        return null;
    }

    @Override
    public Long getArticleCount() {
        return articleRepository.getEffectiveArticleCount();
    }

    /**
     * 查询条件
     * @param root
     * @param criteriaBuilder
     * @param s_bcreateTextTime
     * @param s_ecreateTextTime
     * @param userName
     * @param article
     * @return
     */
    private Predicate getPredicate(Root<Article> root, CriteriaBuilder criteriaBuilder, String s_bcreateTextTime, String s_ecreateTextTime, String userName) {
        Predicate predicate = criteriaBuilder.conjunction();
        if (StringUtil.isNotEmpty(s_bcreateTextTime)) {
            predicate.getExpressions().add(criteriaBuilder.greaterThanOrEqualTo(root.get("articleCreateTime").as(String.class), s_bcreateTextTime));
        }
        if (StringUtil.isNotEmpty(s_ecreateTextTime)) {
            predicate.getExpressions().add(criteriaBuilder.greaterThanOrEqualTo(root.get("articleCreateTime").as(String.class), s_ecreateTextTime));
        }
        if (StringUtil.isNotEmpty(userName)) {
            predicate.getExpressions().add(criteriaBuilder.like(root.get("user").get("userName"), "%" + userName + "%"));
        }
        /*if (article != null) {
            if (StringUtil.isNotEmpty(article.getArticleName())) {
                predicate.getExpressions().add(criteriaBuilder.like(root.get("articleName"), "%" + article.getArticleName() + "%"));
            }
            if (article.getArticleType() != null && article.getArticleType().getArticleTypeId() != null) {
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("articleType").get("articleTypeId"), article.getArticleType().getArticleTypeId()));
            }
            if (article.getUser() != null && article.getUser().getUserId() != null) {
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("user").get("userId"), article.getUser().getUserId()));
            }
        }*/

        return predicate;
    }

    /**
     * 保存文章
     * @param article
     */
    @Override
    public void saveArticle(Article article) {
        articleRepository.save(article);
    }

    @Override
    public Long getArticleCountByArticleType(Integer articleTypeId) {
        return articleRepository.getArticleCountByArticleTypeId(articleTypeId);
    }

    @Override
    public Integer getArticleCountByUserId(Integer userId) {
        return articleRepository.getArticlesCountByUserId(userId);
    }

    @Override
    public Integer getArticleTypeIdByArticleId(Integer articleId) {
        return articleRepository.getArticleTypeIdByArticleId(articleId);
    }

    /**
     * 根据id删除文章
     * @param id
     */
    @Override
    public void deleteArticle(Integer id) {
        articleRepository.deleteById(id);
    }

    /**
     * 根据id查询文章
     * @param id    文章id
     * @return
     */
    @Override
    public Article getArticleById(Integer id) {
        Article article = articleRepository.getOne(id);
        return article;
    }

    /**
     * 文章列表
     * @param page      当前页
     * @param pageSize  每页记录数
     * @return
     */
    public List<Article> listAll(Integer page, Integer pageSize){

        List<Article> list = new ArrayList<>();
        Specification<Article> specification = new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicatesList = new ArrayList<>();

                predicatesList.add(criteriaBuilder.equal(root.get("state"),2));

                return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
            }
        };

        //分页
        try{
            if (page == 0 && pageSize == 0){
                list = articleRepository.findAll(specification);
            }else {
                list = articleRepository.findAll
                        (specification,PageRequest.of(page-1,pageSize,Sort.by(Sort.Direction.DESC,"articleCreateTime"))).getContent();
            }

        }catch (Exception e){
            System.out.println(e);
        }


        return list;
    }

    /**
     * 一个类型文章的查询
     * @param type      文章类型
     * @param page      当前页
     * @param pageSize  每页记录数
     * @return
     */
    @Override
    public List<Article> OneTypeArticlelist(String type, Integer page, Integer pageSize) {
        Map <String,Object> map = new HashMap<>();

        List<Article> list = new ArrayList<>();
        Specification<Article> specification = new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicatesList = new ArrayList<>();
                if (StringUtil.isNotEmpty(type)){
                        predicatesList.add(criteriaBuilder.equal(root.get("articleType").get("articleTypeId"),type));
                }

                return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
            }
        };
        //list = articleRepository.findAll(specification);

        //分页
        try{
            if (page == 0 && pageSize == 0){
                list = articleRepository.findAll(specification);
            }else {
                list = articleRepository.findAll
                        (specification,PageRequest.of(page-1,pageSize,Sort.by(Sort.Direction.DESC,"articleCreateTime"))).getContent();
            }

        }catch (Exception e){

        }
        return list;
    }

    @Override
    public void updateClick(Integer textId) {
        articleRepository.updateClick(textId);
    }

    @Override
    public List<Article> getArticlesByUserId(Integer id) {
        return articleRepository.getArticlesByUserId(id);
    }

    @Override
    public List<Article> getRecommentArticle() {
        return articleRepository.getRecommentArticle();
    }

    @Override
    public List<Article> getNewArticle() {
        return articleRepository.getArticlesByTime();
    }

    @Override
    public void updateArticleById(String articleName, Date date, String content, String contentMd, String summary, Integer articleId) {
        articleRepository.updateArticle(articleName,date,content,contentMd,summary,articleId);
    }

    @Override
    public void updateCommentCount(Integer count, Integer articleId) {
        articleRepository.updateCommentCount(count,articleId);
    }
}
