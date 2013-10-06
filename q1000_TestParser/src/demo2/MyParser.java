package demo2;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class MyParser {

	public static final String[] urls = {
			"http://www.bengou.com/120314/xldsy12031416/1331713933631/1331713933631.html",
			"http://www.bengou.com/120314/xldsy12031416/1331713922707/1331713922707.html",
			"http://www.bengou.com/120314/xldsy12031416/1331713911240/1331713911240.html",
			"http://www.bengou.com/120314/xldsy12031416/1331713903393/1331713903393.html" };

	private NodeFilter filter = new CssSelectorNodeFilter("img#disp");
	private NodeReader nodeReader;

	public List<String> run(List<String> urls) {
		List<String> rs = new ArrayList<String>();
		int size = urls.size();
		for (int i = 0; i < size; i++) {
			List<String> readerResult = toParser(urls.get(i));
			if (null == readerResult) {
				System.out.println("not found img:" + urls.get(i));
			} else {
				rs.addAll(readerResult);
			}
		}
		return rs;
	}

	public List<String> run(String[] urls) {
		List<String> rs = new ArrayList<String>();
		for (int i = 0; i < urls.length; i++) {
			rs.add(urls[i]);
		}
		return run(rs);
	}

	public List<String> toParser(String urlStr) {
		List<String> rs = new ArrayList<String>();
		URL url = null;
		URLConnection openConnection = null;
		Parser p = null;
		NodeList nodeList = null;
		try {
			url = new URL(urlStr);
			openConnection = url.openConnection();
			p = new Parser(openConnection);
			nodeList = p.extractAllNodesThatMatch(filter);
			int size = nodeList.size();
			if (null != nodeList && size > 0) {
				for (int i = 0; i < size; i++) {
					Node n = nodeList.toNodeArray()[i];
					String result = nodeReader.reader(n);
					rs.add(result);
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void download(String urlStr, String fileName) {
		MutilDown.newInstance().download(urlStr, fileName);
	}

	public static void main(String[] args) {
		System.getProperties().put("http.proxySet", "true");
		System.getProperties().put("http.proxyHost", "172.20.230.5");
		System.getProperties().put("http.proxyPort", "3128");
		System.getProperties().put("https.proxySet", "true");
		System.getProperties().put("https.proxyHost", "172.20.230.5");
		System.getProperties().put("https.proxyPort", "3128");

		MyParser mp = new MyParser();

		mp.setNodeReader(new NodeReader() {
			@Override
			public String reader(Node n) {
				if (n instanceof Tag) {
					Tag tag = (Tag) n;
					String src = tag.getAttribute("src");
					if (null != src && src.length() > 0) {
						return src;
					}
				}
				return null;
			}
		});
		mp.setFilter(new CssSelectorNodeFilter("img#disp"));
		List<String> list = new ArrayList<String>();
		list.add(urls[0]);
		list.add(urls[1]);
		list.add(urls[2]);
		list.add(urls[3]);
		List<String> rs = mp.run(list);

		for (int i = 0; i < rs.size(); i++) {
			System.out.println(rs.get(i));
			String url = rs.get(i);
			String fileName = "D:\\manhua\\img" + i + ".jpg";
			mp.download(url, fileName);
			if (new File(fileName).exists()) {
				System.out.println("success download " + fileName);
			} else {
				System.out.println("error download " + fileName);
			}
			System.out.println("----------------------------------");
		}
	}

	public NodeFilter getFilter() {
		return filter;
	}

	public void setFilter(NodeFilter filter) {
		this.filter = filter;
	}

	public NodeReader getNodeReader() {
		return nodeReader;
	}

	public void setNodeReader(NodeReader nodeReader) {
		this.nodeReader = nodeReader;
	}
}
