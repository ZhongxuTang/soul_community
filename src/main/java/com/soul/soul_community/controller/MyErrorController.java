package com.soul.soul_community.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName MyErrorController
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/3/6 15:04
 * @Version 1.0
 **/
@Controller
public class MyErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        Integer statueCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        mv.addObject("statueCode",statueCode);
        if (statueCode == 404){
            mv.addObject("error","Not Found");
        }else if (statueCode ==500){
            mv.addObject("error","Internal Server Error");
        }
        mv.setViewName("/error/error");
        return mv;
    }
}
