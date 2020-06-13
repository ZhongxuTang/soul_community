package com.soul.soul_community.repository;

import com.soul.soul_community.entity.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FeedBackRepository extends JpaRepository<FeedBack,Integer>, JpaSpecificationExecutor<FeedBack> {
}
