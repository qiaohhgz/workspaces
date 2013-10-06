package com.util.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class JSendEmail {
	private String host = "smtp.163.com";

	public void send(String to, String from, String cc, String bcc, String subject, String messageText,
			boolean sessionDebug) {
		Properties props = System.getProperties();
		props.put("mail.host", host);
		props.put("mail.transport.protocol", "smtp");
		Session mailSession = Session.getDefaultInstance(props, null);
		mailSession.setDebug(sessionDebug);
		try {
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] addressTo = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, addressTo);
			if (cc.trim().length() != 0) {
				InternetAddress[] addressCc = { new InternetAddress(cc) };
				msg.setRecipients(Message.RecipientType.CC, addressCc);
			}
			if (bcc.trim().length() != 0) {
				InternetAddress[] addressBcc = { new InternetAddress(bcc) };
				msg.setRecipients(Message.RecipientType.BCC, addressBcc);
			}
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setText(messageText);
			Transport.send(msg);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void applyProxy() {
		System.getProperties().put("http.proxySet", "true");
		System.getProperties().put("http.proxyHost", "172.20.230.5");
		System.getProperties().put("http.proxyPort", "3128");
		System.getProperties().put("https.proxySet", "true");
		System.getProperties().put("https.proxyHost", "172.20.230.5");
		System.getProperties().put("https.proxyPort", "3128");
		System.getProperties().put("smtp.proxySet", "true");
		System.getProperties().put("smtp.proxyHost", "172.20.230.5");
		System.getProperties().put("smtp.proxyPort", "3128");
	}

	public static void main(String[] args) {
		applyProxy();
		String to = "qsjhhgz@163.com";
		String from = "qiaohhgz@163.com";

		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.163.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName(from);
		mailInfo.setPassword("q7837793");// 您的邮箱密码
		mailInfo.setFromAddress(from);
		mailInfo.setToAddress(to);
		mailInfo.setSubject("设置邮箱标题 如http://www.guihua.org 中国桂花网");
		mailInfo.setContent("设置邮箱内容 如http://www.guihua.org 中国桂花网 是中国最大桂花网站==");
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		sms.sendTextMail(mailInfo);// 发送文体格式
		System.out.println("ok text");
		sms.sendHtmlMail(mailInfo);// 发送html格式
		System.out.println("ok html");
	}
}