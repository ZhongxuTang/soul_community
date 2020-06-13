package com.soul.soul_community.service.impl;

import com.soul.soul_community.entity.UserLog;
import com.soul.soul_community.repository.UserLogRepository;
import com.soul.soul_community.service.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserLogServiceImpl
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/2/26 19:56
 * @Version 1.0
 **/
@Service
public class UserLogServiceImpl implements UserLogService {

    @Autowired
    UserLogRepository userLogRepository;

    @Override
    public List<UserLog> getUserLogByUserId(Integer userId,Integer page, Integer pageSize) {



        List<UserLog> list = new ArrayList<>();
        Specification<UserLog> specification = new Specification<UserLog>() {
            @Override
            public Predicate toPredicate(Root<UserLog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicatesList = new ArrayList<>();

                predicatesList.add(criteriaBuilder.equal(root.get("user").get("userId"),userId));

                return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
            }
        };

        //分页
        try{
            if (page == 0 && pageSize == 0){
                list = userLogRepository.findAll(specification);
            }else {
                list = userLogRepository.findAll
                        (specification, PageRequest.of(page-1,pageSize, Sort.by(Sort.Direction.ASC,"executionTime"))).getContent();
            }

        }catch (Exception e){
            System.out.println(e);
        }


        return list;

        //return userLogRepository.getUserLogByUserId(userId);
    }

    @Override
    public void saveUserLog(UserLog userLog) {
        userLogRepository.save(userLog);
    }

    @Override
    public List<UserLog> getAllUserLog() {
        return userLogRepository.getUserLogsByTime();
    }
}
