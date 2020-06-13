package com.soul.soul_community.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @ClassName MailServiceImpl
 * @Deacription 邮件工具类
 * @Author Lemonsoul
 * @Date 2020/2/11 12:55
 * @Version 1.0
 **/
@Component
public class MailUtils {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    private SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

    /**
     * 文本邮件
     * @param to        接受者邮箱
     * @param subject   邮件主题
     * @param contnet   邮件内容
     */
    public boolean sendTextMail(String to, String subject, String contnet) {
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(contnet);
        simpleMailMessage.setFrom(from);
        try {
            mailSender.send(simpleMailMessage);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
