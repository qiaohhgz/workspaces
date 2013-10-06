package com.bean;

import java.util.HashMap;

public class State {
	private int errCode;
	private String errMessage;
	private Page page;
	private Sort sort;
	private Grouping grouping;
	private HashMap<String, String> filter;

	public String toJSONString() {
		StringBuffer sb = new StringBuffer("");
		sb.append("state : {");
		sb.append(getPage().toJSONString());
		sb.append(",");
		sb.append(getSort().toJSONString());
		sb.append("}");
		return sb.toString();
	}

	/**
	 * @return the errCode
	 */
	public int getErrCode() {
		return errCode;
	}

	/**
	 * @param errCode
	 *            the errCode to set
	 */
	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	/**
	 * @return the errMessage
	 */
	public String getErrMessage() {
		return errMessage;
	}

	/**
	 * @param errMessage
	 *            the errMessage to set
	 */
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	/**
	 * @return the page
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(Page page) {
		this.page = page;
	}

	/**
	 * @return the sort
	 */
	public Sort getSort() {
		return sort;
	}

	/**
	 * @param sort
	 *            the sort to set
	 */
	public void setSort(Sort sort) {
		this.sort = sort;
	}

	/**
	 * @return the grouping
	 */
	public Grouping getGrouping() {
		return grouping;
	}

	/**
	 * @param grouping
	 *            the grouping to set
	 */
	public void setGrouping(Grouping grouping) {
		this.grouping = grouping;
	}

	/**
	 * @return the filter
	 */
	public HashMap<String, String> getFilter() {
		return filter;
	}

	/**
	 * @param filter
	 *            the filter to set
	 */
	public void setFilter(HashMap<String, String> filter) {
		this.filter = filter;
	}
}
