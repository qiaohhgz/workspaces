package com.jim.mvc.controller;

import com.jim.mvc.exception.CustomGenericException;
import com.jim.mvc.exception.JsonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 11/4/13
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("exception")
public class ExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @RequestMapping(value = "getPages", method = RequestMethod.GET)
    public ModelAndView getPages(String type) throws Exception {
        if ("error".equals(type)) {
            // go handleCustomException
            throw new CustomGenericException("E888", "This is custom message");
        } else if ("io-error".equals(type)) {
            // go handleAllException
            throw new IOException();
        } else {
            return new ModelAndView("index").addObject("msg", type);
        }
    }

    @RequestMapping(value = "getPagesAsJson", method = RequestMethod.GET)
    public
    @ResponseBody
    String getPagesAsJson(String type) throws Exception {
        if ("error".equals(type)) {
            // go handleCustomException
            throw new JsonException("This is json exception message");
        } else if ("io-error".equals(type)) {
            // go handleAllException
            throw new IOException();
        } else {
            return "page data";
        }
    }

    @ExceptionHandler(JsonException.class)
    public
    @ResponseBody
    String handleJsonException(HttpServletRequest request, HttpServletResponse response, JsonException ex) {
        logger.info("handleJsonException");
        response.setStatus(500);
        return ex.getMessage();

    }

    @ExceptionHandler(CustomGenericException.class)
    public ModelAndView handleCustomException(CustomGenericException ex) {
        logger.info("handleCustomException");
        ModelAndView model = new ModelAndView("error");
        model.addObject("errCode", ex.getErrCode());
        model.addObject("errMsg", ex.getErrMsg());

        return model;

    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {
        logger.info("handleAllException");
        ModelAndView model = new ModelAndView("error");
        model.addObject("errMsg", "this is Exception.class");

        return model;

    }

}
