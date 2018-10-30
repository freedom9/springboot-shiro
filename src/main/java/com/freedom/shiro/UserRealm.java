package com.freedom.shiro;

import com.freedom.entity.User;
import com.freedom.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: freedom
 * @Date: 2018/10/30
 * @Description:  自定义realm
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 执行授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //System.out.println("授权");

        //给资源授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        /*info.addStringPermission("user:add");*/

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        User dbUser = userService.findById(user.getId());

        //info.addStringPermission(dbUser.getPerms());

        Set<String> perms = new HashSet<String>();
        for(String perm : dbUser.getPerms().trim().split(",")){
            if(StringUtils.isNotEmpty(perm)){
                perms.add(perm);
            }
        }
        info.addStringPermissions(perms);
        return info;
    }

    /**
     * 执行认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //System.out.println("认证");
/*
        //假设数据库用户名和密码
        String username = "bjl";
        String password = "123";*/


        //1、判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        User user = userService.findByUsername(token.getUsername());
        if(user == null){
            //用户不存在
            return null;    //shiro底层会抛出UnknownAccountException错误
        }

        //2、判断密码
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, token.getPassword(), "");
        return info;
    }
}
