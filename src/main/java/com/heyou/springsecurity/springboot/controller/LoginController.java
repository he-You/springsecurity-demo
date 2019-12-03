package com.heyou.springsecurity.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 简要说明:
 *
 * @author heyou
 * @date 2019-12-02 15:48
 */
@RestController
public class LoginController {

    @RequestMapping(value = "/login-sucess",name = "登录",produces = {"text/plain;charset=UTF-8"})
    public String loginSucess(){
        return "登录成功";
    }

    @GetMapping(value = "/r/r1",produces = {"text/plain;charset=UTF-8"})
    public String r1(){
        System.out.println("进入r1接口");
        return "请求资源1";
    }

    @GetMapping(value = "/r/r2",produces = {"text/plain;charset=UTF-8"})
    public String r2(){
        System.out.println("进入r2接口");
        return "请求资源2";
    }



    @RequestMapping("/errorPage")
    public String toErrorPage(){
        return "error";
    }


    @RequestMapping("/homePage")
    public String toHomePage(){
        return "home";
    }
}
