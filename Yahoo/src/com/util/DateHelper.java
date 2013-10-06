package com.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.db.model.Appointment;

public class DateHelper {
	public static final Locale LOCALE = Locale.CHINESE;
	public static final SimpleDateFormat FORMAT_1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public static final SimpleDateFormat FORMAT_YEAR_MONTH = new SimpleDateFormat("yyyyMM");
	public static final SimpleDateFormat FORMAT_YEAR = new SimpleDateFormat("yyyy");
	public static final SimpleDateFormat FORMAT_MONTH = new SimpleDateFormat("MM");
	public static final SimpleDateFormat FORMAT_DAY = new SimpleDateFormat("dd");
	public static final SimpleDateFormat FORMAT_SHEET = new SimpleDateFormat("MM");

	public static Date getDate() {
		return getCalendar().getTime();
	}

	public static Calendar getCalendar() {
		return Calendar.getInstance(LOCALE);
	}

	public static Calendar getCalendar(Date date) {
		Calendar c = getCalendar();
		c.setTime(date);
		return c;
	}

	public static int getToDay() {
		return getCalendar().get(Calendar.DATE);
	}

	public static int getDay(Date date) {
		return getCalendar(date).get(Calendar.DATE);
	}

	public static int getDaysByMonth(Date month) {
		return getCalendar(month).get(Calendar.DAY_OF_MONTH);
	}

	public static Date add(Date date, int field, int hours) {
		Calendar c = getCalendar(date);
		c.add(field, hours);
		return c.getTime();
	}

	public static Date addHours(Date date, int hours) {
		return add(date, Calendar.HOUR, hours);
	}

	// SUNDAY、MONDAY、TUESDAY、WEDNESDAY、THURSDAY、FRIDAY 和 SATURDAY。
	public static int getWeekDay(Date date) {
		return getCalendar(date).get(Calendar.DAY_OF_WEEK);
	}

	public static final boolean isWeekEnd(Date date) {
		int weekDay = getWeekDay(date);
		return (weekDay == Calendar.SATURDAY || weekDay == Calendar.SUNDAY);
	}

	public static long getHours(Date begin, Date end) {
		if (getCalendar(end).after(getCalendar(begin))) {
			long endTime = end.getTime();
			long beginTime = begin.getTime();
			long hour = (endTime - beginTime) / (1000 * 60 * 60);
			return hour;
		}
		return -1;
	}

	public static String format(String format, Date date) {
		return new SimpleDateFormat(format).format(date);
	}

	public static void main(String[] args) {
		System.out.println(FORMAT_1.format(addHours(new Date(), -3)));

		System.out.println(getWeekDay(new Date()));

		System.out.println(isWeekEnd(add(new Date(), Calendar.DATE, 3)));

		System.out.println(getHours(new Date(), add(new Date(), Calendar.DATE, 3)));

		System.out.println(getDaysByMonth(add(getDate(), Calendar.MONTH, -6)));
	}
}
