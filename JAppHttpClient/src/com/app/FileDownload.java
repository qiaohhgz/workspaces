package com.app;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.impl.client.DefaultHttpClient;

public class FileDownload {
	public static final boolean PROXY = true;
	public static final boolean PROXY_HTTP = true;
	public static final String PROXY_HOST = "172.20.230.5";
	public static final int PROXY_PORT = 3128;
	public static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");

	public HttpClient getHttpClient() {
		HttpClient httpClient = new DefaultHttpClient();
		if (PROXY) {
			httpClient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, getProxy(PROXY_HTTP));
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

	public void download(String url, String path) throws URISyntaxException, ClientProtocolException, IOException {
		System.out.println("Begin download->>" + path);
		URI uri = new URI(url);
		// HttpPost post = new HttpPost(uri);
		// List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		// nvps.add(new BasicNameValuePair("action", "mobile"));
		// nvps.add(new BasicNameValuePair("mobile", "13120553042"));
		// nvps.add(new BasicNameValuePair("submit", "查询"));
		// post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		// HttpResponse response = getHttpClient().execute(post);

		File file = new File(path);
		FileOutputStream os = new FileOutputStream(file);
		HttpGet get = new HttpGet(uri);
		HttpResponse response = getHttpClient().execute(get);
		printContent(response.getEntity().getContent(), os);
	}

	public void printContent(InputStream is, OutputStream os) throws IOException {
		byte[] bs = new byte[1024];
		int size = 0;
		while ((size = is.read(bs)) != -1) {
			os.write(bs, 0, size);
		}
		os.flush();
		os.close();
		is.close();
	}

}
