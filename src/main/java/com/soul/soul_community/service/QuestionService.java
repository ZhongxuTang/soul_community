package com.soul.soul_community.service;

import com.soul.soul_community.entity.Question;

import java.util.List;

public interface QuestionService {

    /**
     * 获取所有问题（分页）
     * @param page  当前页
     * @param pageSize  每页记录数
     * @return
     */
    List<Question> questionListAll(Integer page, Integer pageSize);

    /**
     * 保存新的提问
     */
    void saveQuestion(Question question);

    /**
     * 根据用户id获取用户的问题数量
     * @param userId
     * @return
     */
    Integer getQuestionCountByUserId(Integer userId);

    /**
     * 更加用户的id获取该用户所有问题
     * @param userId
     * @return
     */
    List<Question> getQuestionByUserId(Integer userId);

    /**
     * 根据questionId获取问题
     * @param questionId
     * @return
     */
    Question getQuestionById(Integer questionId);

    /**
     * 获取所有问题数量
     * @return
     */
    Long getQuestionCount();

    /**
     * 获取所有问题
     * @return
     */
    List<Question> getQuestionByTime();

    /**
     * 根据id删除问题
     * @param questionId
     */
    void deleteQuestionById(Integer questionId);

    /**
     * 根据id获取问题类型
     * @param questionTypeId
     * @return
     */
    Long getQuestionCountByArticleTypeId(Integer questionTypeId);

    /**
     * 更新回复数量
     * @param count
     * @param questionId
     */
    void updateQuestionAnswerCountByQuestionId(Integer count,Integer questionId);
}
