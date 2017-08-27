package com.jdc.jsf.entity;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.Set;

@SuppressWarnings("serial")
public class Class implements Serializable {

	private int id;
	private Date startDate;
	private Set<DayOfWeek> days;
	private String startTime;
	private String endTime;
	private Course course;
	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Set<DayOfWeek> getDays() {
		return days;
	}

	public void setDays(Set<DayOfWeek> days) {
		this.days = days;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
