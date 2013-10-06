package com.jim.account.email;

import com.jim.account.email.exception.AccountEmailException;
import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 5/20/13
 * Time: 3:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccountEmailServiceImpl implements AccountEmailService {
    private static final Logger log = Logger.getLogger(AccountEmailServiceImpl.class);

    private JavaMailSender javaMailSender;
    private String systemEmail;

    @Override
    public void sendMail(String to, String subject, String htmlText) throws AccountEmailException {
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper msgHelper = new MimeMessageHelper(msg);

            msgHelper.setFrom(systemEmail);
            msgHelper.setTo(to);
            msgHelper.setSubject(subject);
            msgHelper.setText(htmlText, true);

            javaMailSender.send(msg);
        } catch (MessagingException e) {
            String error = "Failed to send mail. To: " + to + " Subject:" + subject + " HtmlText:" + htmlText;
            log.error(error, e);
            throw new AccountEmailException(error, e);
        } catch (Exception e) {
            String error = "Failed to send mail. To: " + to + " Subject:" + subject + " HtmlText:" + htmlText;
            log.error(error, e);
            throw new AccountEmailException(error, e);
        }
    }

    public JavaMailSender getJavaMailSender() {
        return javaMailSender;
    }

    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public String getSystemEmail() {
        return systemEmail;
    }

    public void setSystemEmail(String systemEmail) {
        this.systemEmail = systemEmail;
    }
}
