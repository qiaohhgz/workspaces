package com.ui.chat.main;

import java.io.File;

import com.ui.chat.bean.CustomerBean;
import com.ui.chat.bean.MsgBean;

public interface IChatServerAction {
	// 增加一个用户
	public void addUser(CustomerBean cb);

	// 减少一个用户
	public void removeUser(CustomerBean cb);

	// 当客户端发来文本信息是调用
	public void addTextMsg(MsgBean b);

	// 服务器端转发文件
	public void displayFile(File file);

	//
	public File requestSendFile(String ip, String name, String fileName, long size);

	// 接收到的文件大小
	public void displaySize(int size);
}
