package com.db.model;

import java.io.Serializable;
import java.util.Date;

public class Appointment implements Serializable {
	private static final long serialVersionUID = 3080368151604178908L;
	private long id;
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

	public Appointment() {
	}

	public Appointment(Date day, Date beginWorkTime, Date endWorkTime, long workHours, Date beginOverTime,
			Date endOverTime, long overHours, String description) {
		this.day = day;
		this.beginWorkTime = beginWorkTime;
		this.endWorkTime = endWorkTime;
		this.workHours = workHours;
		this.beginOverTime = beginOverTime;
		this.endOverTime = endOverTime;
		this.overHours = overHours;
		this.description = description;
	}

	public Date getBeginOverTime() {
		return beginOverTime;
	}

	public Date getBeginWorkTime() {
		return beginWorkTime;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public Date getDay() {
		return day;
	}

	public String getDescription() {
		return description;
	}

	public Date getEndOverTime() {
		return endOverTime;
	}

	public Date getEndWorkTime() {
		return endWorkTime;
	}

	public long getId() {
		return id;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public long getOverHours() {
		return overHours;
	}

	public long getWorkHours() {
		return workHours;
	}

	public void setBeginOverTime(Date beginOverTime) {
		this.beginOverTime = beginOverTime;
	}

	public void setBeginWorkTime(Date beginWorkTime) {
		this.beginWorkTime = beginWorkTime;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEndOverTime(Date endOverTime) {
		this.endOverTime = endOverTime;
	}

	public void setEndWorkTime(Date endWorkTime) {
		this.endWorkTime = endWorkTime;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public void setOverHours(long overHours) {
		this.overHours = overHours;
	}

	public void setWorkHours(long workHours) {
		this.workHours = workHours;
	}
}
