package com;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.htmlparser.Parser;
import org.htmlparser.beans.StringBean;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.util.ParserException;

public class ParserPost extends BasicParser{
	String mText;

	public HttpURLConnection load(String ipaddress) {
		URL url;
		HttpURLConnection connection;
		StringBuffer buffer;
		PrintWriter out;
		StringBean bean;
		applyProxy();
		try {
			url = new URL("http://www.ip138.com/ips1388.asp");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");

			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);

			connection.setRequestProperty("Accept-Charset", "*");
			connection.setRequestProperty("Referer","http://www.ip138.com/ips1388.asp");
			connection.setRequestProperty("User-Agent", "WhoIs.java/1.0");

			buffer = new StringBuffer(1024);
			buffer.append("id=");
			buffer.append("112.64.127.123&action=2");

			out = new PrintWriter(connection.getOutputStream());
			out.print(buffer);
			out.close();

			bean = new StringBean();
			bean.setConnection(connection);
			mText = bean.getStrings();
			System.out.println(mText);
			return connection;
		} catch (Exception e) {
			mText = e.getMessage();
		}
		return null;
	}
	
	public static void main(String[] args) throws ParserException {
		HttpURLConnection conn = new ParserPost().load("172.20.230.5");
		Parser p = new Parser(conn);
		p.postConnect(conn);
		String str = p.parse(new CssSelectorNodeFilter("body")).elementAt(0).toHtml();
		System.out.println(str);
	}
}
