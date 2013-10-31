package com.jim.mvc.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
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
public class HttpRequestExceptionHandler extends SimpleMappingExceptionResolver {
    Logger logger = LoggerFactory.getLogger(HttpRequestExceptionHandler.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.error(ex.getMessage(), ex);
        Map<String, String> model = new HashMap<String, String>();
        model.put("message", ex.getMessage());
        return new ModelAndView("error", model);
//        return super.doResolveException(request, response, handler, ex);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
