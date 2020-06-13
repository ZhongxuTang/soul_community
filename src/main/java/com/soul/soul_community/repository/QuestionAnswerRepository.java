package com.soul.soul_community.repository;

import com.soul.soul_community.entity.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer,Integer>, JpaSpecificationExecutor<QuestionAnswer> {


    /**
     * 根据问题id查询评论
     * @param questionId
     * @return
     */
    @Query(value = "select * from question_answer where question_id=?1",nativeQuery = true)
    List<QuestionAnswer> findByQuestionId(Integer questionId);

    @Query(value = "select count(*) from question_answer where question_id=?1",nativeQuery = true)
    Integer getQuestinAnswerCountById(Integer questionId);
}
