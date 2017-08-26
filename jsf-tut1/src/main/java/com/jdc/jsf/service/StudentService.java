package com.jdc.jsf.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jdc.jsf.entity.Student;

@LocalBean
@Stateless
public class StudentService {

	@PersistenceContext
	private EntityManager em;
	
	public void create(Student s) {
		em.persist(s);
	}
	
	public List<Student> findAll() {
		return em.createNamedQuery("Student.findAll", Student.class)
				.getResultList();
	}
}
