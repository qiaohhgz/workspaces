package com.app.html.parser;

import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.Parser;

public abstract class BaseParser {

	public void applyProxy() {
		if (hasApplyProxy()) {
			System.getProperties().put("http.proxySet", "true");
			System.getProperties().put("http.proxyHost", "172.20.230.5");
			System.getProperties().put("http.proxyPort", "3128");
			System.getProperties().put("https.proxySet", "true");
			System.getProperties().put("https.proxyHost", "172.20.230.5");
			System.getProperties().put("https.proxyPort", "3128");
		}
	}

	public abstract String getEncoding();

	public List getSearchResult(ParserTemplate parserTemplate) throws Exception {
		applyProxy();
		URLConnection urlConn = new URL(getUrl()).openConnection();
		Parser parser = new Parser(urlConn);
		parser.setEncoding(getEncoding());
		return parserTemplate.doSearch(parser);
	}

	public abstract String getUrl();

	public abstract boolean hasApplyProxy();

	public boolean isNotEmpty(List list) {
		return (list != null && list.size() > 0);
	}

	public boolean isNotEmpty(Node[] ns) {
		return (ns != null && ns.length > 0);
	}

}
