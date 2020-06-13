package com.soul.soul_community.repository;

import com.soul.soul_community.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer>, JpaSpecificationExecutor<Comment> {

    /**
     * 根据用户id查找评论
     * @param userId
     * @return
     */
    @Query(value = "select * from comment where user_id=?1",nativeQuery = true)
    List<Comment> findByUserId(Integer userId);

    /**
     * 根据文章id查询评论
     * @param articleId
     * @return
     */
    @Query(value = "select * from comment where article_id=?1",nativeQuery = true)
    List<Comment> findByArticleId(Integer articleId);

    @Query(value = "select count(*) from comment where article_id=?1",nativeQuery = true)
    Integer getCommentCountById(Integer articleId);

    @Query(value = "select * from comment order by comment_date desc limit 10",nativeQuery = true)
    List<Comment> getCommentsByTime();
}
