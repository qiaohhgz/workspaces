package com.ui.chat.bean;

import com.google.gson.Gson;

public class CustomerBean {
	private static Gson gson = new Gson();
	private String ip;
	private String name;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// ///////////////////////////////to String
	public String toString() {
		return toJson();
	}

	public String toJson() {
		return gson.toJson(this, MessageBean.class);
	}

	// ////////////////////////////////
	public static CustomerBean createBean(String json) {
		return gson.fromJson(json, CustomerBean.class);
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (!(obj instanceof CustomerBean)) {
			return false;
		}
		CustomerBean cb = (CustomerBean) obj;
		if (cb.getIp().equals(ip) && cb.getName().trim().equals(name)) {
			return true;
		}
		return false;
	}

}
