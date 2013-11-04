package com.jim.mvc.controller;

import com.google.gson.Gson;
import com.jim.mvc.constant.SessionKeys;
import com.jim.mvc.model.VRegisterRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory
            .getLogger(HomeController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "redirect:home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(HttpServletRequest request, Locale locale, Model model) {
        if (request.getSession().getAttribute(SessionKeys.SESSION_USER) == null) {
            return "redirect:login";
        }
        logger.info("Welcome home! The client locale is {}.", locale);
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate);
        return "home";
    }

    @RequestMapping(value = "/getServerTime", method = RequestMethod.GET)
    public
    @ResponseBody
    String getServerTime(Locale locale) {
        logger.info("getting server time.");
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        logger.info("server time is " + formattedDate);
        return formattedDate;
    }

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String registerPage() {
        logger.info("Welcome register page!");
        return "register";
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public
    @ResponseBody
    VRegisterRequest register(HttpServletRequest request, @RequestBody VRegisterRequest model) {
        logger.info("Welcome register! name = {}", model.getName());
        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request) {
        return "login";
    }

    @RequestMapping(value = "/loginOne", method = RequestMethod.POST)
    public
    @ResponseBody
    String loginOne(HttpServletRequest request, String username, String password) {
        logger.info("login two username = {} password = {}", username, password);
        if (null != username && null != password && username.length() > 0 && password.length() > 0) {
            request.getSession().setAttribute(SessionKeys.SESSION_USER, username);
            return "redirect:home";
        } else {
            return "redirect:login";
        }

    }

    @RequestMapping(value = "/loginTwo", method = RequestMethod.POST)
    public String loginTwo(Model model, String username, String password) {
        logger.info("login two username = {} password = {}", username, password);
        if (null != username && null != password) {
            model.addAttribute(SessionKeys.SESSION_USER, username);
            return "home";
        }
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute(SessionKeys.SESSION_USER);
        if (request.getSession().getAttribute(SessionKeys.SESSION_USER) == null) {
            logger.info("logout success");
        } else {
            logger.info("logout error");
        }
        new RedirectView();
        return "redirect:login";
    }
}
