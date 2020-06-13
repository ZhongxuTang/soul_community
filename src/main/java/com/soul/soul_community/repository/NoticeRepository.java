package com.soul.soul_community.repository;

import com.soul.soul_community.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface NoticeRepository extends JpaRepository<Notice,Integer>, JpaSpecificationExecutor<Notice> {

    /**
     * 获取最新的一条通知
     * @return
     */
    @Query(value = "select * from notice order by notice_date desc limit 1",nativeQuery = true)
    Notice getNeNotice();
}
