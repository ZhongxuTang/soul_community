package com.soul.soul_community.controller.admin;

import com.soul.soul_community.entity.*;
import com.soul.soul_community.lucene.ArticleIndex;
import com.soul.soul_community.lucene.QuestionIndex;
import com.soul.soul_community.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AdminManagementUserController
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/4/23 14:31
 * @Version 1.0
 **/
@Controller
@RequestMapping("/admin/manage")
public class AdminManagementController {

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @Autowired
    QuestionService questionService;

    @Autowired
    CommentService commentService;

    @Autowired
    ArticleTypeService articleTypeService;

    @Autowired
    ArticleIndex articleIndex;

    @Autowired
    QuestionIndex questionIndex;

    @Autowired
    QuestionAnswerService questionAnswerService;

    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/sealUser")
    public ModelAndView sealUser(@RequestParam Map<String,String> resMap){
        return getModelAndView(resMap, true);
    }

    @RequestMapping("/relieveSealUser")
    public ModelAndView relieveSealUser(@RequestParam Map<String,String> resMap){
        return getModelAndView(resMap, false);
    }

    /**
     * 用户封禁的方法封装
     * @param resMap
     * @param b
     * @return
     */
    private ModelAndView getModelAndView(@RequestParam Map<String, String> resMap, boolean b) {
        ModelAndView mv = new ModelAndView();
        logger.info(resMap.toString());
        User currentUser = userService.findByUserId(Integer.parseInt(resMap.get("userId")));
        currentUser.setSeal(b);
        userService.saveUser(currentUser);
        List<User> users = userService.getAllUser();

        mv.addObject("users", users);
        mv.setViewName("/admin/adminUser");
        return mv;
    }

    /**
     * 文章通过审核
     * @param resMap
     * @return
     */
    @RequestMapping("/agreeArticle")
    public ModelAndView agreeArticle(@RequestParam Map<String,String> resMap){
        ModelAndView mv = new ModelAndView();

        Article thisArticle = articleService.getArticleById(Integer.parseInt(resMap.get("articleId")));
        thisArticle.setState(2);
        articleService.saveArticle(thisArticle);
        List<Article> article = articleService.getNewArticle();

        mv.addObject("article",article);

        mv.setViewName("/admin/adminArticle");
        return mv;
    }

    @RequestMapping("/deleteArticle")
    public ModelAndView deleteArticle(@RequestParam Map<String,String> resMap){
        ModelAndView mv = new ModelAndView();

        try{
            Article currentArticle = articleService.getArticleById(Integer.parseInt(resMap.get("articleId")));
            articleService.deleteArticle(Integer.parseInt(resMap.get("articleId")));
            Long articleCount = articleService.getArticleCountByArticleType(currentArticle.getArticleType().getArticleTypeId());
            Integer userCount = articleService.getArticleCountByUserId(currentArticle.getUser().getUserId());
            articleTypeService.updateArticleCountByOneType(articleCount,currentArticle.getArticleType().getArticleTypeId());
            userService.updateUserArticleCount(userCount,currentArticle.getUser().getUserId());
            List<Article> article = articleService.getNewArticle();
            mv.addObject("article",article);
        }catch (Exception e){
            logger.error(e.toString());
        }finally {

            mv.setViewName("/admin/adminArticle");
        }

        return mv;
    }

    @RequestMapping("/deleteQuestion")
    public ModelAndView deleteQuestion(@RequestParam Map<String,String> resMap){
        ModelAndView mv = new ModelAndView();
        try{
            Question currentQuestion = questionService.getQuestionById(Integer.parseInt(resMap.get("questionId")));
            questionService.deleteQuestionById(Integer.parseInt(resMap.get("questionId")));
            Long questionCount = questionService.getQuestionCountByArticleTypeId(currentQuestion.getArticleType().getArticleTypeId());
            Integer userCount = questionService.getQuestionCountByUserId(currentQuestion.getUser().getUserId());
            articleTypeService.updateQuestionCountByOneType(questionCount,currentQuestion.getArticleType().getArticleTypeId());
            userService.updateUserQuestionCount(userCount,currentQuestion.getUser().getUserId());
            List<Question> question = questionService.getQuestionByTime();

            mv.addObject("question",question);
        }catch (Exception e){
            logger.error(e.toString());
        }finally {

            mv.setViewName("/admin/adminQuestion");
        }
        return mv;
    }


    /**
     * 评论管理
     */
    @RequestMapping("/managerComment")
    public ModelAndView managerComment(@RequestParam Map<String,String> resMap){
        ModelAndView mv = new ModelAndView();
        logger.info(resMap.get("mess"));
        if (resMap.get("mess").equals("article")){

            List<Comment> comment = commentService.getCommentByArticleId(Integer.parseInt(resMap.get("Id")));
            mv.addObject("comment",comment);
            //1表示文章
            mv.addObject("mess","article");
            logger.info("文章中的评论管理");

        }else if (resMap.get("mess").equals("question")){
            List<QuestionAnswer> answer = questionAnswerService.getAnswerByQuestionId(Integer.parseInt(resMap.get("Id")));
            mv.addObject("answer",answer);
            //2表示问答
            mv.addObject("mess","question");
            logger.info("问答中的评论管理");
        }else {
            mv.addObject("mess",3);
            logger.info("不是文章中的也不是问答中的");
        }
        mv.setViewName("/admin/commentManager");
        return mv;
    }

    @ResponseBody
    @RequestMapping("/deleteComment")
    public ModelAndView deleteComment(@RequestParam Map<String,String> resMap){
        ModelAndView mv = new ModelAndView();
        try {
            logger.info(resMap.toString());
            commentService.deleteComment(Integer.parseInt(resMap.get("commentId")));
            Integer count = commentService.getCommentCountById(Integer.parseInt(resMap.get("articleId")));
            articleService.updateCommentCount(count,Integer.parseInt(resMap.get("articleId")));
        }catch (Exception e){
            logger.error(e.toString());
        }finally {
            List<Article> article = articleService.getNewArticle();
            mv.addObject("article",article);
            mv.setViewName("/admin/adminArticle");
            }
        return mv;
    }

    @ResponseBody
    @RequestMapping("/deleteAnswer")
    public ModelAndView deleteAnswer(@RequestParam Map<String,String> resMap){
        ModelAndView mv = new ModelAndView();
        try {
            logger.info(resMap.toString());
            questionAnswerService.deleteQuestionAnswer(Integer.parseInt(resMap.get("answerId")));
            Integer count = questionAnswerService.getQuestionAnswerCountById(Integer.parseInt(resMap.get("questionId")));
            questionService.updateQuestionAnswerCountByQuestionId(count,Integer.parseInt(resMap.get("questionId")));
        }catch (Exception e){
            logger.error(e.toString());
        }finally {
            List<Question> question = questionService.getQuestionByTime();
            mv.addObject("question",question);
            mv.setViewName("/admin/adminQuestion");
        }
        return mv;
    }
}
