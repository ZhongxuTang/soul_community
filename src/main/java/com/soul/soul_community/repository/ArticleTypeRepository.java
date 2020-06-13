package com.soul.soul_community.repository;

import com.soul.soul_community.entity.ArticleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 资源类型Repository
 */
public interface ArticleTypeRepository extends JpaRepository<ArticleType,Integer> , JpaSpecificationExecutor<ArticleType> {

    /**
     * 根据文章类型查询id
     * @param typeName
     * @return
     */
    @Query(value = "select * from article_type where article_type_name=?1",nativeQuery = true)  //nativeQuery使用本地查询
    ArticleType findByTypeName(String typeName);

    @Query(value = "select * from article_type where article_type_id=?1",nativeQuery = true)
    ArticleType findArticleTypeById(Integer articleTypeId);

    @Query(value = "select article_type_name from article_type where article_type_id=?1",nativeQuery = true)
    String findArticleTypeNameById(Integer articleTypeId);

    @Query(value = "update article_type set count=?1 where article_type_id=?2",nativeQuery = true)
    @Modifying
    void updateArticleCountByOneType(Long articleCount,Integer articleTypeId);

    @Query(value = "update article_type set q_count=?1 where article_type_id=?2",nativeQuery = true)
    @Modifying
    void updateQuestionCountByOneType(Long articleCount,Integer articleTypeId);
}
