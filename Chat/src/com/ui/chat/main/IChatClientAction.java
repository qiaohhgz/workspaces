package com.ui.chat.main;

import java.io.File;
import java.io.IOException;

public interface IChatClientAction {
	// 当客户端发送消息时调用
	public boolean sendTextMessage(String text, String toIp, String toName);

	// 当客户端发送文件时调用
	public void sendFile(File file, String toIp) throws IOException;

	// 客户端发送目录
	public void setFilePath(File file) throws IOException;

	// 更新好友列表
	public void refresh();

	// 退出
	public void exit();
}
