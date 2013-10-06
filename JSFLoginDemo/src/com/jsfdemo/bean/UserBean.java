package com.jsfdemo.bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class UserBean {

	private java.lang.String userName;
	private java.lang.String password;

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getPassword() {
		return password;
	}

	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	public String loginUser() {
		if ("admin".equals(getUserName()) && "123".equals(getPassword()))
			return "success";
		FacesContext facesContext = FacesContext.getCurrentInstance();
		System.out.println("login input");
		FacesMessage facesMessage = new FacesMessage("You have entered an invalid user name and/or password");
		facesContext.addMessage("loginForm", facesMessage);
		return "input";
	}
}
