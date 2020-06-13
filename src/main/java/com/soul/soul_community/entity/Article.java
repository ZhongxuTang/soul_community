package com.soul.soul_community.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName Article
 * @Deacription 文章实体类
 * @Author Lemonsoul
 * @Date 2020/2/17 17:30
 * @Version 1.0
 **/
@Entity
@Table(name = "article")
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","fieldHandler"})
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer articleId;

    @Column(length = 200)
    private String articleName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date articleCreateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;      //所属用户

    @ManyToOne
    @JoinColumn(name = "article_type_id")
    private ArticleType articleType;        //所属文章类型

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String contentMd;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String summary;

    private Integer state = 1;

    private String reason;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkDate;

    private Integer readQuantity = 0;

    private Integer userCommentQuantity = 0;

    public Article() {
    }

    public Article(String articleName, Date articleCreateTime, User user, ArticleType articleType, String content, String contentMd, String summary) {
        this.articleName = articleName;
        this.articleCreateTime = articleCreateTime;
        this.user = user;
        this.articleType = articleType;
        this.content = content;
        this.contentMd = contentMd;
        this.summary = summary;
    }

    public Article(String articleName, Date articleCreateTime, User user, ArticleType articleType, String content, String contentMd, String summary, Integer state) {
        this.articleName = articleName;
        this.articleCreateTime = articleCreateTime;
        this.user = user;
        this.articleType = articleType;
        this.content = content;
        this.contentMd = contentMd;
        this.summary = summary;
        this.state = state;
    }
}
