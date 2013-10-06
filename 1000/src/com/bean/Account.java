package com.bean;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.HashMap;

public class Account {
	private Integer id;
	private String name;
	private String pwd;
	private Date createOn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}
	
	public static Account create(HashMap<String, String> filter){
		Account ac = new Account();
		filter.get("id");
		filter.get("name");
		filter.get("pwd");
		filter.get("createOn");
		return ac;
	}
}
