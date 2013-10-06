package com.hhgz.service;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class MyServerClient {
	public static void main(String[] args) {
		try {
			// 创建访问wsdl服务地址的url
			URL url = new URL("http://localhost:18888/ns?wsdl");
			// 通过qname指明服务的具体信息
			QName qname = new QName("http://service.hhgz.com/", "MyServiceImplService");
			// 创建服务
			Service service = Service.create(url, qname);

			// 实现接口
			IMyService ms = service.getPort(IMyService.class);
			// 得到结果
			int out = ms.add(10, 33);
			// 打印结果
			System.out.println(out);
			// 以上服务有问题，依然依赖于IMyServer接口
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
