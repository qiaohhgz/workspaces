package com.common;

public class Format {
	public static String stringFormat(String resource, Object... args) {
		String res = resource;
		for (int i = 0; i < args.length; i++) {
			String s = args[i].toString();
			res = res.replace("{" + i + "}", s);
		}
		return res;
	}

	public static void main(String[] args) {
		System.out.println(stringFormat("{0}{1}{0}", "A", "B"));
	}
}
