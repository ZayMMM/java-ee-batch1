package com.jdc.jsf.entity;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Lob;

@Entity
@SuppressWarnings("serial")
@NamedQuery(name="Class.findAll", query="select c from Class c")
public class Class implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private int id;
	@Temporal(DATE)
	private Date startDate;
	@Enumerated
	@ElementCollection
	private Set<DayOfWeek> days;
	private String startTime;
	private String endTime;
	
	@ManyToOne
	private Course course;
	@Lob
	private String remark;
	
	public Class() {
		days = new LinkedHashSet<>();
	}

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
