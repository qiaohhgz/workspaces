package com.entity;

import java.util.Date;

import com.util.JStringUtils;

public class Product {
	private String id;
	private String name;
	private double price;
	private Date createDate;
	private int status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return JStringUtils.format(
				"{id:'{0}',name:'{1}',price:{2},createDate:'{3}',status:{4}}",
				id, name, price, createDate, status);
	}
}
