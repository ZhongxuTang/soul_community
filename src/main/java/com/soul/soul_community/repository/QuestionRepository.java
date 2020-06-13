package com.soul.soul_community.repository;

import com.soul.soul_community.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Integer>, JpaSpecificationExecutor<Question> {


    @Query(value = "select count(*) from question where user_id=?1",nativeQuery = true)
    Integer getQuestionCountByUserId(Integer userId);

    @Query(value = "select * from question where user_id=?1 order by question_post_date desc",nativeQuery = true)
    List<Question> getQuestionsByUserId(Integer id);

    @Query(value = "select * from question order by question_post_date desc",nativeQuery = true)
    List<Question> getQuestionsByTime();

    @Query(value = "select count(*) from question where question_type_id=?1",nativeQuery = true)
    Long getQuestionCountByArticleTypeId(Integer questionTypeId);

    @Modifying
    @Query(value = "update question set question_answer_quantity=?1 where question_id=?2",nativeQuery = true)
    void updateQuestionAnswerCount(Integer count,Integer articleId);
}
