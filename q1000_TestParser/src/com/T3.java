package com;

import java.net.URL;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class T3 {

	private static URLConnection connection;

	private static void connect(String urlString) {
		try {
			URL url = new URL(urlString);
			connection = url.openConnection();
			System.out.println(connection.getClass());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readContents() {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(connection
					.getInputStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.getProperties().put("http.proxySet", "true");
		System.getProperties().put("http.proxyHost", "172.20.230.5");
		System.getProperties().put("http.proxyPort", "3128");
		System.getProperties().put("https.proxySet", "true");
		System.getProperties().put("https.proxyHost", "172.20.230.5");
		System.getProperties().put("https.proxyPort", "3128");
		connect("http://www.baidu.com");
		readContents();
	}
}