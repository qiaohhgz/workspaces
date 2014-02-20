package com.jim.mvc.model.exception;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 11/4/13
 * Time: 9:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class PageException extends Exception {
    public PageException() {
        super();
    }

    public PageException(String s) {
        super(s);
    }

    public PageException(Throwable cause) {
        super(cause);
    }

    public PageException(String message, Throwable cause) {
        super(message, cause);
    }
}
