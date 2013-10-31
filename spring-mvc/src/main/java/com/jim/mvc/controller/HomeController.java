package com.jim.mvc.controller;

import com.google.gson.Gson;
import com.jim.mvc.model.VRegisterRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory
            .getLogger(HomeController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
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
    String register(HttpServletRequest request, @RequestParam String json) {
        Gson g = new Gson();
        VRegisterRequest model = g.fromJson(json, VRegisterRequest.class);
        logger.info("Welcome register!");

//        String json = String.format("name:{0},hobby:{1}", model.getName(), model.getHobby().get(0).getName());
        return "{msg:'test'}";
    }
}
