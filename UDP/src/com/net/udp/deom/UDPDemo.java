package com.net.udp.deom;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import org.apache.log4j.Logger;

/**
 * @author jim_qiao
 * 
 */
public class UDPDemo {
	private Logger log = Logger.getLogger(UDPDemo.class);
	private DatagramSocket socket;

	public static void main(String[] args) {
		UDPDemo udpDemo = new UDPDemo();
		udpDemo.send("你好");
	}

	public UDPDemo() {
		Accept ac = new Accept();
		Thread act = new Thread(ac);
		act.start();

		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void send(String text) {
		try {
			byte[] bs = text.getBytes();
			socket.send(new DatagramPacket(bs, bs.length, InetAddress.getByName("localhost"), 6666));
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		}
	}

	// 接收
	class Accept implements Runnable {
		private DatagramSocket socket;
		private DatagramPacket p = new DatagramPacket(new byte[1024], 1024);

		@Override
		public void run() {
			try {
				socket = new DatagramSocket(6666);
				log.debug("start server");
			} catch (SocketException e) {
				e.printStackTrace();
			}
			while (true) {
				try {
					log.debug("receive");
					socket.receive(p);
					log.debug("a package");
					String str = new String(p.getData(), 0, p.getLength());
					System.out.println(str);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
