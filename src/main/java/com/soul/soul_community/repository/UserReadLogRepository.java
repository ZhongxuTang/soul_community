package com.soul.soul_community.repository;

import com.soul.soul_community.entity.UserReadLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserReadLogRepository extends JpaRepository<UserReadLog,Integer>, JpaSpecificationExecutor<UserReadLog> {

    @Query(value = "select * from user_read_log where article_id=?1 and user_ip=?2",nativeQuery = true)
    UserReadLog getUserReadLogByArticleId(Integer articleId,String userIP);
}
