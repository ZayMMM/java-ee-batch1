package com.jdc.ee.repo;

import javax.persistence.EntityManager;

public abstract class AbstractRepository<T> {

    public AbstractRepository(Class<T> type, EntityManager em) {
    	this.type = type;
    	this.em = em;
    }

    protected Class<T> type;

    protected EntityManager em;

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
		em.remove(t);
		em.getTransaction().commit();
    }

}