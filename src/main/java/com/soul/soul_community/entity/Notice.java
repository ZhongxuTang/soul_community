package com.soul.soul_community.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName Notice
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/5/1 17:18
 * @Version 1.0
 **/
@Entity
@Data
@Table(name = "notice")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","fieldHandler"})
public class Notice {

    @Id
    //主键生成策略（自增长）
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noticeId;

    @Column(length = 100)
    private String noticeContext;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date noticeDate;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    public Notice() {
    }

    public Notice(String noticeContext, Date noticeDate, Admin admin) {
        this.noticeContext = noticeContext;
        this.noticeDate = noticeDate;
        this.admin = admin;
    }
}
