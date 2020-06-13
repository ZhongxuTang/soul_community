package com.soul.soul_community.repository;

import com.soul.soul_community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName UserRepository
 * @Deacription 用户Repository接口
 * @Author Lemonsoul
 * @Date 2020/2/13 20:15
 * @Version 1.0
 **/

public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {

    /**
     * 根据邮箱查找用户实体
     * @param email
     * @return
     */
    @Query(value = "select * from user where user_email=?1",nativeQuery = true)  //nativeQuery使用本地查询
    User findByEmail(String email);

    /**
     * 根据用户id来查询用户实体
     * @param userId
     * @return
     */
    @Query(value = "select * from user where user_id=?1",nativeQuery = true)
    User findByUserId(Integer userId);

    @Transactional
    @Query(value = "update user set user_experience=user_experience+?2 where user_id=?1",nativeQuery = true)
    @Modifying
    Integer updateExperience(Integer userId,Integer experience);

    @Query(value = "select user_grade from user where user_id=?1",nativeQuery = true)
    Integer getUserGrade(Integer userId);

    @Transactional
    @Query(value = "update user set user_grade=user_grade+1 where user_id=?1",nativeQuery = true)
    @Modifying
    void updateGrade(Integer userId);

    @Query(value = "select * from user order by follow_by_others desc",nativeQuery = true)
    List<User> getAllUser();

    @Transactional
    @Query(value = "update user set article_quantity=?1 where user_id=?2",nativeQuery = true)
    @Modifying
    void updateArticleCountByUser(Integer articleCount,Integer userId);

    @Transactional
    @Query(value = "update user set question_quantity=?1 where user_id=?2",nativeQuery = true)
    @Modifying
    void updateQuestionCountByUser(Integer questionCount,Integer userId);
}
