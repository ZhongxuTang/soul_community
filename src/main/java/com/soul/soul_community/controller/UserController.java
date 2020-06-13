package com.soul.soul_community.controller;

import com.soul.soul_community.entity.FeedBack;
import com.soul.soul_community.entity.User;
import com.soul.soul_community.entity.UserLog;
import com.soul.soul_community.service.FeedBackService;
import com.soul.soul_community.service.UserLogService;
import com.soul.soul_community.service.UserService;
import com.soul.soul_community.service.impl.UserFormServiceImpl;
import com.soul.soul_community.util.Consts;
import com.soul.soul_community.util.EncryptionUtil;
import com.soul.soul_community.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserController
 * @Deacription 用户Controller
 * @Author Lemonsoul
 * @Date 2020/1/25 23:04
 * @Version 1.0
 **/
@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserFormServiceImpl userFormService;

    @Autowired
    UserService userService;

    @Autowired
    UserLogService userLogService;

    @Autowired
    FeedBackService feedBackService;

 /*   @PostMapping("/login")
    public ModelAndView login(User user, HttpServletRequest request){
        ModelAndView mv = new ModelAndView();

       mv.addObject(user);
        mv.setViewName("redirect:/");
       request.getSession().setAttribute("user",user);

       return mv;
   }

   @GetMapping("/login")
    public ModelAndView login(){
       return new ModelAndView("login");
   }*/

    /**
     * 用户登录
     * @param user
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public Map<String,Object> login(User user, HttpSession session, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        if (StringUtil.isEmpty(user.getUserEmail())){
            map.put("success",false);
            map.put("errorInfo","请输入用户名");
        }else if (StringUtil.isEmpty(user.getUserPassword())){
            map.put("success",false);
            map.put("errorInfo","请输入密码");
        }else{
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUserEmail(),EncryptionUtil.md5(user.getUserPassword(),EncryptionUtil.SALT));

            try {
                subject.login(token);       //登录验证
                String username = (String) SecurityUtils.getSubject().getPrincipal();
                User currentUser = userService.findByEmail(username);
                System.out.println("用户状态"+currentUser.isSeal());
                if (currentUser.isSeal()){
                    map.put("success",false);
                    map.put("errorInfo","该用户已封禁，请联系管理员");
                    subject.logout();

                }else {
                    currentUser.setUserLatelyLoginTime(new Date());
                    userService.saveUser(currentUser);
                    session.setAttribute(Consts.CURRENT_USER,currentUser);

                    UserLog userLog = new UserLog
                            (currentUser,"用户登录",request.getMethod(),"安徽蚌埠",request.getRequestURL().toString(),request.getServletPath(),request.toString(),request.getSession().getId(),new Date());
                    userLogService.saveUserLog(userLog);
                    map.put("success",true);

                }
            }catch (Exception e){
                System.out.println("出现错误");
                e.printStackTrace();
                map.put("success",false);
                map.put("errorInfo","用户名或密码错误");
            }
        }

        return map;
    }

    /**
     * 用户注册
     * @param user
     * @param bindingResult
     * @param request
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/reg")
    public Map<String,Object> reg(@Valid User user, BindingResult bindingResult, HttpServletRequest request, HttpSession session) {
        Map<String,Object> map = new HashMap<>();
        if (bindingResult.hasErrors()){
            map.put("success",false);
            //获取错误信息
            map.put("errorInfo",bindingResult.getFieldError().getDefaultMessage());
            System.out.println(map.get("errorInfo"));
        }else if (userService.findByEmail(user.getUserEmail()) != null){
            log.info("邮箱已存在");
            map.put("success",false);
            //获取错误信息
            map.put("errorInfo","邮箱已存在");
        }else if (!request.getParameter("regVerNumber").equals(session.getAttribute(Consts.MAIL_CODE_NAME))){
            map.put("success",false);
            //获取错误信息
            map.put("errorInfo","验证码错误");
        }
        else{
            user.setUserPassword(EncryptionUtil.md5(user.getUserPassword(),EncryptionUtil.SALT));
            user.setUserName("test"+userService.getUserCount());
            user.setUserRegisterDate(new Date());
            user.setUserLatelyLoginTime(new Date());
            userService.saveUser(user);

            UserLog userLog = new UserLog
                    (user,"用户注册",request.getMethod(),"安徽蚌埠",request.getRequestURL().toString(),request.getServletPath(),request.toString(),request.getSession().getId(),new Date());
            userLogService.saveUserLog(userLog);
            map.put("success",true);
        }
        return map;
    }

    /**
     * 找回密码
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/forgetPass")
    public Map<String,Object> forgetPass(User user, HttpServletRequest request, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        if (StringUtil.isEmpty(user.getUserEmail())){
            map.put("success",false);
            map.put("errorInfo","请输入用户名");
        }else if (StringUtil.isEmpty(user.getUserPassword())){
            map.put("success",false);
            map.put("errorInfo","请输入新密码");
        }else if (!request.getParameter("regVerNumber").equals(session.getAttribute(Consts.MAIL_CODE_NAME))){
            map.put("success",false);
            //获取错误信息
            map.put("errorInfo","验证码错误");
        }else{
            User currentUser = userService.findByEmail(user.getUserEmail());
            currentUser.setUserPassword(EncryptionUtil.md5(user.getUserPassword(),EncryptionUtil.SALT));
            userService.saveUser(currentUser);
            UserLog userLog = new UserLog
                    (currentUser,"找回密码",request.getMethod(),"安徽蚌埠",request.getRequestURL().toString(),request.getServletPath(),request.toString(),request.getSession().getId(),new Date());
            userLogService.saveUserLog(userLog);
            map.put("success",true);
        }
        return map;
    }

    /*获取邮箱验证码*/
    @ResponseBody
    @RequestMapping("/getMail")
    public Map<String,Object> getMail(HttpServletRequest request, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        String userEmail = request.getParameter("email");
        if (userService.findByEmail(userEmail) == null){
            map.put("success",true);
            String rs = userFormService.getMail(userEmail);
            session.setAttribute(Consts.MAIL_CODE_NAME,rs);
        }else {
            log.info(userService.findByEmail(userEmail).toString());
            map.put("success",false);
            //获取错误信息
            map.put("errorInfo","邮箱已存在");
        }
        return map;
    }

    /**
     * 反馈
     * @return
     */
    @ResponseBody
    @RequestMapping("/feedback")
    public Map<String,Object> feedBack(HttpSession session,@RequestParam Map<String, String> resMap){
        Map<String,Object> map = new HashMap<>();
        User user = (User) session.getAttribute(Consts.CURRENT_USER);
        if (user == null){
            map.put("success",false);
            map.put("errorInfo","请先登录");
        }else {
            log.info(resMap.get("userEmail"));
            try{
                FeedBack feedBack = new FeedBack(resMap.get("userEmail"),resMap.get("content"),new Date(),user);
                feedBackService.saveFeedBack(feedBack);
                map.put("success",true);
            }catch (Exception e){
                map.put("success",false);
                map.put("errorInfo","任务出错");
            }
        }
        return map;
    }
}
