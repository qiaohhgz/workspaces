package com.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Manager {
	private Properties config = new Properties();// 记录配置项
	private String fn = null;// 记录配置文件名
	
	// 从指定文件名读入配置信息
	public Manager(String fileName) throws Exception {
		try {
			FileInputStream fin = new FileInputStream(fileName);
			config.load(fin); // 载入文件
			fin.close();
		} catch (IOException ex) {
			saveFile(fileName);
		}
		fn = fileName;
	}

	// 指定配置项名称，返回配置值
	public String getValue(String itemName) {
		return config.getProperty(itemName);
	}

	// 指定配置项名称和默认值，返回配置值
	public String getValue(String itemName, String defaultValue) {
		return config.getProperty(itemName, defaultValue);
	}

	// 设置配置项名称及其值
	public void setValue(String itemName, String value) {
		config.setProperty(itemName, value);
		return;
	}

	// 保存配置文件，指定文件名和抬头描述
	public void saveFile(String fileName, String description) throws Exception {
		try {
			FileOutputStream fout = new FileOutputStream(fileName);
			config.store(fout, description);// 保存文件
			fout.close();
		} catch (Exception ex) {
			throw new Exception("无法保存指定的配置文件:" + fileName);
		}
	}

	// 保存配置文件，指定文件名
	public void saveFile(String fileName) throws Exception {
		saveFile(fileName, "");
	}

	// 保存配置文件，采用原文件名
	public void saveFile() throws Exception {
		if (fn.length() == 0)
			fn = "myconf.pro";
		saveFile(fn);
	}
}
