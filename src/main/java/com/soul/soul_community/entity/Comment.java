package com.soul.soul_community.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName Comment
 * @Deacription 评论实类
 * @Author Lemonsoul
 * @Date 2020/3/10 16:23
 * @Version 1.0
 **/
@Entity
@Table(name = "comment")
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","fieldHandler"})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date commentDate;

    @Column(length = 1000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    private Integer status;

    private Integer lastId; //上一条评论的id

    public Comment() {
    }

    public Comment(Date commentDate, String content, User user, Article article) {
        this.commentDate = commentDate;
        this.content = content;
        this.user = user;
        this.article = article;
    }
}
