package com.jim.mvc.exception;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 11/4/13
 * Time: 8:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class JsonException extends Exception {
    public JsonException() {
        super();
    }

    public JsonException(String s) {
        super(s);
    }

    public JsonException(Throwable cause) {
        super(cause);
    }

    public JsonException(String message, Throwable cause) {
        super(message, cause);
    }


}
