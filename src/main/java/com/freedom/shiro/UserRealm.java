package com.freedom.shiro;

import com.freedom.entity.User;
import com.freedom.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
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
    @Autowired
    private Md5Test md5Test;

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


//        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

        CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authenticationToken;

        String captchaCode = token.getCaptchaCode();

        if (captchaCode == null || "".equals(captchaCode)){
            throw new CaptchaEmptyException();
        }

        //获取session中的验证码
        //默认KEY为KAPTCHA_SESSION_KEY,与ValidateCodeController中setAttribute的KEY相对应
        String sessionCaptcha = (String) SecurityUtils.getSubject().getSession().getAttribute("KAPTCHA_SESSION_KEY");

        if (!captchaCode.equals(sessionCaptcha)){
            throw new CaptchaErrorException();
        }

        /*//以下信息是从数据库中获取的.
        //1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
        Object principal = userService.findByUsername(token.getUsername());
        if(principal == null){
            //用户不存在
            return null;    //shiro底层会抛出UnknownAccountException错误
        }

        //2.credentialsSalt:盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(token.getUsername());
        //3、hashedCredentials:密码
        Object hashedCredentials = md5Test.md5(token.getPassword(), credentialsSalt);
        //4、realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
        String realmName = getName();*/

        User user = userService.findByUsername(token.getUsername());
        if(user == null){
            //用户不存在
            return null;    //shiro底层会抛出UnknownAccountException错误
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getUsername()), getName());
        //SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, token.getPassword(), "");
        return info;
    }


    /**
     * 注入加密方式二：
     */
  /*  @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        // 重写 setCredentialsMatcher 方法为自定义的 Realm 设置 hash 验证方法
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);
        super.setCredentialsMatcher(hashedCredentialsMatcher);
    }*/


    /**
     * 自定义验证码为空异常
     * AuthenticationException为shiro认证错误的异常，不同错误类型继承该异常即可
     */
    public class CaptchaEmptyException extends AuthenticationException{
    }

    /**
     * 自定义验证码错误异常
     */
    public class CaptchaErrorException extends AuthenticationException{
    }
}
