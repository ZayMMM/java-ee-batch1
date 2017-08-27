package com.jdc.jsf.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jdc.jsf.entity.Course;

@Stateless
@LocalBean
public class CourseService {

	@PersistenceContext
	private EntityManager em;
	
	public void create(Course course) {
		em.persist(course);
	}
	
	public List<Course> findAll() {
		return em.createNamedQuery("Course.findAll", Course.class)
				.getResultList();
	}
	
	public Course findById(int id) {
		return em.find(Course.class, id);
	}

}
