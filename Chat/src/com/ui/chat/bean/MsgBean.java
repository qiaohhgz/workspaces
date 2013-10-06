package com.ui.chat.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

/**
 * @author jim_qiao
 */
public class MsgBean implements Serializable {
	private static final long serialVersionUID = -2162155552394485650L;
	private static final Logger log = Logger.getLogger(MsgBean.class);
	private static Gson gson = new Gson();
	private MsgType type = MsgType.TYPE_NONE;// 消息类型
	private String content;// 文本消息内容
	private String fileName;// 文件名字
	private Long fileLength;// 文件大小
	private String filePath;// 客户端要保存的文件路径
	private CustomerBean fromCustomer;// 发送者
	private CustomerBean toCustomer;// 接受者
	private List<CustomerBean> customers;// 用户列表
	private Date createOn;// 信息创建时间

	public MsgBean() {

	}

	public MsgBean(MsgType type) {
		this.type = type;
	}

	// //////////////////////// to string

	public String toString() {
		return toJson();
	}

	public String toJson() {
		String json = gson.toJson(this, MsgBean.class);
		return json;
	}

	// //////////////////////// create bean
	public static MsgBean createBean(String json) {
		return gson.fromJson(json, MsgBean.class);
	}

	// ///////////////////////// get set
	public MsgType getType() {
		return type;
	}

	public void setType(MsgType type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public CustomerBean getFromCustomer() {
		return fromCustomer;
	}

	public void setFromCustomer(CustomerBean fromCustomer) {
		this.fromCustomer = fromCustomer;
	}

	public CustomerBean getToCustomer() {
		return toCustomer;
	}

	public void setToCustomer(CustomerBean toCustomer) {
		this.toCustomer = toCustomer;
	}

	public List<CustomerBean> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerBean> customers) {
		this.customers = customers;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

}
