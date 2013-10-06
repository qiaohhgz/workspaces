package com.ui.chat.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SendFileServerDemo {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(8888);
		System.out.println("start");
		Socket client = ss.accept();
		System.out.println("begin");
		InputStream is = client.getInputStream();
		FileOutputStream fos = new FileOutputStream(new File("temp.png"));
		byte[] bs = new byte[1024 * 1024];
		int size = 0;
		while ((size = is.read(bs)) != -1) {
			System.out.println(size + " " + bs.length);
			fos.write(bs, 0, size);
			fos.flush();
		}
		fos.close();
		is.close();
		System.out.println("ok");
	}
}
