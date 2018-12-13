package com.freedom.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @Auther: freedom
 * @Date: 2018/12/13
 * @Description: 扩展shiro登录表单Token，增加验证码字段
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {

    //验证码
    private String captchaCode;

    public CaptchaUsernamePasswordToken(String username, String password, String captchaCode) {
        super(username, password);
        this.captchaCode = captchaCode;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }
}
