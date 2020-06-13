package com.soul.soul_community.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName UserReadLog
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/4/13 22:09
 * @Version 1.0
 **/
@Entity
@Data
@Table(name = "user_read_log")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","fieldHandler"})
public class UserReadLog {

    @Id
    //主键生成策略（自增长）
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer readId;  //日志id

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;  //用户id

    private String userIp;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date userReadDate;

    private Integer userId;

}
