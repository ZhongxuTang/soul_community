package com.soul.soul_community.controller;

import com.alibaba.fastjson.JSONObject;
import com.soul.soul_community.entity.*;
import com.soul.soul_community.lucene.ArticleIndex;
import com.soul.soul_community.service.*;
import com.soul.soul_community.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PageController
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/2/17 14:05
 * @Version 1.0
 **/
@Slf4j
@Controller
@RequestMapping("/page")
public class PageController {

    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleTypeService textTypeService;

    @Autowired
    ArticleTypeService articleTypeService;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @Autowired
    UserLogService userLogService;

    @Autowired
    FollowService followService;

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionAnswerService questionAnswerService;

    @Autowired
    UserReadLogService userReadLogService;

    @Autowired
    ArticleIndex articleIndex;

    @Autowired
    NoticeService noticeService;

    /**
     * 打开编辑器ifream
     * @param session
     * @return
     *//*
    @ResponseBody
    @RequestMapping("/newText")
    public Map<String,Object> newText(HttpSession session){
        Map<String,Object> map = new HashMap<>();
        if (session.getAttribute(Consts.CURRENT_USER) == null){
            map.put("success",false);
            map.put("errorInfo","请先登录");
        }else {

            map.put("success",true);
        }

        return map;
    }*/


