package com.jim.demo.webdisk;

import java.util.Calendar;
import java.util.Locale;

import org.junit.Test;

public class AppTest {
	@Test
	public void testDate() {
		Calendar c = Calendar.getInstance(Locale.CHINA);

		System.out.println(c.get(Calendar.DAY_OF_MONTH));
		System.out.println(c.get(Calendar.DAY_OF_WEEK));
		System.out.println(c.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		System.out.println(c.get(Calendar.DAY_OF_YEAR));
		System.out.println(c.getLeastMaximum(Calendar.DATE));

		System.out.println("=====================");
		for (int m = 0; m < 12; m++) {
			c.set(Calendar.MONTH, m);
			System.out.println(c.getActualMaximum(Calendar.DAY_OF_MONTH));
		}
	}
}
