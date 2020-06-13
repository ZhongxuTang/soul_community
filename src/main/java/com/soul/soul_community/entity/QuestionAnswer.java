package com.soul.soul_community.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName QuestionAnswer
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/4/10 11:11
 * @Version 1.0
 **/
@Entity
@Table(name = "question_answer")
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","fieldHandler"})
public class QuestionAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answerId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date answerDate;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public QuestionAnswer() {
    }

    public QuestionAnswer(Date answerDate, String content, User user, Question question) {
        this.answerDate = answerDate;
        this.content = content;
        this.user = user;
        this.question = question;
    }
}
