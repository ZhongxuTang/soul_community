package com.soul.soul_community.service.impl;

import com.soul.soul_community.entity.Comment;
import com.soul.soul_community.repository.CommentRepository;
import com.soul.soul_community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CommentServiceImpl
 * @Deacription 评论的service实现类
 * @Author Lemonsoul
 * @Date 2020/3/10 16:45
 * @Version 1.0
 **/
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<Comment> getCommentByUserId(Integer userId) {
        return commentRepository.findByUserId(userId);
    }

    @Override
    public List<Comment> getCommentByArticleId(Integer articleId) {
        return commentRepository.findByArticleId(articleId);
    }

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Integer getCommentCountById(Integer articleId) {
        return commentRepository.getCommentCountById(articleId);
    }

    @Override
    public List<Comment> getNewComment() {
        return commentRepository.getCommentsByTime();
    }

    @Override
    public void deleteComment(Integer commentId) {
        commentRepository.deleteById(commentId);
    }


}
