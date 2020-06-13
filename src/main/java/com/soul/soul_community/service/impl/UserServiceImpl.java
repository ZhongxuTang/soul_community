package com.soul.soul_community.service.impl;

import com.soul.soul_community.entity.User;
import com.soul.soul_community.repository.UserRepository;
import com.soul.soul_community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/2/13 20:39
 * @Version 1.0
 **/
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUserId(Integer userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUserExperience(Integer userId,Integer experience) {

        Integer userExperience = userRepository.updateExperience(userId,experience);


        if (userExperience >= 700){
            userRepository.updateGrade(userId);
            userRepository.updateExperience(userId,0);
        }

    }

    @Override
    public Long getUserCount() {
        return userRepository.count();
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.getAllUser();
    }

    @Override
    public void updateUserArticleCount(Integer articleCount,Integer userId) {
        userRepository.updateArticleCountByUser(articleCount,userId);
    }

    @Override
    public void updateUserQuestionCount(Integer questionCount,Integer userId) {
        userRepository.updateQuestionCountByUser(questionCount,userId);
    }

}
