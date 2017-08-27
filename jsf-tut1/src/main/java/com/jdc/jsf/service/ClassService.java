package com.jdc.jsf.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jdc.jsf.entity.Class;

@LocalBean
@Stateless
public class ClassService {

	@PersistenceContext
	private EntityManager em;
	
	public void create(Class clz) {
		em.persist(clz);
	}
	
	public List<Class> findAll() {
		return em.createNamedQuery("Class.findAll", Class.class).getResultList();
	}

}
