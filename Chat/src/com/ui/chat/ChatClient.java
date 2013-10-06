package com.ui.chat;

import com.ui.chat.bean.CustomerBean;
import com.ui.chat.bean.MessageBean;

import java.io.*;
import java.net.*;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author jim_qiao
 * 
 */
public class ChatClient {
	public static final String DEFAULT_SERVER_HOST = "127.0.0.1";

	private SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");

	private String serverHost = DEFAULT_SERVER_HOST;
	private Socket s;
	private DataOutputStream dos;
	private DataInputStream dis;
	private boolean hasConnect;
	private boolean saveFile;
	private CustomerBean customer;
	private IChatServerActionListener serverAction;
	private IChatClientActionListener clientAction;

	public ChatClient(String name, String serverHost, IChatServerActionListener serverAction) {
		if (null != serverHost && !serverHost.trim().equals("")) {
			this.serverHost = serverHost;
        }
        this.serverAction = serverAction;
        customer = new CustomerBean();
		String ip = getLocalHost();
		customer.setIp(ip);
		if (name == null || name.trim().equals("")) {
			customer.setName(ip);
		} else {
			customer.setName(name);
		}

		clientAction = new IChatClientActionListener() {
			@Override
			public boolean sendTextMessage(String msg) {
				if (msg.trim().equals("")) {
					return false;
				}
				MessageBean mb = new MessageBean();
				mb.setContent(msg);
				mb.setCustomer(customer);
				send(mb);
				return true;
			}

			@Override
			public void sendFile(File file) throws IOException {
				MessageBean mb = new MessageBean();
				mb.setType(MessageBean.TYPE_FILE_REQUEST);
				mb.setFileLength(file.length());
				mb.setFileName(file.getName());
				send(mb);
			}

			@Override
			public void setFilePath(File file) throws IOException {
				// TODO Auto-generated method stub
				if (file == null) {
					return;
				}

			}

			@Override
			public void refresh() {
				// TODO Auto-generated method stub
				printDebug("refresh");
				MessageBean mb = new MessageBean();
				mb.setType(MessageBean.TYPE_USER_LIST_REFRESH);
				send(mb);
			}
		};
	}

	private String getLocalHost() {
		try {
			InetAddress localHost = Inet4Address.getLocalHost();
			String ip = localHost.getHostAddress().toString();
			return ip;
		} catch (UnknownHostException e) {
			printDebug(e);
		}
		return null;
	}

	public void connect() {
		printDebug(customer.getIp());
		new Thread(new Runnable() {
			public void run() {
				try {
					printDebug("connect server...");
					while (!hasConnect) {
						try {
							s = new Socket(serverHost, 8888);
							hasConnect = true;
						} catch (Exception e) {
						}
					}
					printDebug(format.format(new Date()) + ":connected server");
					dos = new DataOutputStream(s.getOutputStream());
					dis = new DataInputStream(s.getInputStream());
					new Thread(new ServerListener()).start();
					sendLoginMsg();// 发送登陆消息
				} catch (UnknownHostException e) {
					printDebug(e);
				} catch (IOException e) {
					printDebug(e);
				}
			}
		}).start();
	}

	// 发送登陆消息
	public void sendLoginMsg() {
		MessageBean mb = new MessageBean();
		mb.setType(MessageBean.TYPE_LOGIN);
		mb.setCustomer(customer);
		send(mb);
	}

	// 发送退出消息
	public void sendExitMsg() {
		MessageBean mb = new MessageBean();
		mb.setType(MessageBean.TYPE_EXIT);
		mb.setCustomer(customer);
		send(mb);
	}

	// 发送信息
	public void send(MessageBean mb) {
		try {
			if (hasConnect) {
				dos.writeUTF(mb.toJson());
				dos.flush();
			}
		} catch (IOException ex) {
			printDebug(ex);
		}
	}

	public void disConnect() {
		hasConnect = false;
		try {
			if (dis != null) {
				this.dis.close();
			}
			if (dos != null) {
				this.dos.close();
			}
			if (s != null) {
				this.s.close();
			}
		} catch (IOException ex) {
			printDebug(ex);
			System.exit(0);
		}
		System.exit(0);
	}

	private void printDebug(Exception ex) {
		ex.printStackTrace();
	}

	private void printDebug(String debug) {
		serverAction.displayDebug(debug);
	}

	private class ServerListener implements Runnable {
		private long fileLength;
		private FileOutputStream fos;

		public void run() {
			while (hasConnect) {
				try {
					if (!saveFile) {
						String json = dis.readUTF();// 读取接收到的字符串
						MessageBean mb = MessageBean.createBean(json);// 转换成消息对象
						if (mb.hasText()) {// 文本信息
							String msg = MessageFormat.format("{0}    {1}\n{2}", mb.getCustomer().getName(), format.format(mb
									.getCreateOn()), mb.getContent());
							serverAction.displayTextMessage(msg);
						} else if (mb.getType() == MessageBean.TYPE_EXIT) {// 退出
							disConnect();
						} else if (mb.hasFileRequest()) {// 文件
							String fileName = mb.getFileName();
							fileLength = mb.getFileLength();
							File file = serverAction.requestSendFile(mb.getCustomer().getIp(), mb.getCustomer().getName(),
									fileName, fileLength);
							if (file != null) {// 同意发送
								mb.setFilePath(file.getPath());
								mb.setResult(true);
								mb.setType(MessageBean.TYPE_FILE_RESPONSE);
								send(mb);

								// 等待服务器转发来的问价
								saveFile = true;
								fos = new FileOutputStream(file);
								// TODO
							} else {// 不接受文件
								mb.setType(MessageBean.TYPE_FILE_RESPONSE);
								mb.setResult(false);
								send(mb);
							}
						} else if (mb.hasUserList()) {// 用户列表
							List<CustomerBean> list = mb.getCustomers();
							serverAction.displayUserList(list);
						}
					} else {
						byte[] bs = new byte[1024];
						int size = 0;
						while (size < fileLength && (size = dis.read(bs)) != -1) {
							fos.write(bs, 0, size);
							fos.flush();
							serverAction.displaySize(size);
						}
						fos.close();
						saveFile = false;
					}
				} catch (SocketException ex) {
					printDebug("client close");
					printDebug(ex);
					disConnect();
				} catch (IOException ex) {
					printDebug(ex);
					disConnect();
				}
			}
		}
	}

	public interface IChatServerActionListener {
		// 当服务器端发来用户列表时调用
		public void displayUserList(List<CustomerBean> list);

		// 当服务器端发来文本信息是调用
		public void displayTextMessage(String text);

		// 打印debug信息
		public void displayDebug(String debug);

		// 服务器端转发文件
		public void displayFile(File file);

		//
		public File requestSendFile(String ip, String name, String fileName, long size);

		// 接收到的文件大小
		public void displaySize(int size);
	}

	public interface IChatClientActionListener {
		// 当客户端发送消息时调用
		public boolean sendTextMessage(String text);

		// 当客户端发送文件时调用
		public void sendFile(File file) throws IOException;

		// 客户端发送保存文件的路径
		public void setFilePath(File file) throws IOException;

		// 更新好友列表
		public void refresh();
	}

	public interface ICallBack {
		public void display(String json);
	}

	// ///////////////////////////

	public IChatClientActionListener getClientAction() {
		return clientAction;
	}

	public void setClientAction(IChatClientActionListener clientAction) {
		this.clientAction = clientAction;
	}
}
