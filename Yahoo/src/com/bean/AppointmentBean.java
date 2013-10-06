package com.bean;

import java.util.Date;

import com.db.model.Appointment;

public class AppointmentBean {
	private Date day;
	private Date beginWorkTime;
	private Date endWorkTime;
	private long workHours;
	private Date beginOverTime;
	private Date endOverTime;
	private long overHours;
	private String description;
	private Date lastUpdateDate;
	private Date createOn;

	public AppointmentBean setAttribute(Appointment ap) {
		this.day = ap.getDay();
		this.beginWorkTime = ap.getBeginWorkTime();
		this.endWorkTime = ap.getEndWorkTime();
		this.workHours = ap.getWorkHours();
		this.beginOverTime = ap.getBeginOverTime();
		this.endOverTime = ap.getEndOverTime();
		this.overHours = ap.getOverHours();
		this.description = ap.getDescription();
		this.lastUpdateDate = ap.getLastUpdateDate();
		this.createOn = ap.getCreateOn();
		return this;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public Date getBeginWorkTime() {
		return beginWorkTime;
	}

	public void setBeginWorkTime(Date beginWorkTime) {
		this.beginWorkTime = beginWorkTime;
	}

	public Date getEndWorkTime() {
		return endWorkTime;
	}

	public void setEndWorkTime(Date endWorkTime) {
		this.endWorkTime = endWorkTime;
	}

	public long getWorkHours() {
		return workHours;
	}

	public void setWorkHours(long workHours) {
		this.workHours = workHours;
	}

	public Date getBeginOverTime() {
		return beginOverTime;
	}

	public void setBeginOverTime(Date beginOverTime) {
		this.beginOverTime = beginOverTime;
	}

	public Date getEndOverTime() {
		return endOverTime;
	}

	public void setEndOverTime(Date endOverTime) {
		this.endOverTime = endOverTime;
	}

	public long getOverHours() {
		return overHours;
	}

	public void setOverHours(long overHours) {
		this.overHours = overHours;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}
}
