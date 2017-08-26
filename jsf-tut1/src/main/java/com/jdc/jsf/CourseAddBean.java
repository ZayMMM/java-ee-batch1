package com.jdc.jsf;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;

import com.jdc.jsf.entity.Course;
import com.jdc.jsf.service.CourseService;

@Model
public class CourseAddBean {
	
	private Course course;
	
	@EJB
	private CourseService service;
	
	@PostConstruct
	private void init() {
		course = new Course();
	}
	
	public String save() {
		service.create(course);
		return "/courses";
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
 
}
