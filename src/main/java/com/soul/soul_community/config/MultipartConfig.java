package com.soul.soul_community.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * @ClassName MultipartConfig
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/3/25 11:01
 * @Version 1.0
 **/
@Configuration
public class MultipartConfig {

    @Bean
    MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation("/app/pttms/tmp");
        return factory.createMultipartConfig();
    }
}
