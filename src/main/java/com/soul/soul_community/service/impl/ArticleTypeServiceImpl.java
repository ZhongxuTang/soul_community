package com.soul.soul_community.service.impl;

import com.soul.soul_community.entity.ArticleType;
import com.soul.soul_community.repository.ArticleTypeRepository;
import com.soul.soul_community.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName TextTypeServiceImpl
 * @Deacription 文章类型实现类
 * @Author Lemonsoul
 * @Date 2020/2/17 15:47
 * @Version 1.0
 **/
@Service
@Transactional
public class ArticleTypeServiceImpl implements ArticleTypeService {

    @Autowired
    ArticleTypeRepository articleTypeRepository;

    @Override
    public List<ArticleType> list(Integer page, Integer pageSize, Sort.Direction direction, String... properties) {
        return null;
    }

    @Override
    public List<ArticleType> listAll() {
        return articleTypeRepository.findAll();
    }

    @Override
    public Long getCount() {
        return null;
    }

    @Override
    public void save(ArticleType articleType) {

    }

    @Override
    public void deleteTextTye(Integer id) {

    }

    @Override
    public ArticleType getArticleTypeById(Integer id) {
        return articleTypeRepository.findArticleTypeById(id);
    }

    @Override
    public String getArticleTypeNameById(Integer id) {
        return articleTypeRepository.findArticleTypeNameById(id);
    }


    @Override
    public ArticleType getArticleTypeByName(String textTypeName) {
        return articleTypeRepository.findByTypeName(textTypeName);
    }

    @Override
    public void updateArticleCountByOneType(Long articleCount,Integer articleTypeId) {
        articleTypeRepository.updateArticleCountByOneType(articleCount,articleTypeId);
    }

    @Override
    public void updateQuestionCountByOneType(Long questionCount, Integer articleTypeId) {
        articleTypeRepository.updateQuestionCountByOneType(questionCount,articleTypeId);
    }


}
