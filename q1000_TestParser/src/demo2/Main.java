package demo2;

import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Tag;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.tags.LinkTag;

public class Main {
	// 笨狗漫画
	// "http://www.bengou.com/120314/xldsy12031416/1331713933631/1331713933631.html"
	// "http://www.bengou.com/120314/xldsy12031416/1331713922707/1331713922707.html"
	// "http://www.bengou.com/120314/xldsy12031416/1331713911240/1331713911240.html"
	// "http://www.bengou.com/120314/xldsy12031416/1331713903393/1331713903393.html"

	// http://www.bengou.com/xemhzt/
	// <ul class="mod-pic">
	// <li><a
	// href="http://www.bengou.com/120215/mlyz12021518/index.html"</a></li>
	//
	// <div id="mhlist" class="subsrbelist center">
	// <ul>
	// <li>
	// <h3>
	// <a target="_blank" href="1329301290378/1329301290378.html" title="第2话">
	// </h3>
	// <img width="16" height="10"
	// src="http://static.bengou.com/images2/new08.gif">
	// </li>
	// <li>
	// <h3>
	// <a target="_blank" href="1329301292548/1329301292548.html" title="第1话">
	// </h3>
	// </li>
	// </ul>
	// <br class="clearall">
	// </div>
	private String homeHref;
	private NodeFilter homeHrefFilter;
	private NodeReader homeNodeReader;
	private List<String> bookHrefs = new ArrayList<String>();
	private NodeFilter bookHrefFilter;
	private NodeReader bookNodeReader;
	private List<String> zhangHrefs = new ArrayList<String>();
	private NodeFilter zhangSrcFilter;
	private NodeReader zhangNodeReader;
	private List<String> imgHrefs = new ArrayList<String>();

	private String base = "http://www.bengou.com/120314/qwlu12031415/";

	public Main init() {
		homeHref = "http://www.bengou.com/xemhzt/";
		homeHrefFilter = new CssSelectorNodeFilter("ul[class=mod-pic] a");
		bookHrefFilter = new CssSelectorNodeFilter("div#mhlist>ul>li>h3>a");
		zhangSrcFilter = new CssSelectorNodeFilter("img#disp");

		homeNodeReader = new NodeReader() {
			@Override
			public String reader(Node n) {
				if (n instanceof LinkTag) {
					LinkTag t = (LinkTag) n;
					String href = t.getLink();
					System.out.println("homeHref:" + href);
					return href;
				}
				return null;
			}
		};
		bookNodeReader = new NodeReader() {
			@Override
			public String reader(Node n) {

				if (n instanceof LinkTag) {
					LinkTag t = (LinkTag) n;
					String href = t.getLink();
					System.out.println("bookHref:" + base + href);
					return base + href;
				}
				return null;
			}
		};
		zhangNodeReader = new NodeReader() {
			@Override
			public String reader(Node n) {
				if (n instanceof Tag) {
					Tag tag = (Tag) n;
					String src = tag.getAttribute("src");
					if (null != src && src.length() > 0) {
						System.out.println("imgSrc:" + src);
						return src;
					}
				}
				return null;
			}
		};
		return this;
	}

	public void run() {
		MyParser mp = new MyParser();
		mp.setFilter(homeHrefFilter);
		mp.setNodeReader(homeNodeReader);
		bookHrefs = mp.run(new String[] { homeHref });
		hr();

		mp.setFilter(bookHrefFilter);
		mp.setNodeReader(bookNodeReader);
		zhangHrefs = mp.run(bookHrefs);
		hr();

		mp.setFilter(zhangSrcFilter);
		mp.setNodeReader(zhangNodeReader);
		imgHrefs = mp.run(zhangHrefs);

		// List<String> rs = imgHrefs;
		// for (int i = 0; i < rs.size(); i++) {
		// System.out.println(rs.get(i));
		// String url = rs.get(i);
		// String fileName = "D:\\manhua\\img" + i + ".jpg";
		// mp.download(url, fileName);
		// if (new File(fileName).exists()) {
		// System.out.println("success download " + fileName);
		// } else {
		// System.out.println("error download " + fileName);
		// }
		// System.out.println("----------------------------------");
		// }
	}

	public void hr() {
		System.out.println("-----------------------------");
	}

	public static void main(String[] args) {
		System.getProperties().put("http.proxySet", "true");
		System.getProperties().put("http.proxyHost", "172.20.230.5");
		System.getProperties().put("http.proxyPort", "3128");
		System.getProperties().put("https.proxySet", "true");
		System.getProperties().put("https.proxyHost", "172.20.230.5");
		System.getProperties().put("https.proxyPort", "3128");

		new Main().init().run();
	}
}
