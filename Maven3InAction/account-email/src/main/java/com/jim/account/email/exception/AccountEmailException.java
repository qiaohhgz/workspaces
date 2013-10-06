package com.jim.account.email.exception;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 5/20/13
 * Time: 3:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccountEmailException extends Exception {
    public AccountEmailException(String msg) {
        super(msg);
    }

    public AccountEmailException(String msg, Exception ex) {
        super(msg, ex);
    }
}
