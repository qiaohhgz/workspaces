package com.ui.chat.main;

import java.io.File;
import java.io.IOException;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ui.chat.bean.CustomerBean;
import com.ui.chat.bean.MsgBean;
import com.ui.chat.bean.MsgType;

public class Chat {
	private static final Logger log = Logger.getLogger(MsgBean.class);
	public static final int DEFAULT_TEXT_PORT = 8888;
	public static final int DEFAULT_FILE_PORT = 8889;
	private List<CustomerBean> customerList = new ArrayList<CustomerBean>();
	private List<InetAddress> addressList = new ArrayList<InetAddress>();
	private CustomerBean customer;
	private int textPort = DEFAULT_TEXT_PORT;
	private int filePort = DEFAULT_FILE_PORT;
	private IChatClientAction clientAction;
	private IChatServerAction serverAction;
	private DatagramSocket socket;
	private boolean started;

	public static void main(String[] args) throws BindException {
		Chat c = new Chat("zzzz", new IChatServerAction() {

			@Override
			public void addTextMsg(MsgBean b) {
				// TODO Auto-generated method stub
				System.out.println("ip:" + b.getFromCustomer().getIp() + " name:" + b.getFromCustomer().getName()
						+ " 说：" + b.getContent());
			}

			@Override
			public File requestSendFile(String ip, String name, String fileName, long size) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void displaySize(int size) {
				// TODO Auto-generated method stub

			}

			@Override
			public void displayFile(File file) {
				// TODO Auto-generated method stub

			}

			@Override
			public void addUser(CustomerBean cb) {
				// TODO Auto-generated method stub
				System.out.println("add user ip:" + cb.getIp() + " name:" + cb.getName());
			}

			@Override
			public void removeUser(CustomerBean cb) {
				// TODO Auto-generated method stub
				System.out.println("remove user ip:" + cb.getIp() + " name:" + cb.getName());
			}
		});

		c.start();
		c.getClientAction().sendTextMessage("test message", "172.20.230.58", "zzzz");
		c.getClientAction().exit();
	}

	public Chat(String name, IChatServerAction serverAction) {
		try {
			this.socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		this.serverAction = serverAction;
		this.customer = new CustomerBean();
		this.clientAction = new ChatClientAction();
		String ip = getLocalHost();
		customer.setIp(ip);
		if (name == null || name.trim().equals("")) {
			customer.setName(ip);
		} else {
			customer.setName(name);
		}
		List<String> ips = getIps(ip);
		for (String str : ips) {
			addressList.add(getAddressByIp(str));
		}
		started = true;
	}

	private List<String> getIps(String ip) {
		int index = ip.lastIndexOf(".");
		String after = ip.substring(0, index);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 256; i++) {
			list.add(new String(after + "." + i));
		}
		return list;
	}

