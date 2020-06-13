package com.soul.soul_community.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName Admin
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/5/1 3:37
 * @Version 1.0
 **/
@Entity
@Data
@Table(name = "admin")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","fieldHandler"})
public class Admin {

    @Id
    //主键生成策略（自增长）
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;         //用户id

    @Column(length = 200)
    private String adminUsername;

    @Column(length = 200)
    private String adminPassword;

    @Column(length = 100)
    private String adminNickname;

}
