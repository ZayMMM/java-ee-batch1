package com.jdc.ee.repo;

import javax.persistence.EntityManager;

public abstract class AbstractRepository<T> {

    public AbstractRepository(Class<T> type, EntityManager em) {
    	this.type = type;
    	this.em = em;
    }

    protected Class<T> type;

    protected EntityManager em;

    public abstract void create(T t);

    public abstract T find(Object id);

    public abstract void update(T t);

    public abstract void delete(T t);

}