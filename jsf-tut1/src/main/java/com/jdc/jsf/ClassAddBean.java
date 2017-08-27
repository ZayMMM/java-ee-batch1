package com.jdc.jsf;

import java.time.DayOfWeek;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;

import com.jdc.jsf.entity.Class;
import com.jdc.jsf.entity.Course;
import com.jdc.jsf.service.ClassService;
import com.jdc.jsf.service.CourseService;

@Model
public class ClassAddBean {

	private Class clas;
	
	private DayOfWeek[] days;
	private List<Course> courses;
	
	@EJB
	private ClassService service;
	@EJB
	private CourseService courseService;
	
	@PostConstruct
	private void init() {
		clas = new Class();
		courses = courseService.findAll();
		days = DayOfWeek.values();
	}
	
	public String save() {
		service.create(clas);
		return "/classes?faces-redirect=true";
	}

	public Class getClas() {
		return clas;
	}

	public void setClas(Class clas) {
		this.clas = clas;
	}

	public DayOfWeek[] getDays() {
		return days;
	}

	public void setDays(DayOfWeek[] days) {
		this.days = days;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
}
