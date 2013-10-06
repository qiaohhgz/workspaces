package com.bean;

public class Page {
	private int rowsPerPage;
	private int totalRecords;
	private int recordOffset;
	private String visibility;
	private boolean local;

	public String toJSONString() {
		StringBuffer sb = new StringBuffer("");
		sb.append("page:{\"recordOffset\" : ");
		sb.append(getRecordOffset());
		sb.append(", \"local\":");
		sb.append(false);
		sb.append(", \"rowsPerPage\":");
		sb.append(getRowsPerPage());
		sb.append(", \"totalRecords\":");
		sb.append(getTotalRecords());
		sb.append("}");
		return sb.toString();
	}

	/**
	 * @return the rowsPerPage
	 */
	public int getRowsPerPage() {
		return rowsPerPage;
	}

	/**
	 * @param rowsPerPage
	 *            the rowsPerPage to set
	 */
	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	/**
	 * @return the totalRecords
	 */
	public int getTotalRecords() {
		return totalRecords;
	}

	/**
	 * @param totalRecords
	 *            the totalRecords to set
	 */
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * @return the recordOffset
	 */
	public int getRecordOffset() {
		return recordOffset;
	}

	/**
	 * @param recordOffset
	 *            the recordOffset to set
	 */
	public void setRecordOffset(int recordOffset) {
		this.recordOffset = recordOffset;
	}

	/**
	 * @return the visibility
	 */
	public String getVisibility() {
		return visibility;
	}

	/**
	 * @param visibility
	 *            the visibility to set
	 */
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	/**
	 * @return the local
	 */
	public boolean isLocal() {
		return local;
	}

	/**
	 * @param local
	 *            the local to set
	 */
	public void setLocal(boolean local) {
		this.local = local;
	}
}
