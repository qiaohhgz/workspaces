package com.bean;

public class Grouping {
	private String[] _keys;

	public Grouping() {
	}

	public Grouping(String[] _keys) {
		this._keys = _keys;
	}

	/**
	 * @return the _keys
	 */
	public String[] get_keys() {
		return _keys;
	}

	/**
	 * @param _keys
	 *            the _keys to set
	 */
	public void set_keys(String[] _keys) {
		this._keys = _keys;
	}
}
