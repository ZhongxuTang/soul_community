package com.soul.soul_community.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName User
 * @Deacription 用户实体类
 * @Author Lemonsoul
 * @Date 2020/2/11 17:59
 * @Version 1.0
 **/
@Entity
@Data
@Table(name = "user")
//忽略类中不存在的字段，java转json hibernate懒加载造成无线递归的问题
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","fieldHandler"})
public class User implements Serializable {

    @Id
    //主键生成策略（自增长）
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;         //用户id

    @Column(length = 200)
    private String userName;        //用户昵称

    @NotEmpty(message = "密码不能为空")
    @Column(length = 100)
    private String userPassword;    //用户密码

    @Email(message = "邮箱格式有误")
    @NotEmpty(message = "邮箱不能为空")
    @Column(length = 100)
    private String userEmail;       //用户邮箱

    @Column(length = 100)
    private String userHeadPortrait = "/img/headPortrait/user01.png";    //用户头像

    @Column(length = 50)
    private String userSex = "男";         //用户性别

    private Integer userPoints;      //用户积分

    private Integer userGrade = 0;    //用户等级

    private Integer userExperience = 0;   //用户经验

    private boolean seal = false;   //是否被封禁

    private String userRoleName="会员";         //角色，是否为管理员之类的

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date userRegisterDate;       //注册时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date userLatelyLoginTime;    //最近一次登录时间

    private String personalizedSignature;   //个性签名

    private Integer followQuantity = 0;

    private Integer followByOthers = 0;

    private Integer articleQuantity = 0;

    private Integer questionQuantity = 0;   //该用户发布的问题数量

    private Integer answerQuantityToUser = 0;   //回答该用户的用户数量

    private Integer answerQuantity = 0; //该用户的回答数量

    /**
     * 注解表示不是数据库字段
     */
    @Transient
    private Integer messageCount;










}
