package com.jim.mvc.controller;

import com.jim.mvc.modal.LoginUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.Annotation;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 11/16/13
 * Time: 11:52 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class HomeController{

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public LoginUser testOne() throws Exception {
        LoginUser loginUser = mockLoginUser();
        return loginUser;
    }

    @RequestMapping(value = "ajax", method = RequestMethod.GET)
    @ResponseBody
    public Object ajax() throws Exception {
        LoginUser loginUser = mockLoginUser();
        return loginUser;
    }

    private LoginUser mockLoginUser(){
        LoginUser loginUser = new LoginUser();
        loginUser.setUsername("Home Décor ");
        loginUser.setPwd("123456");
        return loginUser;
    }
}
