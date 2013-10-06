package com.app;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Hello world!
 * 
 */
public class AppDemo {
	public static String URL = "http://127.0.0.1:8080/test/h?param=aaaaaaaaaaaaaaa";
	public static final String PROXY_HOST = "172.20.230.5";
	public static final int PROXY_PORT = 3128;

	public static void applyProxy() {
		System.getProperties().put("http.proxySet", "true");
		System.getProperties().put("http.proxyHost", "172.20.230.5");
		System.getProperties().put("http.proxyPort", "3128");
		System.getProperties().put("https.proxySet", "true");
		System.getProperties().put("https.proxyHost", "172.20.230.5");
		System.getProperties().put("https.proxyPort", "3128");
	}

	public void requestPost() throws URISyntaxException, IllegalStateException, IOException {
		System.out.println("Hello World!");
		URI uri = new URI("http://avatar.profile.csdn.net/F/E/E/2_dracularking.jpg");
		HttpPost post = new HttpPost(uri);
		HttpResponse response = getHttpClient(true).execute(post);
		HttpEntity entity = response.getEntity();
		InputStream inputStream = entity.getContent();
		String name = entity.getContentType().getValue();
		System.out.println(name);
		FileOutputStream os = new FileOutputStream(new File("a.jpg"));
		byte[] bs = new byte[1024];
		while (inputStream.read(bs) != -1) {
			System.out.println(new String(bs, 0, bs.length));
			os.write(bs, 0, bs.length);
		}
		os.close();
		inputStream.close();
	}

	public void requestGet() throws URISyntaxException, IllegalStateException, IOException {
		System.out.println("Hello World!");
		URI uri = new URI("http://avatar.profile.csdn.net/F/E/E/2_dracularking.jpg");
		HttpGet get = new HttpGet(uri);
		HttpResponse response = getHttpClient(true).execute(get);
		HttpEntity entity = response.getEntity();
		InputStream inputStream = entity.getContent();
		String name = entity.getContentType().getValue();
		System.out.println(name);
		FileOutputStream os = new FileOutputStream(new File("a.jpg"));
		byte[] bs = new byte[512];
		while (inputStream.read(bs) != -1) {
			os.write(bs, 0, bs.length);
		}
		os.close();
		inputStream.close();
	}

	public static void main(String[] args) throws MalformedURLException, IOException, URISyntaxException {
		// new AppDemo().requestGet();
		new Thread(new Runnable() {
			String url = URL;
			HttpClient httpClient = new AppDemo().getHttpClient(true);

			public void run() {
				while (true) {
					try {
						HttpPost post = new HttpPost(url);
						HttpResponse response = httpClient.execute(post);
						printContent(response.getEntity().getContent());
						url += "aaaaa";
					} catch (Exception e) {
						e.printStackTrace();// 8045
						break;
					}
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			String url = URL;
			HttpClient httpClient = new AppDemo().getHttpClient(false);

			public void run() {
				while (true) {
					try {
						HttpGet get = new HttpGet(url);
						HttpResponse response = httpClient.execute(get);
						printContent(response.getEntity().getContent());
						url += "aaaaa";
					} catch (Exception e) {
						e.printStackTrace();// 8045
						break;
					}
				}
			}
		}).start();
	}

	public HttpClient getHttpClient(boolean hasProxy) {
		HttpClient httpClient = new DefaultHttpClient();
		if (hasProxy) {
			httpClient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, getProxy(true));
		}
		return httpClient;
	}

	public HttpHost getProxy(boolean isHttp) {
		if (isHttp) {
			return new HttpHost(PROXY_HOST, PROXY_PORT, "http");
		} else {
			return new HttpHost(PROXY_HOST, PROXY_PORT, "https");
		}
	}

	public static void printContent(InputStream is) throws IOException {
		byte[] bs = new byte[1024];
		int size = 0;
		while ((size = is.read(bs)) != -1) {
			System.out.println(new String(bs, 0, size, Charset.availableCharsets().get("GB2312")));
		}
	}
}
