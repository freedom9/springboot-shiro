package com.freedom.DTO;

/**
 * @Auther: freedom
 * @Date: 2018/12/13
 * @Description:
 */
public class LoginDTO {

    private String username;

    private String password;

    private String captchaCode;

    public LoginDTO() {
    }

    public LoginDTO(String username, String password, String captchaCode) {
        this.username = username;
        this.password = password;
        this.captchaCode = captchaCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
