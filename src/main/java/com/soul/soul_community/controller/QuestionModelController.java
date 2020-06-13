package com.soul.soul_community.controller;

import com.soul.soul_community.entity.Question;
import com.soul.soul_community.entity.User;
import com.soul.soul_community.lucene.QuestionIndex;
import com.soul.soul_community.service.ArticleTypeService;
import com.soul.soul_community.service.QuestionService;
import com.soul.soul_community.service.UserService;
import com.soul.soul_community.util.Consts;
import com.soul.soul_community.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName QuestionModelController
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/4/5 17:58
 * @Version 1.0
 **/
@Slf4j
@Controller
@RequestMapping("/questionModel")
public class QuestionModelController {

    @Autowired
    ArticleTypeService articleTypeService;

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionIndex questionIndex;

    @Autowired
    UserService userService;


    /**
     * 发布问题
     * @param session
     * @param resMap
     * @return
     */
    @ResponseBody
    @RequestMapping("/postQuestion")
    public Map<String,Object> postQuestion(HttpSession session, @RequestParam Map<String, String> resMap){
        Map<String,Object> map = new HashMap<>();
        if (session.getAttribute(Consts.CURRENT_USER) == null){
            map.put("success",false);
            map.put("errorInfo","请先登录");
        }else {
            if (StringUtil.isEmpty(resMap.get("quertionTitle"))){
                map.put("success",false);
                map.put("errorInfo","标题不能为空");
            }else if (StringUtil.isEmpty(resMap.get("articleLabelId"))){
                map.put("success",false);
                map.put("errorInfo","请选择一个标签");
            }else if (StringUtil.isEmpty(resMap.get("quertionContent"))){
                map.put("success",false);
                map.put("errorInfo","问题不能为空");
            }else {
                User user = (User) session.getAttribute(Consts.CURRENT_USER);
                Question question = new Question(user,articleTypeService.getArticleTypeById(Integer.parseInt(resMap.get("articleLabelId"))),
                        resMap.get("quertionTitle"),resMap.get("quertionContent"),new Date());
                questionService.saveQuestion(question);

                Integer questionCount = questionService.getQuestionCountByUserId(user.getUserId());
                user.setQuestionQuantity(questionCount);

                map.put("success",true);
                map.put("errorInfo","问题发布成功");
                Long count = questionService.getQuestionCountByArticleTypeId(Integer.parseInt(resMap.get("articleLabelId")));
                articleTypeService.updateQuestionCountByOneType(count,Integer.parseInt(resMap.get("articleLabelId")));
                userService.updateUserQuestionCount(questionService.getQuestionCountByUserId(user.getUserId()),user.getUserId());
                questionIndex.addQuestionIndex(question);
            }
            log.info(map.toString());
        }
        return map;
    }

}