	private InetAddress getAddressByIp(String ip) {
		try {
			InetAddress address = InetAddress.getByName(ip);
			return address;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getLocalHost() {
		try {
			InetAddress localHost = Inet4Address.getLocalHost();
			String ip = localHost.getHostAddress().toString();
			log.info(ip);
			return ip;
		} catch (UnknownHostException e) {
			log.error(e);
		}
		return null;
	}

	private void disConnect() {
		try {
			log.debug("disConnect");
			started = false;
			if (socket != null) {
				socket.close();
			}
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void start() throws BindException {
		new Thread(new ClientTextListener()).start();
		sendLoginMsg();
	}

	// 发送登陆消息
	private void sendLoginMsg() {
		log.debug("sendLoginMsg");
		customerList.clear();
		MsgBean mb = new MsgBean(MsgType.TYPE_LOGIN_REQUEST);
		mb.setFromCustomer(customer);

		for (InetAddress ad : addressList) {
			if (!ad.getHostAddress().equals(customer.getIp())) {
				send(mb, ad);
			}
		}
	}

	// 发送退出消息
	private void sendExitMsg() {
		log.debug("sendExitMsg");
		MsgBean mb = new MsgBean(MsgType.TYPE_EXIT_REQUEST);
		mb.setFromCustomer(customer);

		sendThis(mb);

		for (CustomerBean cb : customerList) {
			send(mb, getAddressByIp(cb.getIp()));
		}
	}

	// 发送信息
	private void send(MsgBean mb, InetAddress address) {
		try {
			if (started) {
				byte[] bs = mb.toJson().getBytes();
				DatagramPacket packet = null;
				packet = new DatagramPacket(bs, bs.length, address, textPort);
				socket.send(packet);
			}
		} catch (IOException ex) {
			log.error(ex);
		}
	}

	// 给自己发
	private void sendThis(MsgBean mb) {
		send(mb, getAddressByIp(customer.getIp()));
	}

	private CustomerBean getCustomerBeanByIPAndName(String ip, String name) {
		for (CustomerBean cb : customerList) {
			if (cb.equals(customer)) {
				return cb;
			}
		}
		return null;
	}

	private boolean hasExist(MsgBean msg) {
		for (CustomerBean cb : customerList) {
			if (cb.equals(msg.getFromCustomer())) {
				return true;
			}
		}
		return false;
	}

	class ChatClientAction implements IChatClientAction {

		@Override
		public boolean sendTextMessage(String msg, String ip, String name) {
			try {
				MsgBean mb = new MsgBean(MsgType.TYPE_TEXT_REQUEST);
				mb.setContent(msg);
				mb.setFromCustomer(customer);
				CustomerBean cb = getCustomerBeanByIPAndName(ip, name);
				mb.setToCustomer(cb);
				// 先给自己发一封
				sendThis(mb);
				if (null == mb.getToCustomer()) {// 给所有人发送
					for (CustomerBean cb2 : customerList) {
						send(mb, getAddressByIp(cb2.getIp()));
					}
				} else {// 给指定人发送
					send(mb, getAddressByIp(cb.getIp()));
				}
			} catch (Exception ex) {
				log.error(ex);
				return false;
			}
			return true;
		}

		@Override
		public void sendFile(File file, String toIp) throws IOException {
			// TODO Auto-generated method stub

		}

		@Override
		public void setFilePath(File file) throws IOException {
			// TODO Auto-generated method stub
		}

		@Override
		public void refresh() {
			// TODO Auto-generated method stub
			sendLoginMsg();
		}

		@Override
		public void exit() {
			// TODO Auto-generated method stub
			sendExitMsg();
		}
	}

	// 接收文本消息
	class ClientTextListener implements Runnable {
		private DatagramSocket socket;
		private DatagramPacket p;

		public ClientTextListener() {
			p = new DatagramPacket(new byte[1024], 1024);
		}

		public void run() {
			try {
				socket = new DatagramSocket(textPort);
				log.debug("start text listener");
			} catch (SocketException e) {
				e.printStackTrace();
			}
			while (started) {
				try {
					log.debug("receive");
					socket.receive(p);
					log.debug("a package");
					String json = new String(p.getData(), 0, p.getLength());
					log.debug(json);
					MsgBean msg = MsgBean.createBean(json);
					switch (msg.getType()) {
					case TYPE_NONE: {
						break;
					}
					case TYPE_LOGIN_REQUEST: {
						if (msg.getFromCustomer().equals(customer)) {
							break;
						}
						// 添加
						if (!hasExist(msg)) {
							customerList.add(msg.getFromCustomer());
							serverAction.addUser(msg.getFromCustomer());
						}
						// 回复
						MsgBean b = new MsgBean(MsgType.TYPE_LOGIN_RESPONSE);
						b.setFromCustomer(customer);
						send(b, getAddressByIp(msg.getFromCustomer().getIp()));
						break;
					}
					case TYPE_LOGIN_RESPONSE: {
						// 添加
						customerList.add(msg.getFromCustomer());
						serverAction.addUser(msg.getFromCustomer());
						break;
					}
					case TYPE_EXIT_REQUEST: {
						// 自己的关闭请求
						if (msg.getFromCustomer().equals(customer)) {
							disConnect();
						} else {
							CustomerBean cb = getCustomerBeanByIPAndName(msg.getFromCustomer().getIp(), msg
									.getFromCustomer().getName());
							customerList.remove(cb);
							serverAction.removeUser(msg.getFromCustomer());
						}
						break;
					}
					case TYPE_EXIT_RESPONSE: {
						break;
					}
					case TYPE_TEXT_REQUEST: {
						serverAction.addTextMsg(msg);
						break;
					}
					case TYPE_TEXT_RESPONSE: {
						break;
					}
					}
				} catch (IOException e) {
					started = false;
					log.error(e);
				}
			}
		}
	}

	/**
	 * @author jim_qiao
	 * 
	 */
	class ClientFileListener implements Runnable {

		public ClientFileListener(Socket s) {
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
		}

	}

	public IChatClientAction getClientAction() {
		return clientAction;
	}
}
