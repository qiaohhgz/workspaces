package com.jim.account.email;

import com.jim.account.email.exception.AccountEmailException;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 5/20/13
 * Time: 3:52 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AccountEmailService {
    void sendMail(String to, String subject, String htmlText) throws AccountEmailException;
}
