package com.jdc.jsf;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;

import com.jdc.jsf.entity.Student;
import com.jdc.jsf.service.StudentService;

@Model
public class StudentListBean {

	private List<Student> list;
	
	@EJB
	private StudentService service;
	
	@PostConstruct
	private void init() {
		list = service.findAll();
	}

	public List<Student> getList() {
		return list;
	}

	public void setList(List<Student> list) {
		this.list = list;
	}

}
