package com.soul.soul_community.service;

import com.soul.soul_community.entity.Article;
import com.soul.soul_community.entity.ArticleType;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 文章接口
 */
public interface ArticleService {

    /**
     * 根据分页查询条件查询资源信息列表
     * @param article              条件
     * @param userName          用户昵称
     * @param s_bcreateTextTime    文章发布开始时间
     * @param s_ecreateTextTime   文章发布结束时间
     * @param page              当前页
     * @param pageSize          每页记录数
     * @param direction         排序规则
     * @param properties        排序字段
     * @return
     */
    List<Article> articleList(String userName, String s_bcreateTextTime, String s_ecreateTextTime, Integer page, Integer pageSize, Sort.Direction direction, String ...properties);

    /**
     * 根据条件获取总记录数
     * @param article              条件
     * @param userName          用户昵称
     * @param s_bcreateTextTime    文章发布开始时间
     * @param s_ecreateTextTime   文章发布结束时间
     * @return
     */
    Long getCount(String userName, String s_bcreateTextTime, String s_ecreateTextTime);

    /**
     * 获取文章总数
     * @return
     */
    Long getArticleCount();
    /**
     * 增加或修改资源
     * @param article
     */
    void saveArticle(Article article);

    /**
     * 根据文章类型id统计文章数
     * @param articleId
     * @return
     */
    Long getArticleCountByArticleType(Integer articleTypeId);

    /**
     * 获取用户所属文章数
     * @param userId
     * @return
     */
    Integer getArticleCountByUserId(Integer userId);

    /**
     * 根据文章id获取文章类型id
     * @param articleId
     * @return
     */
    Integer getArticleTypeIdByArticleId(Integer articleId);

    /**
     * 根据id删除文章
     * @param id
     */
    void deleteArticle(Integer id);
    /**
     * 根据主键获取文章
     * @param id    文章id
     * @return
     */
    Article getArticleById(Integer id);

    /**
     * 根据条件查询文章信息（前台）
     * @param page      当前页
     * @param pageSize  每页记录数
     * @return
     */
    List<Article> listAll(Integer page, Integer pageSize);


    /**
     * 根据条件查询文章信息（前台）
     * @param type      文章类型
     * @param page      当前页
     * @param pageSize  每页记录数
     * @return
     */
    List<Article> OneTypeArticlelist(String type, Integer page, Integer pageSize);

    /**
     * 文章阅读数+1
     * @param textId     文章编号
     */
    void updateClick(Integer textId);

    /**
     * 更具用户id查询其所属的文章
     * @param id    用户id
     * @return
     */
    List<Article> getArticlesByUserId(Integer id);

    /**
     * 根据文章的阅读量推荐文章
     * @return
     */
    List<Article> getRecommentArticle();

    /**
     * 获取新发布的10篇文章
     * @return
     */
    List<Article> getNewArticle();

    /**
     * 更新文章
     */
    void updateArticleById(String articleName, Date date, String content, String contentMd, String summary, Integer articleId);

    /**
     * 更新文章评论数量
     * @param count
     * @param articleId
     */
    void updateCommentCount(Integer count,Integer articleId);

}
