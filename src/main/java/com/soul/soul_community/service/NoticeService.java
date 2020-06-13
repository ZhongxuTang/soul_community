package com.soul.soul_community.service;

import com.soul.soul_community.entity.Notice;

/**
 * @ClassName NoticeService
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/5/1 17:24
 * @Version 1.0
 **/

public interface NoticeService {

    /**
     * 发布一条通知
     * @param notice
     */
    void saveNotice(Notice notice);

    /**
     * 获取最新的一条通知
     * @return
     */
    Notice getNewNotice();

    /**
     * 获取公告数量
     * @return
     */
    Long getNoticeCount();
}
