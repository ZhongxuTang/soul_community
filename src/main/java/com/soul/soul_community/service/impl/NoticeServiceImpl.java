package com.soul.soul_community.service.impl;

import com.soul.soul_community.entity.Notice;
import com.soul.soul_community.repository.NoticeRepository;
import com.soul.soul_community.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName NoticeServiceImpl
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/5/1 17:25
 * @Version 1.0
 **/
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeRepository noticeRepository;

    @Override
    public void saveNotice(Notice notice) {
        noticeRepository.save(notice);
    }

    @Override
    public Notice getNewNotice() {
        return noticeRepository.getNeNotice();
    }

    @Override
    public Long getNoticeCount() {
        return noticeRepository.count();
    }
}
