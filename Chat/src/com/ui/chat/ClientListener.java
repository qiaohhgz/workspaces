package com.ui.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ui.chat.bean.CustomerBean;
import com.ui.chat.bean.MessageBean;

/**
 * @author jim_qiao
 * 
 */
public class ClientListener implements Runnable {
	private Socket s;
	private DataInputStream dis;
	private DataOutputStream dos;
	private boolean hasConnect = false;
	private List<ClientListener> clients;
	private CustomerBean customer;

	public ClientListener(Socket s, List<ClientListener> clients) {
		try {
			this.s = s;
			this.dis = new DataInputStream(s.getInputStream());
			this.dos = new DataOutputStream(s.getOutputStream());
			this.clients = clients;
			hasConnect = true;
		} catch (IOException ex) {
			printDebug(ex);
		}
	}

	public void send(MessageBean msgBean) {
		try {
			if (hasConnect) {
				dos.writeUTF(msgBean.toJson());
				dos.flush();
			}
		} catch (IOException ex) {
			printDebug(ex);
		}
	}

	public void refresh() {
		List<CustomerBean> customers = new ArrayList<CustomerBean>();
		for (int i = 0; i < clients.size(); i++) {
			ClientListener cl = clients.get(i);
			customers.add(cl.getCustomer());
		}
		MessageBean userListBean = new MessageBean();
		userListBean.setType(MessageBean.TYPE_USER_LIST);
		userListBean.setCustomers(customers);
		for (int i = 0; i < clients.size(); i++) {
			ClientListener cl = clients.get(i);
			cl.send(userListBean);
		}
	}

	public void run() {
		try {
			while (hasConnect) {
				String json = dis.readUTF();
				MessageBean mb = MessageBean.createBean(json);
				switch (mb.getType()) {
				case MessageBean.TYPE_LOGIN:// 登陆
					this.customer = mb.getCustomer();
					printDebug("a client login success --> iP:" + customer.getIp() + "  name:" + customer.getName());
					// TODO 发送用户列表
					refresh();
					break;
				case MessageBean.TYPE_EXIT:// 退出
					String ip = mb.getCustomer().getIp();
					if (removeThis(ip)) {// 先从集合中删除这个客户端，然后回复消息给客户端，告诉他可以关闭了
						printDebug("a client exit success --> iP:" + customer.getIp() + "  name:" + customer.getName());
					}
					break;
				case MessageBean.TYPE_TEXT:// 文本
					// TODO 回复用户已收到该信息
					// 给每一个用户都发一条消息
					mb.setCreateOn(new Date());
					for (int i = 0; i < clients.size(); i++) {
						ClientListener ct = clients.get(i);
						ct.send(mb);
					}
					break;
				case MessageBean.TYPE_FILE_REQUEST:// 接收到客户端发送文件请求
					// TODO 发送文件
					String fileName = mb.getFileName();
					long length = mb.getFileLength();
					mb.setCustomer(customer);
					printDebug(fileName + " length:" + length);
					// 询问每一个客户端是否接收文件
					for (int i = 0; i < clients.size(); i++) {
						// 不给自己发送文件
						if (!clients.get(i).getCustomer().getIp().equals(getCustomer().getIp())) {
							ClientListener ct = clients.get(i);
							ct.send(mb);
						}
					}
					break;
				case MessageBean.TYPE_FILE_RESPONSE:// 得到客户端的响应
					if (mb.hasResult()) {// 同意接收文件
						long len = mb.getFileLength();
						byte[] bs = new byte[1024];
						int size = 0;
						long lengthSum = 0;
						while (lengthSum < len && (size = dis.read(bs)) != -1) {
							lengthSum += size;
							dos.write(bs, 0, size);
						}
						dos.flush();
						dos.close();
						printDebug("ok");
					} else {

					}
					break;
				case MessageBean.TYPE_USER_LIST_REFRESH:
					refresh();
					break;
				}
			}
		} catch (EOFException ex) {
			disConnect();
			printDebug(ex);
		} catch (SocketException ex) {
			disConnect();
			printDebug(ex);
		} catch (IOException ex) {
			disConnect();
			printDebug(ex);
		} finally {
			disConnect();
		}
	}

	private boolean removeThis(String ip) {
		if (ip.equals(customer.getIp())) {
			// 发送最后一封关闭消息
			MessageBean msgb = new MessageBean();
			msgb.setType(MessageBean.TYPE_EXIT);
			send(msgb);
			// 从集合中删除自己
			clients.remove(this);
			// 更新好友列表
			refresh();
			// 关闭连接
			disConnect();
			printDebug("clients:" + clients.size());
			return true;
		}
		return false;
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
		}
	}

	public CustomerBean getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerBean customer) {
		this.customer = customer;
	}

	private void printDebug(String debug) {
		System.out.println(debug);
	}

	private void printDebug(Exception ex) {
		ex.printStackTrace();
	}
}
