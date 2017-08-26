package com.jdc.jsf;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;

import com.jdc.jsf.entity.Student;
import com.jdc.jsf.service.StudentService;

@Model
public class StudentAddBean {

	private Student student;

	@EJB
	private StudentService service;

	@PostConstruct
	private void init() {
		student = new Student();
	}

	public String save() {
		service.create(student);
		return "/students";
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
