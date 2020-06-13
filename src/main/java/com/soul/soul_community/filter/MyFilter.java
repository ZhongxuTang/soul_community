package com.soul.soul_community.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import java.io.IOException;

/**
 * @ClassName MyFilter
 * @Deacription 定义过滤规则
 * @Author Lemonsoul
 * @Date 2020/2/8 18:00
 * @Version 1.0
 **/

@Configuration
public class MyFilter implements Filter {

    /**
     * 自定义规律规则
     * @return
     */
    @Bean
    public FilterRegistrationBean<MyFilter> getMyFilter(){

        FilterRegistrationBean<MyFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new MyFilter());
        filter.addUrlPatterns("/*");

        return filter;
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
