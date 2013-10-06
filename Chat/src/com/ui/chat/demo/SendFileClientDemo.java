package com.ui.chat.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SendFileClientDemo {
	public static void main(String[] args) throws IOException {
		Socket server = new Socket("172.20.230.90", 8888);
		System.out.println("connect");
		OutputStream os = server.getOutputStream();
		FileInputStream fis = new FileInputStream(new File("image.png"));
		byte[] bs = new byte[1024];
		System.out.println("begin");
		int size = 0;
		while ((size = fis.read(bs)) != -1) {
			os.write(bs, 0, size);
			os.flush();
		}
		os.close();
		fis.close();
		System.out.println("ok");
	}

}
