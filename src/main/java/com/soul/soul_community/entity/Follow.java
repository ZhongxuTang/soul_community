package com.soul.soul_community.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName Follow
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/3/28 15:34
 * @Version 1.0
 **/
@Entity
@Data
@Table(name = "follow")
//忽略类中不存在的字段，java转json hibernate懒加载造成无线递归的问题
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","fieldHandler"})
public class Follow {

    @Id
    //主键生成策略（自增长）
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer followId;

    private Integer currentUserId;    //关注者的id

    @ManyToOne
    @JoinColumn(name="followed_user_id")
    private User followedUserId;     //被关注者id

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date followTime;

    private Integer status = 1;     //关注的状态，1位正在关注，2位取消关注

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cancelFollowTime;      //取消关注时间
}