    /**
     * 保存新的文章
     * @param session
     * @param resMap
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveArticle")
        public Map<String,Object> saveArticle(HttpSession session, @RequestParam Map<String,String> resMap,HttpServletRequest request,String articleId){
            Map<String,Object> map = new HashMap<>();
            User user = (User) session.getAttribute(Consts.CURRENT_USER);

            if (StringUtil.isEmpty(resMap.get("title"))){
                map.put("success",false);
                map.put("errorInfo","请输入文章标题");
            }else if (StringUtil.isEmpty(resMap.get("content"))){
                map.put("success",false);
                map.put("errorInfo","不能发布空文章");
            }else {
                if (articleId.equals("null") || articleId == null){
                    log.info(articleId);
                    log.info(resMap.get("articleLabelId"));
                    ArticleType articleType =  articleTypeService.getArticleTypeById(Integer.parseInt(resMap.get("articleLabelId")));
                    log.info(articleType.toString());
                    String summary = StringUtil.formatHTML(resMap.get("content"));
                    Article article = new Article(resMap.get("title"),new Date(),user, articleType,resMap.get("content"),resMap.get("contentMd"),summary);
                    articleService.saveArticle(article);
                    UserLog userLog = new UserLog
                            ((User) session.getAttribute(Consts.CURRENT_USER),"新增文章",request.getMethod(),"安徽蚌埠",request.getRequestURL().toString(),request.getServletPath(),request.toString(),request.getSession().getId(),new Date());
                    userLogService.saveUserLog(userLog);
                    Integer articleCount = articleService.getArticleCountByUserId(user.getUserId());
                    user.setArticleQuantity(articleCount);
                    Long Count = articleService.getArticleCountByArticleType(Integer.parseInt(resMap.get("articleLabelId")));
                    articleTypeService.updateArticleCountByOneType(Count,Integer.parseInt(resMap.get("articleLabelId")));
                    userService.updateUserArticleCount(articleService.getArticleCountByUserId(user.getUserId()),user.getUserId());
                    //添加索引
                    articleIndex.addArticleIndex(article);
                    userService.updateUserExperience(user.getUserId(),10);
                }else{
                    log.info(articleId);
                    Article article = articleService.getArticleById(Integer.parseInt(articleId));
                    log.info(article.toString());
                    article.setArticleName(resMap.get("title"));
                    article.setArticleType(articleTypeService.getArticleTypeById(Integer.parseInt(resMap.get("articleLabelId"))));
                    article.setContentMd(resMap.get("contentMd"));
                    article.setContent(resMap.get("content"));
                    article.setState(1);
                    log.info("修改后"+article.toString());
                    articleService.saveArticle(article);
                    UserLog userLog = new UserLog
                            ((User) session.getAttribute(Consts.CURRENT_USER),"重新编辑文章",request.getMethod(),"安徽蚌埠",request.getRequestURL().toString(),request.getServletPath(),request.toString(),request.getSession().getId(),new Date());
                    userLogService.saveUserLog(userLog);
                    articleIndex.updateArticleIndex(article);
                }

                map.put("success",true);
            }
            return map;
    }

    /**
     * 保存草稿
     * @param session
     * @param request
     * @param resMap
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveDraft")
    public Map<String,Object> saveDraft(HttpSession session,HttpServletRequest request, @RequestParam Map<String,String> resMap){
        log.info(resMap.toString());
        Map<String,Object> map = new HashMap<>();
        User user = (User) session.getAttribute(Consts.CURRENT_USER);
        if (user == null){
            map.put("success",false);
            map.put("errorInfo","请先登录");
            return map;
        }else {
            log.info(resMap.toString());
            ArticleType articleType =  articleTypeService.getArticleTypeById(Integer.parseInt(resMap.get("articleLabelId")));
            log.info(articleType.toString());
            String summary = StringUtil.formatHTML(resMap.get("content"));
            Article article = new Article(resMap.get("title"),new Date(),user, articleType,resMap.get("content"),resMap.get("contentMd"),summary,3);
            articleService.saveArticle(article);
            UserLog userLog = new UserLog
                    ((User) session.getAttribute(Consts.CURRENT_USER),"新增草稿",request.getMethod(),"安徽蚌埠",request.getRequestURL().toString(),request.getServletPath(),request.toString(),request.getSession().getId(),new Date());
            userLogService.saveUserLog(userLog);
            Integer articleCount = articleService.getArticleCountByUserId(user.getUserId());
            user.setArticleQuantity(articleCount);
            Long Count = articleService.getArticleCountByArticleType(Integer.parseInt(resMap.get("articleLabelId")));
            articleTypeService.updateArticleCountByOneType(Count,Integer.parseInt(resMap.get("articleLabelId")));
            userService.updateUserArticleCount(articleService.getArticleCountByUserId(user.getUserId()),user.getUserId());
            map.put("success",true);
        }


        return map;
    }


    //图片上传
    @ResponseBody
    @RequestMapping("/uploadImg")
    public JSONObject uploadImg(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file, HttpServletRequest request, HttpServletResponse response){

        String filePath= "F:\\soul_community\\src\\main\\resources\\static\\img\\userArticleImg";
        JSONObject json = UploadFileUtil.uploadImg(request,filePath,file);
        log.info(json.toString());
        return json;
    }

    /**
     * 打开Markerdown
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/editMd")
    public Map<String,Object> editMd(HttpSession session, Model model){
        Map<String,Object> map = new HashMap<>();
        if (session.getAttribute(Consts.CURRENT_USER) == null){
            map.put("success",false);
            map.put("errorInfo","请先登录");
        }else {
            map.put("success",true);
            List<ArticleType> articleType = articleTypeService.listAll();
            model.addAttribute("articleType",articleType);
        }
        return map;
    }

    /**
     * 返回文章编辑页面
     * @param articleId
     * @return
     */
    @RequestMapping("/editArticle/{articleId}")
    public ModelAndView editArticle(@PathVariable(value = "articleId",required = false) String articleId){
        ModelAndView mv = new ModelAndView();
        Article article = articleService.getArticleById(Integer.parseInt(articleId));
        List<ArticleType> articleType = articleTypeService.listAll();
        mv.addObject("article",article);
        mv.addObject("articleType",articleType);
        mv.setViewName("/edit_md");
        return mv;
    }

