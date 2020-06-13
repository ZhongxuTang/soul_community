package com.soul.soul_community.controller;

import com.soul.soul_community.entity.Follow;
import com.soul.soul_community.entity.User;
import com.soul.soul_community.service.FollowService;
import com.soul.soul_community.service.UserService;
import com.soul.soul_community.util.Consts;
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
 * @ClassName FollowController
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/3/28 19:47
 * @Version 1.0
 **/
@Slf4j
@Controller
@RequestMapping("/follow")
public class FollowController {

    @Autowired
    FollowService followService;

    @Autowired
    UserService userService;

    /**
     * 用户的关注和取消关注
     * @param session
     * @param resMap
     * @return
     */
    @ResponseBody
    @RequestMapping("/followUser")
    public Map<String,Object> followUser(HttpSession session, @RequestParam Map<String,String> resMap){
        Map<String,Object> map = new HashMap<>();

        if (session.getAttribute(Consts.CURRENT_USER) == null){
            map.put("success",false);
            map.put("errorInfo","请先登录");
        }else {
            log.info(resMap.toString());
            Follow follow = followService.getFollowStatus(((User)session.getAttribute(Consts.CURRENT_USER)).getUserId(),Integer.parseInt(resMap.get("userId")));
            if (follow == null){
                User currentUser = userService.findByUserId(Integer.parseInt(resMap.get("userId")));
                Follow follow1 = new Follow();
                follow1.setFollowTime(new Date());
                follow1.setCurrentUserId(((User) session.getAttribute(Consts.CURRENT_USER)).getUserId());
                follow1.setFollowedUserId(currentUser);
                followService.saveFollow(follow1);
                map.put("success",true);
                map.put("yes","关注成功");
                map.put("thisStatus","取消关注");
            }else if(follow.getStatus() == 2){
                follow.setStatus(1);
                log.info(follow.toString());
                followService.saveFollow(follow);
                map.put("success",true);
                map.put("yes","关注成功");
                map.put("thisStatus","取消关注");
            }else {
                follow.setStatus(2);
                map.put("success",true);
                followService.saveFollow(follow);
                map.put("yes","取消关注成功");
                map.put("thisStatus","关注");
            }

        }
        return map;
    }

}
