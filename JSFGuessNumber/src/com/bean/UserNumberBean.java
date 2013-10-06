package com.bean;

import java.util.Random;

public class UserNumberBean {

	private int userNumber;
	private int keyNumber;
	private int maxNum = 10;
	private int minNum;
	private String responseStr;

	public UserNumberBean() {
		Random r = new Random();
		keyNumber = r.nextInt(maxNum);
		System.out.println("keyNumber:" + keyNumber);
	}

	public int getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}

	public int getKeyNumber() {
		return keyNumber;
	}

	public void setKeyNumber(int keyNumber) {
		this.keyNumber = keyNumber;
	}

	public int getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}

	public int getMinNum() {
		return minNum;
	}

	public void setMinNum(int minNum) {
		this.minNum = minNum;
	}

	public String getResponseStr() {
		if (userNumber == keyNumber) {
			responseStr = "您真聪明，您猜对了！";
		} else {
			responseStr = "对不起，您猜错了！不是" + userNumber + "!";
		}
		return responseStr;
	}

	public void setResponseStr(String responseStr) {
		this.responseStr = responseStr;
	}
}
