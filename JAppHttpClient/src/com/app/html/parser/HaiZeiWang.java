package com.app.html.parser;

import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;

import com.app.FileDownload;

public class HaiZeiWang extends BaseParser {
	private static final String encoding = "gb2312";
	private static final String urlBase = "http://www.zhuzhu.cc";
	private static final String url = urlBase + "/dh/haizeiwang.html";

	public static void main(String[] args) throws Exception {
		HaiZeiWang hzw = new HaiZeiWang();
		List list = hzw.getSearchResult(hzw.parserTemplate);
	}

	ParserTemplate parserTemplate = new ParserTemplate() {

		public List doSearch(Parser parser) throws Exception {
			CssSelectorNodeFilter ulFilter = getCssFilter("div[class~=videolist] ul a");
			Node[] nodes = parser.extractAllNodesThatMatch(ulFilter).toNodeArray();
			for (Node node : nodes) {
				if (node instanceof LinkTag) {
					LinkTag link = (LinkTag) node;
					System.out.println("href:" + urlBase + link.getAttribute("href"));
					System.out.println("title:" + link.getAttribute("title"));
					System.out.println("innerHTML:" + link.toPlainTextString());
				}
			}

			parser.reset();
			CssSelectorNodeFilter lis = getCssFilter("div[class~=random] ul li");
			nodes = parser.extractAllNodesThatMatch(lis).toNodeArray();
			FileDownload fileDownload = new FileDownload();
			for (Node node : nodes) {
				Node aChild = node.getFirstChild();
				if (aChild instanceof LinkTag) {
					String href = ((LinkTag) aChild).getLink();
					href = href.substring(href.lastIndexOf("/") + 1, href.lastIndexOf("."));
					Node imgChild = aChild.getFirstChild();
					if (imgChild instanceof ImageTag) {
						ImageTag img = (ImageTag) imgChild;
						String imageURL = img.getImageURL();
						String endName = imageURL.substring(imageURL.lastIndexOf("."));
						String alt = img.getAttribute("alt");
						String path = href + endName;
						System.out.println(alt);
						System.out.println(endName);
						System.out.println(imageURL);
						fileDownload.download(imageURL, "download\\" + path);
					}
				}
			}
			return null;
		}

		public CssSelectorNodeFilter getCssFilter(String selector) {
			return new CssSelectorNodeFilter(selector);
		}
	};

	@Override
	public String getEncoding() {
		return encoding;
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public boolean hasApplyProxy() {
		return true;
	}
}
