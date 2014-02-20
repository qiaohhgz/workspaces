/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jim.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;


public class ExceptionHandler extends SimpleMappingExceptionResolver {

    private String ajaxErrorView;
    private String ajaxDefaultErrorMessage = "An error has occurred";
    private boolean ajaxShowTechMessage = true;
    
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        if( isAjax(request) ) {
            String exceptionMessage = ajaxDefaultErrorMessage;
            if( ajaxShowTechMessage )
                exceptionMessage += "\n" + getExceptionMessage(e);
            ModelAndView m = new ModelAndView(ajaxErrorView);
            m.addObject("exceptionMessage", exceptionMessage);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return m;
        } else {
            return super.resolveException(request, response, o, e);
        }
    }

    private String getExceptionMessage(Throwable e) {
        String message = "";
        while( e != null ) {
            message += e.getMessage() + "\n";
            e = e.getCause();
        }
        return message;
    }
    
    private boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

    public void setAjaxDefaultErrorMessage(String ajaxDefaultErrorMessage) {
        this.ajaxDefaultErrorMessage = ajaxDefaultErrorMessage;
    }

    public void setAjaxErrorView(String ajaxErrorView) {
        this.ajaxErrorView = ajaxErrorView;
    }

    public void setAjaxShowTechMessage(boolean ajaxShowTechMessage) {
        this.ajaxShowTechMessage = ajaxShowTechMessage;
    }

    public String getAjaxDefaultErrorMessage() {
        return ajaxDefaultErrorMessage;
    }

    public String getAjaxErrorView() {
        return ajaxErrorView;
    }

    public boolean isAjaxShowTechMessage() {
        return ajaxShowTechMessage;
    }
    
    
    
}
