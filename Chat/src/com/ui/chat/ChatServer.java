package com.ui.chat;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

/**
 * @author jim_qiao
 * 
 */
public class ChatServer {
	private List<ClientListener> clients = new ArrayList<ClientListener>();

	private boolean stated;
	private ServerSocket ss;
	private JTextArea debugArea;

	public static void main(String[] args) {
		new ChatServer(null).start();
	}

	public ChatServer(JTextArea debugArea) {
		this.setDebugArea(debugArea);
		launchFrame();
	}

	public void launchFrame() {
	}

	public void start() {
		try {
			ss = new ServerSocket(8888);
			stated = true;
		} catch (BindException ex) {
			printDebug("端口8888被占用");
			printDebug(ex);
			System.exit(0);
		} catch (IOException ex) {
			System.exit(0);
		}
		printDebug("search client ...");
		try {
			while (stated) {
				Socket s = ss.accept();
				printDebug("a client connected");
				ClientListener ct = new ClientListener(s, clients);
				clients.add(ct);
				new Thread(ct).start();
			}
		} catch (Exception ex) {
			printDebug(ex);
		} finally {
			disConnect();
		}
	}

	public void printDebug(String debug) {
		System.out.println(debug);
	}

	public void printDebug(Exception ex) {
		ex.printStackTrace();
	}

	public void disConnect() {
		try {
			stated = false;
			if (ss != null) {
				ss.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public JTextArea getDebugArea() {
		return debugArea;
	}

	public void setDebugArea(JTextArea debugArea) {
		this.debugArea = debugArea;
	}
}
