package com.jim.mvc.exception;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 11/4/13
 * Time: 10:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomGenericException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String errCode;
    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public CustomGenericException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
