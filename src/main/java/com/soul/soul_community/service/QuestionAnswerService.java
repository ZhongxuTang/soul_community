package com.soul.soul_community.service;

import com.soul.soul_community.entity.QuestionAnswer;

import java.util.List;

public interface QuestionAnswerService {

    /**
     * 获取该id下的所有回复
     * @param questionId
     * @return
     */
    List<QuestionAnswer> getAnswerByQuestionId(Integer questionId);

    /**
     * 保存回复
     * @param questionAnswer
     */
    void saveAnswer(QuestionAnswer questionAnswer);

    /**
     * 获取该问题下的评论数量
     * @param questionId
     * @return
     */
    Integer getQuestionAnswerCountById(Integer questionId);

    /**
     * 删除该回答
     * @param questionAnswerId
     */
    void deleteQuestionAnswer(Integer questionAnswerId);
}
