package com.jdc.jsf;

import com.jdc.jsf.entity.Class;
import com.jdc.jsf.service.ClassService;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;

@Model
public class ClassListBean {
	
	private List<Class> classes;
	
	@EJB
	private ClassService service;
	
	@PostConstruct
	private void init() {
		classes = service.findAll();
	}

	public List<Class> getClasses() {
		return classes;
	}

	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}

}
