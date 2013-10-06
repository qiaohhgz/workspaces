package com.app.html.parser;

import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;

public class FocusNewsParser extends BaseParser {

	public static NodeFilter blockFilter = new CssSelectorNodeFilter("div[class~=focusnews] > dl > dd");
	public static NodeFilter hotFilter = new CssSelectorNodeFilter("a");
	public static NodeFilter titleFilter = new CssSelectorNodeFilter("a");
	public static NodeFilter titleUrlFilter = new CssSelectorNodeFilter("h3[class=t]>a");
	public static NodeFilter contentFilter = new CssSelectorNodeFilter("td[class=f]");

	public String getTitle(Parser parser) throws Exception {
		String title = "";
		parser.reset();
		Node[] ns = parser.extractAllNodesThatMatch(titleFilter).toNodeArray();
		for (int i = 0; i < ns.length; i++) {
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
				content = html.substring(begin, end);
			}
		}
		return content;
	}

	public List<News> getHotnews(Parser parser) throws Exception {
		List<News> list = new ArrayList<News>();
		parser.reset();
		Node[] nodes = parser.extractAllNodesThatMatch(hotFilter).toNodeArray();
		for (int i = 0; i < nodes.length; i++) {
			Node node = nodes[i];
			String link = "";
			String title = "";
			String imageURL = "";
			if (node instanceof LinkTag) {
				link = ((LinkTag) node).getLink();
				Node[] childNodes = node.getChildren().toNodeArray();
				for (int j = 0; j < childNodes.length; j++) {
					Node n = childNodes[j];
					if (n instanceof ImageTag) {
						imageURL = ((ImageTag) n).getImageURL();
					}
					if (n instanceof TextNode) {
						title = n.toPlainTextString().trim();
					}
				}
			}
			News n = new News();
			n.setTitleUrl(link);
			n.setTitle(title);
			n.setImageUrl(imageURL);
		}
		return list;
	}

	ParserTemplate parserTemplate = new ParserTemplate() {
		public List<News> doSearch(Parser parser) throws Exception {
			List<News> list = new ArrayList<News>();
			parser.reset();
			String blockHtml = parser.extractAllNodesThatMatch(blockFilter).toHtml();
			parser.setInputHTML(blockHtml);
			list.addAll(getHotnews(parser));
			return list;
		}
	};

	public static void main(String[] args) throws Exception {
		FocusNewsParser focusNewsParser = new FocusNewsParser();
		List<News> list = focusNewsParser.getSearchResult(focusNewsParser.parserTemplate);

	}

	@Override
	public String getEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasApplyProxy() {
		// TODO Auto-generated method stub
		return false;
	}
}
