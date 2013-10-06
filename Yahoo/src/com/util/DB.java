package com.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.db.model.Appointment;

public class DB implements Serializable {
	private static final long serialVersionUID = 3210964094963012695L;
	private Map<Integer, Map<Integer, List<Appointment>>> map = new HashMap<Integer, Map<Integer, List<Appointment>>>();

	public List<Appointment> getAppointments() {
		Date sameMonth = new Date();
		return getAppointments(sameMonth);
	}

	public List<Appointment> getAppointments(Date date) {
		List<Appointment> aps = new ArrayList<Appointment>();
		Integer year = DateHelper.getCalendar(date).get(Calendar.YEAR);
		Integer month = DateHelper.getCalendar(date).get(Calendar.MONTH);
		Map<Integer, List<Appointment>> yMap = map.get(year);
		if (yMap == null) {
			return aps;
		}
		aps = yMap.get(month);
		return aps;
	}

	public void add(Appointment ap) {
		Date date = ap.getDay();
		if (date == null) {
			return;
		}
		Integer year = DateHelper.getCalendar(date).get(Calendar.YEAR);
		Integer month = DateHelper.getCalendar(date).get(Calendar.MONTH);
		Map<Integer, List<Appointment>> yearMap = map.get(year);
		if (yearMap == null) {
			yearMap = new HashMap<Integer, List<Appointment>>();
			map.put(year, yearMap);
		}
		List<Appointment> monthList = yearMap.get(month);
		if (monthList == null) {
			monthList = new ArrayList<Appointment>();
			yearMap.put(month, monthList);
		}
		monthList.add(ap);
	}

	public Appointment getByDay(Date date) {
		Integer year = DateHelper.getCalendar(date).get(Calendar.YEAR);
		Integer month = DateHelper.getCalendar(date).get(Calendar.MONTH);
		Integer day = DateHelper.getDay(date);
		Map<Integer, List<Appointment>> yMap = map.get(year);
		if (yMap == null) {
			return null;
		}
		List<Appointment> aps = yMap.get(month);
		if (aps == null) {
			return null;
		}
		for (Appointment ap : aps) {
			if (DateHelper.getDay(ap.getDay()) == day) {
				return ap;
			}
		}
		return null;
	}
}
