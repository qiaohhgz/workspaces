package com;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class T2 {
	public static void main(String[] args) {
		System.getProperties().put("http.proxySet", "true");
		System.getProperties().put("http.proxyHost", "172.20.230.5");
		System.getProperties().put("http.proxyPort", "3128");
		System.getProperties().put("https.proxySet", "true");
		System.getProperties().put("https.proxyHost", "172.20.230.5");
		System.getProperties().put("https.proxyPort", "3128");

		new T2().testGet();
	}

	public void testGet() {
		GetMethod gm = new GetMethod("http://www.baidu.com");
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(
				1000 * 10);
		int status = 0;
		try {
			status = client.executeMethod(gm);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (status == HttpStatus.SC_OK) {
			try {
				System.out.println(gm.getResponseBodyAsString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		gm.releaseConnection();
	}

	public void testPost() {
		PostMethod pm = new PostMethod("http://mail.163.com/");
		pm.addParameter("username", "qsjhhgz@163.com");
		pm.addParameter("password", "q7837793");
		pm.addParameter("savelogin", "0");
		pm.addParameter("url2", "http://mail.163.com/errorpage/err_163.htm");
		int status = 0;
		HttpClient hc = new HttpClient();
		hc.getHttpConnectionManager().getParams().setConnectionTimeout(
				1000 * 10);
		try {
			status = hc.executeMethod(pm);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (HttpStatus.SC_OK == status) {
			System.out.println("ok");
		}
		try {
			System.out.println(pm.getResponseBodyAsString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void test(){
		
	}
}
