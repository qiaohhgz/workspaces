package com.util;

public class JStringUtils {
	public static String format(String source, Object... array) {
		for (int i = 0; i < array.length; i++) {
			String oldStr = "{" + i + "}";
			String newStr = array[i].toString();
			while (source.indexOf(oldStr) != -1) {
				source = source.replace(oldStr, newStr);
			}
		}
		return source;
	}
}
