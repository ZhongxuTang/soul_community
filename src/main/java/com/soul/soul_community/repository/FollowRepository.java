package com.soul.soul_community.repository;

import com.soul.soul_community.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow,Integer>, JpaSpecificationExecutor<Follow> {

    @Query(value = "select * from follow where current_user_id=?1 and followed_user_id=?2",nativeQuery = true)
    Follow getFollowStatus(Integer userId,Integer followUserId);

    @Query(value = "select * from follow where current_user_id=?1",nativeQuery = true)
    List<Follow> getFollowByUserId(Integer userId);
}
