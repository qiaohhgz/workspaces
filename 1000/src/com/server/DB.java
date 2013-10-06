package com.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.bean.Account;

public class DB {
	public static final List<Account> accounts = new ArrayList<Account>();
	public static final String CONTENT = "qwertyuiopasdfghjklzxcvbnm";
	public static Integer id = 0;
	static {
		addTestData();
	}

	public static void addTestData() {
		Random r = new Random();
		for (int i = 0; i < 100; i++) {
			Account ac = new Account();
			ac.setId(i);
			ac.setCreateOn(new Date());
			String n = "";
			for (int j = 0; j < 5; j++) {
				n += CONTENT.charAt(r.nextInt(CONTENT.length()));
			}
			ac.setName(n);
			String s = "";
			for (int j = 0; j < 6; j++) {
				s += r.nextInt(10);
			}
			ac.setPwd(s);
			accounts.add(ac);
		}
	}
}
