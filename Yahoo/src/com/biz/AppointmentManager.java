package com.biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bean.AppointmentBean;
import com.bean.AppointmentTable;
import com.db.dao.AppointmentDao;
import com.db.model.Appointment;
import com.util.DateHelper;

public class AppointmentManager {

	private AppointmentDao dao = new AppointmentDao();

	public Appointment getAppointmentByDay(Date date) {
		Appointment ap = dao.getByDay(date);
		if (ap == null) {
			ap = new Appointment();
			ap.setDay(date);
			ap.setCreateOn(date);
		}
		return ap;
	}

	public Appointment modifyAppointment(Appointment ap) {
		ap.setLastUpdateDate(new Date());
		return dao.update(ap);
	}

	public void beginWork(String desc) throws Exception {
		Date date = DateHelper.getCalendar().getTime();
		Appointment ap = getAppointmentByDay(date);
		ap.setBeginWorkTime(date);
		ap.setDescription(desc);
		modifyAppointment(ap);
	}

	public void endWork(String desc) throws Exception {
		Date date = DateHelper.getCalendar().getTime();
		Appointment ap = getAppointmentByDay(date);
		if (ap.getBeginWorkTime() == null) {
			throw new Exception("请先签到");
		}
		long workHours = DateHelper.getHours(ap.getBeginWorkTime(), date);
		ap.setEndWorkTime(date);
		ap.setWorkHours(workHours);
		ap.setDescription(desc);
		modifyAppointment(ap);
	}

	public void beginOverTime(String desc) throws Exception {
		Date date = DateHelper.getCalendar().getTime();
		Appointment ap = getAppointmentByDay(date);
		ap.setBeginOverTime(date);
		ap.setDescription(desc);
		modifyAppointment(ap);
	}

	public void endOverTime(String desc) throws Exception {
		Date date = DateHelper.getCalendar().getTime();
		Appointment ap = getAppointmentByDay(date);
		if (ap.getBeginOverTime() == null) {
			throw new Exception("还没开始加班");
		}
		long workHours = DateHelper.getHours(ap.getBeginOverTime(), date);
		ap.setEndOverTime(date);
		ap.setOverHours(workHours);
		ap.setDescription(desc);
		modifyAppointment(ap);
	}

	public AppointmentTable getList() {
		AppointmentTable table = new AppointmentTable();
		List<AppointmentBean> list = new ArrayList<AppointmentBean>();
		for (Appointment ap : dao.getList()) {
			list.add(new AppointmentBean().setAttribute(ap));
		}
		table.setAps(list);
		return table;
	}

	public static void main(String[] args) {
		try {
			AppointmentManager manager = new AppointmentManager();
			manager.beginWork(null);
			manager.endWork(null);
			manager.beginOverTime("Hello");
			manager.endOverTime("world");
			System.out.println(manager.getList().getAps().size());
			System.out.println("OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
