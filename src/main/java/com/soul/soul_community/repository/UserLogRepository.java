package com.soul.soul_community.repository;

import com.soul.soul_community.entity.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserLogRepository extends JpaRepository<UserLog,Integer>, JpaSpecificationExecutor<UserLog> {

    @Query(value = "select * from user_log where user_id=?1",nativeQuery = true)
    List<UserLog> getUserLogByUserId(Integer userId);

    @Query(value = "select * from user_log order by execution_time desc limit 10",nativeQuery = true)
    List<UserLog> getUserLogsByTime();

}
