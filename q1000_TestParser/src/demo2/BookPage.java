package demo2;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.InputTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class BookPage implements IBookPage {

	private NodeFilter titleFilter = new CssSelectorNodeFilter("h1#title");
	private NodeFilter blockFilter = new CssSelectorNodeFilter("div#color");
	private NodeFilter imgUrlFilter = new CssSelectorNodeFilter("img#disp");
	private NodeFilter thePageFilter = new CssSelectorNodeFilter("input#thePage");
	private NodeFilter maxPageFilter = new CssSelectorNodeFilter("input#maxPage");
	private String[] pictree;
	private String nextPage;
	private int nextPageLimit = 10;

	private static final String home = "http://www.bengou.com/120215/mlyz12021518/1329301292548/1329301292548.html";
	private int thePage = 1;
	private int maxPage;

	public static void main(String[] args) {
		try {
			List<Book> results = new BookPage().parseResults(home);
			for (Book b : results) {
				new BookPage().download(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void download(Book b) {
		String dir = "F:\\images\\";
		String fileName = b.getTitle();
		String type = b.getImageUrl().substring(b.getImageUrl().lastIndexOf("."));
		String location = dir + fileName + type;
		System.out.println(location);
		MutilDown.newInstance().download(b.getImageUrl(), location);
	}

	public BookPage() {
		System.getProperties().put("http.proxySet", "true");
		System.getProperties().put("http.proxyHost", "172.20.230.5");
		System.getProperties().put("http.proxyPort", "3128");
		System.getProperties().put("https.proxySet", "true");
		System.getProperties().put("https.proxyHost", "172.20.230.5");
		System.getProperties().put("https.proxyPort", "3128");
	}

	@Override
	public List<Book> parseResults(String bookUrl) throws Exception {
		List<Book> books = new ArrayList<Book>();
		nextPage = bookUrl;
		int nextPageNumber = 0;
		while (hasMorePage() && nextPageNumber <= nextPageLimit) {
			nextPageNumber++;
			URL url = new URL(nextPage);
			URLConnection openConnection = url.openConnection();
			Parser parser = new Parser(openConnection);

			// pictree = getPictree(parser);
			// thePage = getThePage(parser);
			// maxPage = getMaxPage(parser);
			nextPage = getPage(parser);

			books.addAll(parseOnePage(parser));

		}
		return books;
	}

	private boolean hasMorePage() {
		return (nextPage != null);
	}

	private boolean isNotEmpty(NodeList nodeList) {
		if (null != nodeList && nodeList.size() > 0)
			return true;
		return false;
	}

	@Override
	public List<Book> parseOnePage(Parser parser) throws ParserException {
		List<Book> books = new ArrayList<Book>();
		parser.reset();
		NodeList nodeList = parser.extractAllNodesThatMatch(blockFilter);
		Book book = null;
		if (null != nodeList && nodeList.size() > 0) {
			Parser p = new Parser();
			book = new Book();
			p.setInputHTML(nodeList.elementAt(0).toHtml());

			book.setImageUrl(getImgSrc(p));
			book.setTitle(getTitle(parser));

			books.add(book);
		}
		return books;
	}

	private String[] getPictree(Parser parser) throws ParserException {
		return null;
	}

	@Override
	public String getImgSrc(Parser parser) throws ParserException {
		String src = null;
		parser.reset();
		try {
			NodeList imgs = parser.extractAllNodesThatMatch(imgUrlFilter);
			if (imgs.size() > 0) {
				Node n = imgs.elementAt(0);
				if (n instanceof Tag) {
					if (n instanceof ImageTag) {
						ImageTag imageTag = (ImageTag) n;
						src = imageTag.getImageURL();
					}
				}
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return src;
	}

	@Override
	public String getPage(Parser parser) throws ParserException {
		parser.reset();
		String nextPage = "";
		String base = home.substring(0, home.lastIndexOf("/") + 1);
		if ((thePage + 1) > maxPage) {
			nextPage = null;
		} else {
			if (pictree.length >= thePage) {
				nextPage = base + pictree[pictree.length];
			} else {
				nextPage = base + pictree[thePage];
			}
		}
		return nextPage;
	}

	public String getTitle(Parser parser) throws ParserException {
		String title = null;
		parser.reset();
		NodeList nodeList = parser.extractAllNodesThatMatch(titleFilter);
		if (isNotEmpty(nodeList)) {
			title = nodeList.elementAt(0).toPlainTextString().trim();
		}
		return title;
	}

	public int getThePage(Parser parser) throws ParserException {
		int page = 0;
		parser.reset();
		NodeList nodeList = parser.extractAllNodesThatMatch(thePageFilter);
		if (isNotEmpty(nodeList)) {
			Node node = nodeList.elementAt(0);
			if (node instanceof InputTag) {
				InputTag input = (InputTag) node;
				String value = input.getAttribute("value");
				page = Integer.parseInt(value);
			}
		}
		return page;
	}

	public int getMaxPage(Parser parser) throws ParserException {
		int page = 0;
		parser.reset();
		NodeList nodeList = parser.extractAllNodesThatMatch(maxPageFilter);
		if (isNotEmpty(nodeList)) {
			Node node = nodeList.elementAt(0);
			if (node instanceof InputTag) {
				InputTag input = (InputTag) node;
				String value = input.getAttribute("value");
				page = Integer.parseInt(value);
			}
		}
		return page;
	}
}
