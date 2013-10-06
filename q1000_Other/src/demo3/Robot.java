//package demo3;
//
//import java.io.IOException;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Queue;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//public class Robot {
//
//	// robot url
//	private List<String> urlList;
//	// cache url
//	private Queue<String> urlQueue;
//	// define Host
//	public final static String HOST = "debugs.tk";
//
//	// constructor
//	public Robot() {
//		super();
//		// initialization robot's member
//		setUrlList(new LinkedList<String>());
//		setUrlQueue(new LinkedList<String>());
//	}
//
//	// url
//	public List<String> getUrlList() {
//		return urlList;
//	}
//
//	public void setUrlList(List<String> urlList) {
//		this.urlList = urlList;
//	}
//
//	// cache
//	public Queue<String> getUrlQueue() {
//		return urlQueue;
//	}
//
//	public void setUrlQueue(Queue<String> urlQueue) {
//		this.urlQueue = urlQueue;
//	}
//
//	// Legal link
//	private boolean isURL(String url) {
//		try {
//			// judge url
//			Pattern pattern = Pattern.compile("^[a-zA-z]+://[^\\s]*");
//			Matcher matcher = pattern.matcher(url);
//			if (matcher.matches()) {
//				return true;
//			} else {
//				return false;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
//
//	// whether the url is belong to host
//	public static boolean isHost(String url) {
//		return url.contains(HOST);
//	}
//
//	// travel all url
//	public void traverse(String seed) {
//
//		for (this.getUrlQueue().add(seed); !this.getUrlQueue().isEmpty();) {
//			boolean flag = true;
//			Document document = null;
//			try {
//				document = Jsoup.connect(seed).timeout(5000).get();
//			} catch (IOException e) {
//				e.printStackTrace();
//				// whether connect success
//				flag = false;
//			}
//			// whether connect success,then select a tag
//			// add these aTag into queue
//			if (flag) {
//        // get url
//				Elements elements = document.select("a[href]");
//				for (Element e : elements) {
//					String s = e.attr("abs:href");
//					// Legal link and belong host
//					// and url not in list
//					// then add it
//					if (isURL(s) && s.contains(HOST)
//							&& (!getUrlQueue().contains(s))
//							&& (!getUrlList().contains(s))) {
//						this.getUrlQueue().add(s);
//					}
//				}
//			}
//			// get head of queue
//			// and set it seed
//			// travel seed it again
//			seed = this.getUrlQueue().poll();
//			this.getUrlList().add(seed);
//			// show information
//			// System.out.println("SIZE:" 
//			// + this.getUrlQueue().size() + "---"
//			// + seed + " connect!");
//		}
//	}
//
//	// public static void main(String[] args) {
//	// Robot robot = new Robot();
//	// robot.traverse("http://debugs.tk");
//	// List<String> list = robot.getUrlList();
//	// for (String s : list) {
//	// System.out.println(s);
//	// }
//	// }
//
//}