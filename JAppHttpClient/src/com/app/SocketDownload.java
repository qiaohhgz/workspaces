package com.app;


import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URI;

public class SocketDownload {
	public static void main(String[] args) throws Exception {
		new SocketDownload().download("http://pic.zhuzhu.cc:8011/pic/uploadimg/2011-10/120_85.jpg");
	}

	public void download(String url) throws Exception {
		URI uri = new URI(url);
		
		Socket socket = new Socket();

		InetAddress[] allByName = Inet4Address.getAllByName(url); 
		
		for (InetAddress address : allByName) {
			System.out.println(address.getHostAddress());
			System.out.println(address.getHostName());
		}
	}
}
