package com.jim.mvc.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 10/31/13
 * Time: 4:21 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class HttpRequestExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(HttpRequestExceptionHandler.class);

    @ExceptionHandler(JsonException.class)
    public
    @ResponseBody
    JsonException handleException(JsonException e) {
        logger.info("json exception.{}", e);
        return e;
    }

    @ExceptionHandler(PageException.class)
    public String handleException(PageException e) {
        logger.info("page exception.{}", e);
        return "error";
    }
}
