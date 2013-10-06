package demo2;

import java.net.URL;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.CssSelectorNodeFilter;

public class T {
	public static void main(String[] args) throws Exception {
		String html = "<div id=\"mhlist\" class=\"subsrbelist center\"><ul><li><h3><a target=\"_blank\" href=\"1329301290378/1329301290378.html\" title=\"第2话\"></h3><img width=\"16\" height=\"10"
				+ "src=\"http://static.bengou.com/images2/new08.gif\"></li><li><h3><a target=\"_blank\" href=\"1329301292548/1329301292548.html\" title=\"第1话\">"
				+ "</h3></li></ul><br class=\"clearall\"></div>";
		System.getProperties().put("http.proxySet", "true");
		System.getProperties().put("http.proxyHost", "172.20.230.5");
		System.getProperties().put("http.proxyPort", "3128");
		System.getProperties().put("https.proxySet", "true");
		System.getProperties().put("https.proxyHost", "172.20.230.5");
		System.getProperties().put("https.proxyPort", "3128");
		String base = "http://www.bengou.com/120314/qwlu12031415/";
		URL url = new URL("http://www.bengou.com/120314/qwlu12031415/index.html");
		Parser p = new Parser(url.openConnection());
		//p.setInputHTML(html);
		CssSelectorNodeFilter filter = new CssSelectorNodeFilter("div#mhlist a");
		Node[] array = p.extractAllNodesThatMatch(filter).toNodeArray();
		for (int i = 0; i < array.length; i++) {
			if(array[i] instanceof Tag){
				Tag lt =(Tag) array[i]; 
				System.out.println(base + lt.getAttribute("href"));
			}
		}
	}
}
