package com.soul.soul_community.repository;

import com.soul.soul_community.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Integer>, JpaSpecificationExecutor<Article> {


    /**
     * 文章阅读数+1
     * @param articleId     文章编号
     */
    @Query(value = "update article set read_quantity=read_quantity+1 where article_id=?1",nativeQuery = true)
    @Modifying
    void updateClick(Integer articleId);

    @Query(value = "select * from article where user_id=?1 order by article_create_time desc",nativeQuery = true)
    List<Article> getArticlesByUserId(Integer id);

    @Query(value = "select count(*) from article where article_type_id=?1",nativeQuery = true)
    Long getArticleCountByArticleTypeId(Integer articleTypeId);

    @Query(value = "select count(*) from article where user_id=?1",nativeQuery = true)
    Integer getArticlesCountByUserId(Integer userId);

    @Query(value = "select article_type_id from article where article_id=?1",nativeQuery = true)
    Integer getArticleTypeIdByArticleId(Integer articleId);

    @Query(value = "select * from article order by read_quantity desc limit 10",nativeQuery = true)
    List<Article> getRecommentArticle();

    @Query(value = "select * from article order by article_create_time desc",nativeQuery = true)
    List<Article> getArticlesByTime();

    @Query(value = "select count(*) from article where state=2",nativeQuery = true)
    Long getEffectiveArticleCount();

    @Modifying
    @Query(value = "update article set article_name=?1,article_create_time=?2,content=?3,content_md=?4,summary=?5 where article_id=?6",nativeQuery = true)
    void updateArticle(String articleName, Date date,String content,String contentMd,String summary,Integer articleId);

    @Modifying(clearAutomatically = true)
    @Query(value = "update article set user_comment_quantity=?1 where article_id=?2",nativeQuery = true)
    void updateCommentCount(Integer count,Integer articleId);

}
