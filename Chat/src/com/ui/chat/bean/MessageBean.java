package com.ui.chat.bean;

import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

public class MessageBean {
	public static final int TYPE_TEXT = 1;
	public static final int TYPE_LOGIN = 2;
	public static final int TYPE_EXIT = 3;
	public static final int TYPE_FILE = 4;
	public static final int TYPE_FILE_REQUEST = 6;
	public static final int TYPE_FILE_RESPONSE = 7;
	public static final int TYPE_USER_LIST = 5;
	public static final int TYPE_USER_LIST_REFRESH = 8;

	private static Gson gson = new Gson();

	private int type = TYPE_TEXT;// 消息类型
	private String content;// 文本消息内容
	private Date createOn;// 信息创建时间
	private CustomerBean customer;// 发送者
	private boolean result;// 接收文件还是不接收文件
	private String fileName;// 文件名字
	private long fileLength;// 文件大小
	private String filePath;// 客户端要保存的文件路径
	private List<CustomerBean> customers;// 用户列表

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getFileLength() {
		return fileLength;
	}

	public void setFileLength(long fileLength) {
		this.fileLength = fileLength;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

	public CustomerBean getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerBean customer) {
		this.customer = customer;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<CustomerBean> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerBean> customers) {
		this.customers = customers;
	}

	// //////////////////// has method
	public boolean hasText() {
		return (TYPE_TEXT == type);
	}

	public boolean hasLogin() {
		return (TYPE_LOGIN == type);
	}

	public boolean hasExit() {
		return (TYPE_EXIT == type);
	}

	public boolean hasUserList() {
		return (TYPE_USER_LIST == type);
	}

	public boolean hasFile() {
		return (TYPE_FILE == type);
	}

	public boolean hasFileRequest() {
		return (TYPE_FILE_REQUEST == type);
	}

	public boolean hasFileResponse() {
		return (TYPE_FILE_RESPONSE == type);
	}

	public boolean hasUserListRefresh() {
		return (TYPE_USER_LIST_REFRESH == type);
	}

	// //////////////////////// to string

	public String toString() {
		return toJson();
	}

	public String toJson() {
		return gson.toJson(this, MessageBean.class);
	}

	// ////////////////////////
	public static MessageBean createBean(String json) {
		return gson.fromJson(json, MessageBean.class);
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public boolean hasResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

}
