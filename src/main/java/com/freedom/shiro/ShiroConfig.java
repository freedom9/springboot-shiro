package com.freedom.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Auther: freedom
 * @Date: 2018/10/30
 * @Description: Shiro的配置类
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean factory = new ShiroFilterFactoryBean();

        //设置安全管理器
        factory.setSecurityManager(securityManager);

        //设置内置过滤器
        /**
         * 常用的过滤器：
         *    anon: 无需认证（登录）可以访问
         *    authc: 必须认证才可以访问
         *    user: 如果使用rememberMe的功能可以直接访问
         *    perms: 该资源必须得到资源权限才可以访问
         *    role: 该资源必须得到角色权限才可以访问
         *
         */
        Map<String, String> filterMap = new LinkedHashMap<String, String>();
        filterMap.put("/testThymeleaf", "anon");
        filterMap.put("/login", "anon");

        //授权过滤器
        filterMap.put("/add", "perms[user:add]");
        filterMap.put("/update", "perms[user:update]");

        filterMap.put("/**", "authc");
        factory.setFilterChainDefinitionMap(filterMap);

        //修改重定向登录页面
        factory.setLoginUrl("/toLogin");

        factory.setUnauthorizedUrl("/unAuth");

        return factory;
    }


    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    /**
     * 创建realm
     */
    @Bean(name = "userRealm")
    public UserRealm getUserRealm(){
        return new UserRealm();
    }

    /**
     * 配置ShiroDialect用于thymeleaf和shiro标签配合使用
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
