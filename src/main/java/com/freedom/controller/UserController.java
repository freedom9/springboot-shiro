package com.freedom.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * @Auther: freedom
 * @Date: 2018/10/29
 * @Description:
 */
@Controller
public class UserController {

    @GetMapping("/testThymeleaf")
    public String testThymeleaf(Model model){
        model.addAttribute("name", "恒拓开源");
        return "test";
    }

    @GetMapping("/add")
    public String add(){
        return "/user/add";
    }

    @GetMapping("/update")
    public String update(){
        return "/user/update";
    }

    @GetMapping("/toLogin")
    public String toLogin(){
        return "/login";
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model){
        //1、获得subjest
        Subject subject = SecurityUtils.getSubject();
        //2、封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //3、执行登录方法
        try {
            subject.login(token);
        } catch (UnknownAccountException e){
            model.addAttribute("msg", "用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException e){
            model.addAttribute("msg", "密码错误");
            return "login";
        } catch (LockedAccountException e){
            model.addAttribute("msg", "用户被锁定");
            return "login";
        } catch (AuthenticationException e){
            model.addAttribute("msg","登录失败");
            return "login";
        }

        return "redirect:testThymeleaf";

    }

    @GetMapping("/unAuth")
    public String unAuth(){
        return "/unAuth";
    }
}
