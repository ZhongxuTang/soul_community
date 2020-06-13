package com.soul.soul_community.controller;

import com.alibaba.fastjson.JSONObject;
import com.soul.soul_community.entity.Article;
import com.soul.soul_community.entity.Question;
import com.soul.soul_community.entity.User;
import com.soul.soul_community.entity.UserLog;
import com.soul.soul_community.lucene.ArticleIndex;
import com.soul.soul_community.lucene.QuestionIndex;
import com.soul.soul_community.service.*;
import com.soul.soul_community.util.Consts;
import com.soul.soul_community.util.EncryptionUtil;
import com.soul.soul_community.util.StringUtil;
import com.soul.soul_community.util.UploadFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PersonalCenter
 * @Deacription 用户个人中心相关操作
 * @Author Lemonsoul
 * @Date 2020/2/25 20:45
 * @Version 1.0
 **/
@Slf4j
@Controller
@RequestMapping("/userCenter")
public class PersonalCenterController {

    ModelAndView mv = new ModelAndView();

    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleTypeService articleTypeService;

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @Autowired
    UserLogService userLogService;

    @Autowired
    ArticleIndex articleIndex;

    @Autowired
    QuestionIndex questionIndex;

    /**
     * 用户文章列表
     * @param session
     * @return
     */
    @RequestMapping("/userPost")
    public ModelAndView userPost(HttpSession session){
        User currentUser = (User) session.getAttribute(Consts.CURRENT_USER);
        List<Article> articleList = articleService.getArticlesByUserId(currentUser.getUserId());
        mv.addObject("articleList",articleList);
        mv.addObject("user",currentUser);
        mv.setViewName("/personalCenter/myPost");
        return mv;
    }

    /**
     * 删除文章
     * @param resMap
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteUserArticle")
    public Map<String,Object> deleUserArticle(@RequestParam Map<String,String> resMap){
        Map<String,Object> map = new HashMap<>();
        log.info("删除的文章id为"+resMap.get("articleId"));
        Article currentArticle = articleService.getArticleById(Integer.parseInt(resMap.get("articleId")));
        try {
            articleService.deleteArticle(Integer.parseInt(resMap.get("articleId")));
            Long count = articleService.getArticleCountByArticleType(currentArticle.getArticleType().getArticleTypeId());
            Integer userCount = articleService.getArticleCountByUserId(currentArticle.getUser().getUserId());
            log.info("当前用户发表了"+userCount+"篇文章");
            userService.updateUserArticleCount(userCount,currentArticle.getUser().getUserId());
            articleTypeService.updateArticleCountByOneType(count,currentArticle.getArticleType().getArticleTypeId());
            articleIndex.deleteIndex(resMap.get("articleId"));
            map.put("success",true);
        }catch (Exception e){
            map.put("success",false);
        }
        return map;
    }

    /**
     * 删除问题
     * @param resMap
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteUserQuestion")
    public Map<String,Object> deleteUserQuestion(@RequestParam Map<String,String> resMap){
        Map<String,Object> map = new HashMap<>();
        log.info("删除的问题id为"+resMap.get("questionId"));
        Question currentQuestion = questionService.getQuestionById(Integer.parseInt(resMap.get("questionId")));
        try {
            questionService.deleteQuestionById(Integer.parseInt(resMap.get("questionId")));
            Long count = questionService.getQuestionCountByArticleTypeId(currentQuestion.getArticleType().getArticleTypeId());
            Integer userCount = questionService.getQuestionCountByUserId(currentQuestion.getUser().getUserId());
            log.info("当前用户发表了"+userCount+"个问题");
            userService.updateUserQuestionCount(userCount,currentQuestion.getUser().getUserId());
            articleTypeService.updateQuestionCountByOneType(count,currentQuestion.getArticleType().getArticleTypeId());
            questionIndex.deleteIndex(resMap.get("questionId"));
            map.put("success",true);
        }catch (Exception e){
            map.put("success",false);
        }
        return map;
    }

    /**
     * 用户问答区域
     * @param session
     * @return
     */
    @RequestMapping("/personalQuestion")
    public ModelAndView personalQuestion(HttpSession session){
        User currentUser = (User) session.getAttribute(Consts.CURRENT_USER);
        List<Question> questions = questionService.getQuestionByUserId(currentUser.getUserId());
        mv.addObject("questions",questions);
        mv.addObject("user",currentUser);
        mv.setViewName("/personalCenter/personalQuestion");
        return mv;
    }


    /**
     * 用户个人信息
     * @return
     */
    @RequestMapping("/personalInformation")
    public ModelAndView personalInformation(){
        mv.setViewName("/personalCenter/personalInformation");
        return mv;
    }

    /**
     * 用户日志
     * @param session
     * @return
     */
    @RequestMapping("/userLog")
    public ModelAndView userLog(HttpSession session){
        User currentUser = (User) session.getAttribute(Consts.CURRENT_USER);
        List<UserLog> list = userLogService.getUserLogByUserId(currentUser.getUserId(),1,6);
        mv.addObject("log",list);
        mv.setViewName("/personalCenter/userLog");
        return mv;
    }

    /**
     * 修改用户个人信息
     * @param user
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/modifyPersonalInformation")
    public Map<String,Object> modifyPersonalInformation(User user,HttpSession session){
        Map<String,Object> map = new HashMap<>();
        User currentUser = (User)session.getAttribute(Consts.CURRENT_USER);
        if (!StringUtil.isEmpty(user.getUserName())){
            log.info("修改了用户名");
            currentUser.setUserName(user.getUserName());
        }
        if (!StringUtil.isEmpty(user.getUserPassword())){
            log.info("修改了密码");
            currentUser.setUserPassword(EncryptionUtil.md5(user.getUserPassword(),EncryptionUtil.SALT));
        }
        if (!StringUtil.isEmpty(user.getUserSex())){
            log.info("修改了性别");
            currentUser.setUserSex(user.getUserSex());
        }
        if (!StringUtil.isEmpty(user.getPersonalizedSignature())){
            log.info("修改了个新签名");
            currentUser.setPersonalizedSignature(user.getPersonalizedSignature());
        }
        userService.saveUser(currentUser);
        session.setAttribute(Consts.CURRENT_USER,userService.findByEmail(currentUser.getUserEmail()));
        map.put("success",true);
        return map;
    }

    @ResponseBody
    @RequestMapping("/uploadHeadPoint")
    public Map<String,Object> uploadfile(@RequestParam(value = "file", required = true) MultipartFile file, HttpServletRequest request, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        try {
            String filePath= "F:\\soul_community\\src\\main\\resources\\static\\img\\headPortrait";
            JSONObject json = UploadFileUtil.uploadImg(request,filePath,file);
            User currentUser = (User)session.getAttribute(Consts.CURRENT_USER);
            currentUser.setUserHeadPortrait("/img/headPortrait/"+json.get("fileName").toString());
            userService.saveUser(currentUser);
            session.setAttribute(Consts.CURRENT_USER,userService.findByEmail(currentUser.getUserEmail()));
            log.info(json.toString());
            // 图片上传后地址
            map.put("code", 0); ///图片地址和上传后的文件名
            // 图片上传的状态 1成功0失败
            map.put("msg", "上传成功");
        }catch (Exception e){

        }

        // 图片上传后地址
        map.put("code", 1); ///图片地址和上传后的文件名
        // 图片上传的状态 1成功0失败
        map.put("msg", "上传失败");

        return map;
    }

}
