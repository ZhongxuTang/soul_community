package com.soul.soul_community.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @ClassName ArticleType
 * @Deacription 文章类型的实体类
 * @Author Lemonsoul
 * @Date 2020/2/17 15:17
 * @Version 1.0
 **/
@Entity
@Table(name="article_type")
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","fieldHandler"})
public class ArticleType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer articleTypeId;

    @NotEmpty(message = "文章类型不能为空")
    @Column(length = 200)
    private String articleTypeName;

    @Column(length = 1000)
    private String remark;

    private Integer count = 0;

    private Integer qCount = 0;

}
