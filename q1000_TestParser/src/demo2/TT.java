package demo2;

import java.net.URL;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.CssSelectorNodeFilter;

public class TT {
	public static void main(String[] args) throws Exception {
		System.getProperties().put("http.proxySet", "true");
		System.getProperties().put("http.proxyHost", "172.20.230.5");
		System.getProperties().put("http.proxyPort", "3128");

		URL url = new URL("http://www.bengou.com/080819/hyrz008081909/1336571498277/1336571498277.html");
		// http://www.bengou.com/120314/qwlu12031415/1331709980205/1331709980205.html
		Parser p = new Parser(url.openConnection());
		CssSelectorNodeFilter filter = new CssSelectorNodeFilter("img#disp");
		Node[] array = p.extractAllNodesThatMatch(filter).toNodeArray();
		for (int i = 0; i < array.length; i++) {
			if (array[i] instanceof Tag) {
				Tag lt = (Tag) array[i];
				System.out.println(lt.getAttribute("src"));
			}
		}
	}
}
