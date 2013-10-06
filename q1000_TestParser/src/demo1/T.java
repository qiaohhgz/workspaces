package demo1;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.CssSelectorNodeFilter;

import util.YBStringUtils;

public class T {
	public static void applyProxy() {
		System.getProperties().put("http.proxySet", "true");
		System.getProperties().put("http.proxyHost", "172.20.230.5");
		System.getProperties().put("http.proxyPort", "3128");
		System.getProperties().put("https.proxySet", "true");
		System.getProperties().put("https.proxyHost", "172.20.230.5");
		System.getProperties().put("https.proxyPort", "3128");
	}

	public static final String urlStr = "http://www.baidu.com/s?";
	public static final String bg2312 = "gb2312";
	public static final String keyword = "新闻";
	public static NodeFilter blockFilter = new CssSelectorNodeFilter("table[class=result]");
	public static NodeFilter titleFilter = new CssSelectorNodeFilter("h3[class=t]");
	public static NodeFilter titleUrlFilter = new CssSelectorNodeFilter("h3[class=t]>a");
	public static NodeFilter contentFilter = new CssSelectorNodeFilter("td[class=f]");

	public String getURL(String wd) throws Exception {
		return "wd=" + URLEncoder.encode(wd, bg2312);
	}

	public String getTitle(Parser parser) throws Exception {
		String title = "";
		parser.reset();
		Node[] ns = parser.extractAllNodesThatMatch(titleFilter).toNodeArray();
		if (isNotEmpty(ns)) {
			title = ns[0].toPlainTextString().trim();
		}
		return title;
	}

	public String getTitleURL(Parser parser) throws Exception {
		String titleUrl = "";
		parser.reset();
		Node[] ns = parser.extractAllNodesThatMatch(titleUrlFilter).toNodeArray();
		if (isNotEmpty(ns)) {
			if (ns[0] instanceof Tag) {
				Tag tag = (Tag) ns[0];
				titleUrl = tag.getAttribute("href").trim();
			}
		}
		return titleUrl;
	}

	public String getContent(Parser parser) throws Exception {
		String content = "";
		parser.reset();
		Node[] ns = parser.extractAllNodesThatMatch(contentFilter).toNodeArray();
		if (isNotEmpty(ns)) {
			String html = ns[0].toHtml().trim();
			if (null != html && html.length() > 0 && -1 != html.indexOf("<font") && html.indexOf("<span") != -1) {
				int begin = html.indexOf("<font");
				int end = begin + html.substring(begin).indexOf("<span");
				content = YBStringUtils.toPlainText(html.substring(begin, end));
			}
		}
		return content;
	}

	public static boolean isNotEmpty(Node[] ns) {
		return (ns != null && ns.length > 0);
	}

	public static boolean isNotEmpty(List list) {
		return (list != null && list.size() > 0);
	}

	public List<News> doSearch(String url, String keyword) throws Exception {
		List<News> newsList = new ArrayList<News>();
		URLConnection urlConn = new URL(url + getURL(keyword)).openConnection();
		Parser parser = new Parser(urlConn);
		Node[] blockNodes = parser.extractAllNodesThatMatch(blockFilter).toNodeArray();
		for (int i = 0; i < blockNodes.length; i++) {
			parser.setInputHTML(blockNodes[i].toHtml());
			News n = new News();
			n.setTitle(getTitle(parser));
			n.setTitleUrl(getTitleURL(parser));
			n.setContent(getContent(parser));

			newsList.add(n);
		}
		return (isNotEmpty(newsList)) ? (newsList) : (null);
	}

	public static void main(String[] args) throws Exception {
		applyProxy();
		T t = new T();
		List<News> ns = t.doSearch(urlStr, keyword);
		int i = 1;
		for (News n : ns) {
			System.out.println("================ " + i++);
			System.out.println(n.getTitle());
			System.out.println(n.getTitleUrl());
			System.out.println(n.getContent());
		}
	}

	class News {
		private String title;
		private String titleUrl;
		private String content;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getTitleUrl() {
			return titleUrl;
		}

		public void setTitleUrl(String titleUrl) {
			this.titleUrl = titleUrl;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
	}
}
