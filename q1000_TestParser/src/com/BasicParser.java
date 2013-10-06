package com;

import org.htmlparser.util.NodeList;

public abstract class BasicParser {
	public static void applyProxy() {
		System.getProperties().put("http.proxySet", "true");
		System.getProperties().put("http.proxyHost", "172.20.230.5");
		System.getProperties().put("http.proxyPort", "3128");
		System.getProperties().put("https.proxySet", "true");
		System.getProperties().put("https.proxyHost", "172.20.230.5");
		System.getProperties().put("https.proxyPort", "3128");
	}

	public static boolean isEmpty(NodeList bodys) {
		return bodys == null || bodys.size() == 0;
	}
}
