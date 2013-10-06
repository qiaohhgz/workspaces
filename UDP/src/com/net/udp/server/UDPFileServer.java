package com.net.udp.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import com.net.udp.client.UDPFileReceiver;

public class UDPFileServer {
	private int port = 1220;
	private DatagramSocket socket;

	public UDPFileServer() throws SocketException {
		socket = new DatagramSocket();
		socket.setSoTimeout(60000);
		System.out.println("start a server");
	}

	public void service() throws IOException {
		InputStream is = this.getClass().getResourceAsStream("lbjn.jpg");
		byte[] buffer = new byte[UDPFileReceiver.PACKAGE_SIZE];
		int len = 0;
		int count = 0;
		while ((len = is.read(buffer)) != -1) {
			count++;
			DatagramPacket packet = new DatagramPacket(buffer, len, InetAddress.getByName("localhost"), port);
			socket.send(packet);
		}
		byte[] bs = "end".getBytes();
		DatagramPacket packet = new DatagramPacket(bs, bs.length, InetAddress.getByName("localhost"), port);
		socket.send(packet);
		System.out.println("send (" + count + ") package");
		socket.close();
	}

	public static void main(String[] args) throws SocketException, IOException {
		new UDPFileServer().service();
	}
}
