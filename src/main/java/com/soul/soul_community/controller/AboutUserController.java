package com.soul.soul_community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName AboutUserController
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/3/28 12:34
 * @Version 1.0
 **/
@Controller
@RequestMapping("/aboutUser")
public class AboutUserController {

    ModelAndView mv = new ModelAndView();

    public ModelAndView question(){

        return mv;
    }
}
