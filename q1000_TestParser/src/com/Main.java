package com;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class Main {
	private static final String charset_utf8 = "utf-8";
	private static final String charset_gb2312 = "gb2312";
	private static final String charset_gbk = "gbk";

	public static void main(String[] args) {
		try {
			//baidu();
			// searchPageImages();
			// test();
			System.out.println(urlToString("http://mp3.baidu.com"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 申请代理
	 */
	public static void applyProxy() {
		System.getProperties().put("http.proxySet", "true");
		System.getProperties().put("http.proxyHost", "172.20.230.5");
		System.getProperties().put("http.proxyPort", "3128");
		System.getProperties().put("https.proxySet", "true");
		System.getProperties().put("https.proxyHost", "172.20.230.5");
		System.getProperties().put("https.proxyPort", "3128");
	}

	public static String urlToString(String url) throws ParserException {
		applyProxy();
		Parser p = new Parser(url);
		p.setEncoding(charset_gb2312);
		NodeList bodys = p.extractAllNodesThatMatch(new CssSelectorNodeFilter(
				"html"));
		if (!isEmpty(bodys)) {
			return bodys.elementAt(0).toHtml();
		}
		return null;
	}

	public static void baidu() throws ParserException, MalformedURLException,
			IOException {
		applyProxy();
		String html = "http://www.baidu.com/";
		Parser p = new Parser(html);
		p.setEncoding(charset_gb2312);
		NodeList bodys = p.extractAllNodesThatMatch(new CssSelectorNodeFilter(
				"body"));
		if (!isEmpty(bodys)) {
			Node node = bodys.elementAt(0);
			if (node instanceof LinkTag) {
				System.out.println(((LinkTag) node).getLink());
			} else {
				String value = node.toPlainTextString();
				System.out.println(value);
			}
		}
	}

	public static void searchPageImages() throws ParserException,
			MalformedURLException, IOException {
		try {
			applyProxy();
			String html = "http://newyork.citysearch.com/profile/7108110/new_york_ny/lombardi_s.html";
			Parser p = new Parser(html);
			p.setEncoding(charset_gbk);
			NodeFilter filter = new CssSelectorNodeFilter("img");
			NodeList bodys = p.extractAllNodesThatMatch(filter);
			HashSet<String> imageSrc = new HashSet<String>();
			if (!isEmpty(bodys)) {
				for (int i = 0; i < bodys.size(); i++) {
					Node imgNode = bodys.elementAt(i);
					Tag t = null;
					if (imgNode instanceof Tag)
						t = (Tag) imgNode;
					if (null != t) {
						String src = t.getAttribute("src");
						if (!imageSrc.contains(src)) {
							imageSrc.add(src);
							System.out.println(src);
							saveImage(src);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveImage(String imageSrc) {
		try {
			URL url = new URL(imageSrc);
			String name = imageSrc.substring(imageSrc.lastIndexOf("/") + 1);
			if (name.indexOf(".") == -1)
				return;
			File file = new File("E:\\Images\\" + name);
			byte[] bs = new byte[1024];
			URLConnection conn = url.openConnection();
			InputStream is = conn.getInputStream();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			while (is.read(bs) != -1) {
				os.write(bs);
			}
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(os.toByteArray());
			fos.close();
			is.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean isEmpty(NodeList bodys) {
		return bodys == null || bodys.size() == 0;
	}

	public static void test() {
		try {
			applyProxy();
			String html = "http://www.local.com/business/results/?keyword=pizza&location=Los+Angeles%2CLA";
			Parser p = new Parser(html);
			p.setEncoding(charset_utf8);
			NodeFilter blockFilter = new CssSelectorNodeFilter(
					"div[class~=courtesyListing]");
			NodeFilter filter = new CssSelectorNodeFilter(
					"ul[class~=starRating]");
			Node[] blocks = p.extractAllNodesThatMatch(blockFilter)
					.toNodeArray();
			for (int i = 0; i < blocks.length; i++) {
				Parser pp = new Parser();
				pp.setInputHTML(blocks[i].toHtml());
				NodeList bodys = pp.extractAllNodesThatMatch(filter);
				if (((Tag) bodys.elementAt(0)).getAttribute("class").contains(
						"nostar"))
					System.out.println("Review it!");
				else
					System.out.println("Reviews");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
