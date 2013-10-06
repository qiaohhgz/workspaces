package com.bean;

public class Sort {
	private String key;
	private int dir;
	private boolean local;

	public final static int SORT_ASCENDING = 0;
	public final static int SORT_DESCENDING = 1;

	public String toJSONString() {
		StringBuffer sb = new StringBuffer("");
		sb.append("sort:{\"key\" :\"");
		sb.append(getKey());
		sb.append("\", \"local\":");
		sb.append(isLocal());
		sb.append(", \"dir\":");
		sb.append(getDir());
		sb.append("}");
		return sb.toString();
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the dir
	 */
	public int getDir() {
		return dir;
	}

	/**
	 * @param dir
	 *            the dir to set
	 */
	public void setDir(int dir) {
		this.dir = dir;
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
