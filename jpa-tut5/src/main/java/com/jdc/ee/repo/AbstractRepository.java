package com.jdc.ee.repo;

import javax.persistence.EntityManager;

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

}