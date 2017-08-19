package com.jdc.ee.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public abstract class AbstractRepository<T> {

    public AbstractRepository(EntityManager em, Class<T> type) {
    	this.em = em;
    	this.type = type;
    }

    protected EntityManager em;
    protected Class<T> type;

    public void create(T t) {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
    }

    public T find(Object id) {
    	return em.find(type, id);
    }

    public void update(T t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
    }

    public void delete(T t) {
		em.getTransaction().begin();
		t = em.merge(t);
		em.remove(t);
		em.getTransaction().commit();
    }
    
    public List<T> getAll() {
    	String queryName = String.format("%s.getAll", type.getSimpleName());
    	TypedQuery<T> query = em.createNamedQuery(queryName, type);
    	return query.getResultList();
    }

    public long getAllCount() {
    	String queryName = String.format("%s.getAllCount", type.getSimpleName());
    	TypedQuery<Long> query = em.createNamedQuery(queryName, Long.class);
    	return query.getSingleResult();
    }
    
    public List<T> findByName(String name) {
    	String queryName = String.format("%s.findByName", type.getSimpleName());
    	TypedQuery<T> query = em.createNamedQuery(queryName, type);
    	query.setParameter("name", name);
    	return query.getResultList();
    }
}