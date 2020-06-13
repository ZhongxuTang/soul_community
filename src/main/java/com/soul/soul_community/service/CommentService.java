package com.soul.soul_community.service;

import com.soul.soul_community.entity.Comment;

import java.util.List;

public interface CommentService {

    /**
     * 根据用户id查询所属评论
     * @param userId
     * @return
     */
    List<Comment> getCommentByUserId(Integer userId);

    /**
     * 根据文章id查询评论
     * @param articleId
     * @return
     */
    List<Comment> getCommentByArticleId(Integer articleId);

    /**
     * 发表评论
     * @param comment
     */
    void saveComment(Comment comment);


    Integer getCommentCountById(Integer articleId);

    /**
     * 获取最新的评论
     * @return
     */
    List<Comment> getNewComment();


    /**
     * 删除评论
     * @param commentId
     */
    void deleteComment(Integer commentId);
}
