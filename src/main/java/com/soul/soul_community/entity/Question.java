package com.soul.soul_community.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName Question
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/4/5 18:18
 * @Version 1.0
 **/
@Entity
@Table(name = "question")
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","fieldHandler"})
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;      //所属用户

    @ManyToOne
    @JoinColumn(name = "question_type_id")
    private ArticleType articleType;        //所属类型

    private String questionTitle;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String questionContent;

    private int questionAnswerQuantity = 0;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date questionPostDate;

    public Question(User user, ArticleType articleType, String questionTitle, String questionContent, Date questionPostDate) {
        this.user = user;
        this.articleType = articleType;
        this.questionTitle = questionTitle;
        this.questionContent = questionContent;
        this.questionPostDate = questionPostDate;
    }

    public Question(){

    }


}
