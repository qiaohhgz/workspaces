package com.hhgz.service;

import javax.xml.ws.Endpoint;

public class MyServer {
	public static void main(String[] args) {
		String address = "http://localhost:18888/ns";
		Endpoint.publish(address, new MyServiceImpl());
	}
}
