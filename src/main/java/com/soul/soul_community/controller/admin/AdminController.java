package com.soul.soul_community.controller.admin;

import com.soul.soul_community.entity.*;
import com.soul.soul_community.service.*;
import com.soul.soul_community.util.Consts;
import com.soul.soul_community.util.EncryptionUtil;
import com.soul.soul_community.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AdminController
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/4/12 15:45
 * @Version 1.0
 **/
@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @Autowired
    CommentService commentService;

    @Autowired
    QuestionService questionService;

    @Autowired
    UserLogService userLogService;

    @Autowired
    AdminService adminService;

    @Autowired
    NoticeService noticeService;

    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/admin/login")
    public ModelAndView adminLogin(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/login");
        return mv;
    }

    @ResponseBody
    @RequestMapping("/admin/loginIt")
    public Map<String,Object> login(Admin admin, HttpSession session){
        Map<String,Object> map = new HashMap<>();

        logger.info("管理员"+admin.getAdminUsername()+"登录");

        if (StringUtil.isEmpty(admin.getAdminUsername())){
            map.put("success",false);
            map.put("errorInfo","请输入账户名");
        }else if (StringUtil.isEmpty(admin.getAdminPassword())){
            map.put("success",false);
            map.put("errorInfo","请输入密码");
        }else {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(admin.getAdminUsername(), EncryptionUtil.md5(admin.getAdminPassword(),EncryptionUtil.SALT));
            try {
                subject.login(token);       //登录验证
                String username = (String) SecurityUtils.getSubject().getPrincipal();

                    Admin currentAdmin = adminService.getAdminByUserName(username);

                    session.setAttribute(Consts.CURRENT_ADMIN,currentAdmin);

                    map.put("success",true);

            }catch (Exception e){
                logger.info("用户名或密码错误");
                e.printStackTrace();
                map.put("success",false);
                map.put("errorInfo","用户名或密码错误");
            }

        }
        return map;

    }

    @RequestMapping("/admin")
    public ModelAndView admin(HttpSession session){
        ModelAndView mv = new ModelAndView();

        if (session.getAttribute(Consts.CURRENT_ADMIN) == null){
            mv.setViewName("/admin/login");
            return mv;
        }else {
            Long userCount = userService.getUserCount();
            Long articleCount = articleService.getArticleCount();
            Long questionCount = questionService.getQuestionCount();
            List<Article> article = articleService.getNewArticle();
            List<UserLog> userLogs = userLogService.getAllUserLog();
            Long noticeCount = noticeService.getNoticeCount();

            mv.addObject("userCount",userCount);
            mv.addObject("articleCount",articleCount);
            mv.addObject("questionCount",questionCount);
            mv.addObject("article",article);
            mv.addObject("userLogs",userLogs);
            mv.addObject("noticeCount",noticeCount);
            mv.setViewName("/admin/admin");
            return mv;
        }

    }

    @RequestMapping("/admin/article")
    public ModelAndView adminArticle(){
        ModelAndView mv = new ModelAndView();
        List<Article> article = articleService.getNewArticle();
        mv.addObject("article",article);

        mv.setViewName("/admin/adminArticle");
        return mv;
    }

    @RequestMapping("/admin/question")
    public ModelAndView adminQuestion(){
        ModelAndView mv = new ModelAndView();

        List<Question> question = questionService.getQuestionByTime();

        mv.addObject("question",question);
        mv.setViewName("/admin/adminQuestion");
        return mv;
    }

    @RequestMapping("/admin/user")
    public ModelAndView adminUser(){
        ModelAndView mv = new ModelAndView();

        List<User> users = userService.getAllUser();

        mv.addObject("users",users);
        mv.setViewName("/admin/adminUser");
        return mv;
    }

    @RequestMapping("/admin/switchComment")
    public ModelAndView switchComment(){
        ModelAndView mv = new ModelAndView();
        List<Comment> comment = commentService.getNewComment();
        mv.addObject("comment",comment);
        mv.setViewName("/admin/model/newComment");
        return mv;
    }

    @RequestMapping("/admin/switchArticle")
    public ModelAndView switchArticle(){
        ModelAndView mv = new ModelAndView();
        List<Article> article = articleService.getNewArticle();
        mv.addObject("article",article);
        mv.setViewName("/admin/model/newArticle");
        return mv;
    }

    @ResponseBody
    @RequestMapping("/admin/postNotice")
    public Map<String,Object> postNotice(HttpSession session,@RequestParam Map<String,String> resMap){
        Map<String,Object> map = new HashMap<>();
        logger.info("新通知:"+resMap.get("notice"));

        Admin admin = (Admin) session.getAttribute(Consts.CURRENT_ADMIN);

        if (admin == null){
            map.put("success",false);
            map.put("errorInfo","请先登录");
        }else {
            try{
                Notice notice = new Notice(resMap.get("notice"),new Date(),admin);
                noticeService.saveNotice(notice);
                map.put("success",true);
            }catch (Exception e){
                map.put("success",false);
                map.put("errorInfo","发布异常");
            }
        }

        return map;
    }
}
