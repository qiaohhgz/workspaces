package com.jim.mvc.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 10/31/13
 * Time: 4:21 AM
 * To change this template use File | Settings | File Templates.
 */
@ControllerAdvice
public class GlobalExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);

    @ExceptionHandler(JsonException.class)
    public
    @ResponseBody
    String handleJsonException(JsonException e) {
        logger.info("json exception. Error message = {}", e.getMessage());
        return e.getMessage();
    }

    @ExceptionHandler(PageException.class)
    public String handlePageException(PageException e) {
        logger.info("page exception. Error message = {}", e.getMessage());
        return "error";
    }
}
