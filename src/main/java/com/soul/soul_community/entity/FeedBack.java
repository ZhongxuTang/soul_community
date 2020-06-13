package com.soul.soul_community.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName FeedBack
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/5/1 16:45
 * @Version 1.0
 **/
@Entity
@Data
@Table(name = "feedback")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","fieldHandler"})
public class FeedBack {

    @Id
    //主键生成策略（自增长）
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feedbackId;         //用户id

    @Column(length = 100)
    private String feedbackEmail;

    private String feedbackContext;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date feedbackDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public FeedBack() {
    }

    public FeedBack(String feedbackEmail, String feedbackContext, Date feedbackDate, User user) {
        this.feedbackEmail = feedbackEmail;
        this.feedbackContext = feedbackContext;
        this.feedbackDate = feedbackDate;
        this.user = user;
    }
}
