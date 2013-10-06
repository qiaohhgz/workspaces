package com.oneonefive;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.BasicParser;

public class OneOneFive extends BasicParser {
	public static void main(String[] args) throws ParserException {
		OneOneFive m = new OneOneFive();
		Parser p = getParser();
//		m.login(p);
//		m.index(p);
		m.login2(p);
	}

	public static Parser getParser() throws ParserException {
		applyProxy();
		Parser p = new Parser();
		p.setEncoding("UTF-8");
		return p;
	}

	public boolean login(Parser p) throws ParserException {
		String url = "https://passport.115.com/?ac=login&login[account]=qsj695546958@163.com&login[passwd]=q7837793";
		final String resultUrl = url;
		System.out.println(resultUrl);
		p.setURL(resultUrl);
		NodeFilter filter = new CssSelectorNodeFilter("body");
		NodeList nodeList = p.parse(filter);
		if (!isEmpty(nodeList)) {
			// System.out.println(nodeList.elementAt(0).toHtml());
			System.out.println("login");
			return true;
		} else {
			System.out.println("login empty");
			return false;
		}
	}

	public boolean login2(Parser p) throws ParserException {
		String url = "https://passport.115.com/?ac=login&login[account]=qsj695546958@163.com&login[passwd]=q7837793";
		final String resultUrl = url;
		System.out.println(resultUrl);
		p.setURL(resultUrl);
		NodeFilter filter = new CssSelectorNodeFilter("body");
		NodeList nodeList = p.parse(filter);
		if (!isEmpty(nodeList)) {
			System.out.println("login");
			p.setURL("http://www.115.com");
			NodeFilter f = new CssSelectorNodeFilter("div[class=user-space]");
			NodeList list = p.parse(f);
			if (!isEmpty(list)) {
				System.out.println("ok");
			} else {
				System.out.println("index empty");
			}
			return true;
		} else {
			System.out.println("login empty");
			return false;
		}
	}

	public void index(Parser p) throws ParserException {
		String url = "http://www.115.com";
		final String resultUrl = url;
		System.out.println(resultUrl);
		p.setURL(resultUrl);
		NodeFilter filter = new CssSelectorNodeFilter("div[class=user-space]");
		NodeList nodeList = p.parse(filter);
		if (!isEmpty(nodeList)) {
			System.out.println(nodeList.elementAt(0).toHtml());
		} else {
			System.out.println("index empty");
		}
	}
}
