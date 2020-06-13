package com.soul.soul_community.service;

import com.soul.soul_community.entity.ArticleType;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * 文章类型接口
 */
public interface ArticleTypeService {

    /**
     * 分页查询文章类型列表
     * @param page          当前页
     * @param pageSize      每页记录数
     * @param direction     排序规则
     * @param properties    排序字段
     * @return
     */
    List<ArticleType> list(Integer page, Integer pageSize, Sort.Direction direction, String ...properties);

    /**
     * 查询资源类型列表
     * @param direction
     * @param properties
     * @return
     */
    List<ArticleType> listAll();

    /**
     * 获取总记录数
     * @return
     */
    Long getCount();

    /**
     * 添加或修改资源类型
     * @param articleType
     */
    void save(ArticleType articleType);

    /**
     * 根据id删除文章类型
     * @param id
     */
    void deleteTextTye(Integer id);

    /**
     * 根据id查询文章类型
     * @param id
     * @return
     */
    ArticleType getArticleTypeById(Integer id);

    /**
     * 根据类型id查询类型名字
     * @param id
     * @return
     */
    String getArticleTypeNameById(Integer id);
    /**
     * 根据文章类型查询id
     * @param textTypeName
     * @return
     */
    ArticleType getArticleTypeByName(String textTypeName);

    /**
     * 更新一种类型文章的数量
     * @param articleCount  文章数量
     * @param articleTypeId 文章类型id
     */
    void updateArticleCountByOneType(Long articleCount,Integer articleTypeId);

    /**
     * 更新一种类型问题的数量
     * @param questionCount  文章数量
     * @param articleTypeId 文章类型id
     */
    void updateQuestionCountByOneType(Long questionCount,Integer articleTypeId);

}
