package com.soul.soul_community.service.impl;

import com.soul.soul_community.entity.Question;
import com.soul.soul_community.repository.QuestionRepository;
import com.soul.soul_community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName QuestionServiceImpl
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/4/5 18:26
 * @Version 1.0
 **/
@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;


    @Override
    public List<Question> questionListAll(Integer page, Integer pageSize) {

        List<Question> list = new ArrayList<>();
        Specification<Question> specification = new Specification<Question>() {
            @Override
            public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicatesList = new ArrayList<>();

                return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
            }
        };

        //分页
        try{
            if (page == 0 && pageSize == 0){
                list = questionRepository.findAll(specification);
            }else {
                list = questionRepository.findAll
                        (specification, PageRequest.of(page-1,pageSize, Sort.by(Sort.Direction.ASC,"questionPostDate"))).getContent();
            }

        }catch (Exception e){
            System.out.println(e);
        }


        return list;
    }

    @Override
    public void saveQuestion(Question question) {

        questionRepository.save(question);
    }

    @Override
    public Integer getQuestionCountByUserId(Integer userId) {
        return questionRepository.getQuestionCountByUserId(userId);
    }

    @Override
    public List<Question> getQuestionByUserId(Integer userId) {
        return questionRepository.getQuestionsByUserId(userId);
    }

    @Override
    public Question getQuestionById(Integer questionId) {
        return questionRepository.getOne(questionId);
    }

    @Override
    public Long getQuestionCount() {
        return questionRepository.count();
    }

    @Override
    public List<Question> getQuestionByTime() {
        return questionRepository.getQuestionsByTime();
    }

    @Override
    public void deleteQuestionById(Integer questionId) {
        questionRepository.deleteById(questionId);
    }

    @Override
    public Long getQuestionCountByArticleTypeId(Integer questionTypeId) {
        return questionRepository.getQuestionCountByArticleTypeId(questionTypeId);
    }

    @Override
    public void updateQuestionAnswerCountByQuestionId(Integer count, Integer questionId) {
        questionRepository.updateQuestionAnswerCount(count,questionId);
    }
}
