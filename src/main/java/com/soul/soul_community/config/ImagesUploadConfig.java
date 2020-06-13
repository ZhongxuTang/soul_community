package com.soul.soul_community.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName ImagesUploadConfig
 * @Deacription TODO
 * @Author Lemonsoul
 * @Date 2020/5/18 16:11
 * @Version 1.0
 **/
@Configuration
public class ImagesUploadConfig implements WebMvcConfigurer {
    /**
     * 解决图片上传无法立即显示，而需要重启才能访问的问题。
     * 这是因为对服务器的保护措施导致的，服务器不能对外部暴露真实的资源路径，需要配置虚拟路径映射访问。
     */

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //获取文件的真实路径
        String path = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\img\\";
        //       /img/**是对应resource下工程目录
        registry.addResourceHandler("/img/**").addResourceLocations("file:"+path);
    }
}
