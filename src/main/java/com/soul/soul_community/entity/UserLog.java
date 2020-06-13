package com.soul.soul_community.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName UserLog
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/2/26 19:41
 * @Version 1.0
 **/
@Entity
@Data
@Table(name = "user_log")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","fieldHandler"})
public class UserLog {

    @Id
    //主键生成策略（自增长）
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logId;  //日志id

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  //用户id

    @Column(length = 200)
    private String behavior;    //行为

    @Column(length = 200)
    private String requestType; //请求类型

    @Column(length = 200)
    private String source;  //来源

    @Column(length = 200)
    private String requestAddress;  //请求地址

    @Column(length = 200)
    private String requestMethod;   //请求方法

    @Column(length = 200)
    private String requestParameter;    //请求参数

    @Column(length = 200)
    private String sessionId;  //sessionId

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date executionTime; //执行时间

    public UserLog() {
    }

    public UserLog(User user, String behavior, String requestType, String source, String requestAddress, String requestMethod, String requestParameter, String sessionId, Date executionTime) {
        this.user = user;
        this.behavior = behavior;
        this.requestType = requestType;
        this.source = source;
        this.requestAddress = requestAddress;
        this.requestMethod = requestMethod;
        this.requestParameter = requestParameter;
        this.sessionId = sessionId;
        this.executionTime = executionTime;
    }
}