    /**
     * 查看文章
     * @param textId
     * @return
     */
    @RequestMapping("/article/{articleId}")
    public ModelAndView articleDetail(@PathVariable(value = "articleId",required = false) String textId,HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        String replace = textId.replace(".ftl","");
        articleService.updateClick(Integer.parseInt(replace));
        Article article = articleService.getArticleById(Integer.parseInt(replace));
        if (userReadLogService.theFirstRead(Integer.parseInt(replace),AddressUtil.getIpAddress(request))){
            articleService.updateClick(Integer.parseInt(replace));
        }
        /*if (article.getState() != 2){
            return null;
        }*/

        List<Comment> comment = commentService.getCommentByArticleId(Integer.parseInt(replace));
        List<Article> recommendArticle = articleService.getRecommentArticle();
        mv.addObject("article", article);
        mv.addObject("comment",comment);
        mv.addObject("recommendArticles",recommendArticle);
        mv.setViewName("/article");
        return mv;
    }

    /**
     * 问题详情页
     * @param questionId
     * @return
     */
    @RequestMapping("/question/{questionId}")
    public ModelAndView questionDetail(@PathVariable(value = "questionId",required = false) String questionId){
        log.info(questionId);
        ModelAndView mv = new ModelAndView();
        log.info(questionId);
        Question question = questionService.getQuestionById(Integer.parseInt(questionId));
        log.info(question.toString());
        List<QuestionAnswer> answers = questionAnswerService.getAnswerByQuestionId(Integer.parseInt(questionId));

        mv.addObject("question",question);
        mv.addObject("answers",answers);
        mv.setViewName("/questionModel/questionDetail");

        return mv;
    }


    /**
     * 分页
     * @param pageNum
     * @return
     */
    @RequestMapping("/{pageNum}")
    public ModelAndView myPaging(@PathVariable(value = "pageNum",required = false) Integer pageNum,String type,HttpSession session){
        log.info("需要分页的是"+type);
        ModelAndView mv = new ModelAndView();
        if (type.equals("article")){
            List<Article> article = articleService.listAll(pageNum,6);
            Long pageCount = MathUtil.pageCount(articleService.getArticleCount());
            List<ArticleType> articleType = articleTypeService.listAll();
            List<Article> recommendArticle = articleService.getRecommentArticle();
            Notice notice = noticeService.getNewNotice();
            mv.addObject("article",article);
            mv.addObject("recommendArticles",recommendArticle);
            mv.addObject("articleType",articleType);
            mv.addObject("pageNum",pageNum);
            mv.addObject("pageCount",pageCount);
            mv.addObject("notice",notice);
            mv.setViewName("/index");
        }else if (type.equals("question")){
            List<ArticleType> articleType = articleTypeService.listAll();
            List<Question> questions = questionService.questionListAll(pageNum,6);
            Long pageCount = MathUtil.pageCount(articleService.getArticleCount());
            mv.addObject("questions",questions);
            mv.addObject("articleType",articleType);
            mv.addObject("pageNum",pageNum);
            mv.addObject("pageCount",pageCount);
            mv.setViewName("/question");
        }
        return mv;
    }

    /**
     * 获取该用户的信息
     * @param userId
     * @return
     */
    @RequestMapping("/user/{userId}")
    public ModelAndView userPage(HttpSession session,@PathVariable(value = "userId",required = false) Integer userId){
        ModelAndView mv = new ModelAndView();
        User user = userService.findByUserId(userId);
        List<Article> article = articleService.getArticlesByUserId(userId);

        if (session.getAttribute(Consts.CURRENT_USER) == null){
            mv.addObject("statusMes","关注");
        }else {
            Follow follow = followService.getFollowStatus(((User)session.getAttribute(Consts.CURRENT_USER)).getUserId(),userId);
            if (follow == null || follow.getStatus() == 2){
                mv.addObject("statusMes","关注");
            }else {
                mv.addObject("statusMes","已关注");
            }
        }
        mv.addObject("user",user);
        mv.addObject("article",article);
        mv.setViewName("/personal");
        return mv;
    }
}
