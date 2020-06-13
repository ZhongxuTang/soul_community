package com.soul.soul_community.service.impl;

import com.soul.soul_community.entity.QuestionAnswer;
import com.soul.soul_community.repository.QuestionAnswerRepository;
import com.soul.soul_community.service.QuestionAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName QuestionAnsowerServiceImpl
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/4/10 11:16
 * @Version 1.0
 **/
@Service
@Transactional
public class QuestionAnswerServiceImpl implements QuestionAnswerService {

    @Autowired
    QuestionAnswerRepository questionAnswerRepository;

    @Override
    public List<QuestionAnswer> getAnswerByQuestionId(Integer questionId) {
        return questionAnswerRepository.findByQuestionId(questionId);
    }

    @Override
    public void saveAnswer(QuestionAnswer questionAnswer) {
        questionAnswerRepository.save(questionAnswer);
    }

    @Override
    public Integer getQuestionAnswerCountById(Integer questionId) {
        return questionAnswerRepository.getQuestinAnswerCountById(questionId);
    }

    @Override
    public void deleteQuestionAnswer(Integer questionAnswerId) {
        questionAnswerRepository.deleteById(questionAnswerId);
    }


}
