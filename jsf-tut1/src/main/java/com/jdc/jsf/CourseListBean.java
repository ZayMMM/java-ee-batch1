package com.jdc.jsf;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;

import com.jdc.jsf.entity.Course;
import com.jdc.jsf.service.CourseService;

@Model
public class CourseListBean {

	private List<Course> courses;

	@EJB
	private CourseService service;

	@PostConstruct
	private void init() {
		courses = service.findAll();
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

}
