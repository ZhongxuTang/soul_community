package com.soul.soul_community.shiro.config;

import com.soul.soul_community.shiro.realm.MyRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName ShiroConfig
 * @Deacription shiro配置类
 *      Filter Chaind说明
 *      1、一个URL可以配置多个Filter，使用逗号分隔
 *      2、当设置多个过滤器时，全部通过，才视为通过
 *      3、部分过滤器可以指定参数，如perms，roles
 *
 * @Author Lemonsoul
 * @Date 2020/2/14 15:35
 * @Version 1.0
 **/
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //必须设置SecurityManager，如果不设置，会自动寻找web工程根目录下的login.jsp页面
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/");

        //设置拦截器
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        //过滤链定义，从上往下执行，一般将/**放在最下面
        //authc:所有的url必须认证通过才能访问    anon:所有的url都可以匿名访问

        //不会被拦截的链接
        filterChainDefinitionMap.put("/","anon");
        filterChainDefinitionMap.put("/question","anon");
        filterChainDefinitionMap.put("/static/**","anon");
        filterChainDefinitionMap.put("/popup/login_1.html","anon");
        filterChainDefinitionMap.put("/popup/register_1.html","anon");
        filterChainDefinitionMap.put("/popup/edit.html","anon");
        filterChainDefinitionMap.put("/popup/forgetPass.html","anon");
        filterChainDefinitionMap.put("/article","anon");
        filterChainDefinitionMap.put("/admin","anon");

        //退出的过滤器,shiro已经实现
        filterChainDefinitionMap.put("/logout","logout");

        //会被拦截的

        //filterChainDefinitionMap.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm
        securityManager.setRealm(myRealm());
        return securityManager;
    }

    /**
     * 身份认证的realm
     * @return
     */
    @Bean
    public MyRealm myRealm(){
        MyRealm myRealm = new MyRealm();
        return myRealm;
    }

    /**
     * Shiro的生命周期处理器
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro的注解（如@RequiresRoles，@RequiresPermissions）,需要借助Spring的AOP扫描使用shiro注解的类，并在必要时进行安全逻辑验证
     * 一下两个bean即可实现此功能
     */

    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }
}
