package com.bean;

import java.util.Date;

public class AccountFilterBean {
	private Integer id;
	private String name;
	private String pwd;
	private Date forStartDate;
	private Date toStartDate;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * @param pwd
	 *            the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * @return the forStartDate
	 */
	public Date getForStartDate() {
		return forStartDate;
	}

	/**
	 * @param forStartDate
	 *            the forStartDate to set
	 */
	public void setForStartDate(Date forStartDate) {
		this.forStartDate = forStartDate;
	}

	/**
	 * @return the toStartDate
	 */
	public Date getToStartDate() {
		return toStartDate;
	}

	/**
	 * @param toStartDate
	 *            the toStartDate to set
	 */
	public void setToStartDate(Date toStartDate) {
		this.toStartDate = toStartDate;
	}
}
