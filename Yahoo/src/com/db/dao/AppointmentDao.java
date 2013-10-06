package com.db.dao;

import java.util.Date;
import java.util.List;

import com.db.model.Appointment;
import com.util.DB;

public class AppointmentDao extends BaseDao {

	public Appointment save(final Appointment ap) {
		return (Appointment) execute(new CallBack() {
			public Object execute(DB db) {
				db.add(ap);
				return ap;
			}
		});
	}

	public void deleteByDay(final Date day) {
		execute(new CallBack() {
			public Object execute(DB db) {
				Appointment ap = db.getByDay(day);
				if (ap != null) {
					db.getAppointments(day).remove(ap);
				}
				return null;
			}
		});
	}

	public Appointment getByDay(final Date date) {
		return getDB().getByDay(date);
	}

	public Appointment update(final Appointment ap) {
		Appointment old = getByDay(ap.getDay());
		if (old != null) {
			deleteByDay(ap.getDay());

			old.setDay(ap.getDay());
			old.setBeginWorkTime(ap.getBeginWorkTime());
			old.setEndWorkTime(ap.getEndWorkTime());
			old.setWorkHours(ap.getWorkHours());
			old.setBeginOverTime(ap.getBeginOverTime());
			old.setEndOverTime(ap.getEndOverTime());
			old.setOverHours(ap.getOverHours());
			old.setDescription(ap.getDescription());

			return save(old);
		} else {
			return save(ap);
		}
	}

	public List<Appointment> getList() {
		return getDB().getAppointments();
	}
}
