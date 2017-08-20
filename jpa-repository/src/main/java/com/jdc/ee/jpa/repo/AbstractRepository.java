package com.jdc.ee.jpa.repo;

import java.util.List;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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

    public void delete(Object id) {
		em.getTransaction().begin();
		
		String sql = String.format("delete from %s t where t.id = :id", type.getSimpleName());
		Query query = em.createQuery(sql);
		query.setParameter("id", id);
		query.executeUpdate();
		
		em.getTransaction().commit();
    }
    
    public List<T> find(Searchable search) {
    	
    	StringBuffer sb = new StringBuffer(String.format("select t from %s t ", type.getSimpleName()));
    	
    	if(null != search) {
    		
    		if(search instanceof Joinable) {
    			Joinable j = (Joinable) search;
    			sb.append(j.join());
    		}
    		
    		sb.append(search.where());
    		
    		if(search instanceof Sortable) {
    			Sortable s = (Sortable) search;
    			sb.append(s.sort());
    		}
    	}
    	
    	TypedQuery<T> q = em.createQuery(sb.toString(), type);
    	
    	if(null != search) {
    		for(Entry<String, Object> entry : search.params().entrySet()) {
    			q.setParameter(entry.getKey(), entry.getValue());
    		}
    	}
    	
    	return q.getResultList();
    }
    
    public List<T> find(Searchable search, int start, int limit) {
    	
    	StringBuffer sb = new StringBuffer(String.format("select t from %s t ", type.getSimpleName()));
    	
    	if(null != search) {
    		
    		if(search instanceof Joinable) {
    			Joinable j = (Joinable) search;
    			sb.append(j.join());
    		}
    		
    		sb.append(search.where());
    		
    		if(search instanceof Sortable) {
    			Sortable s = (Sortable) search;
    			sb.append(s.sort());
    		}
    	}
    	
    	TypedQuery<T> q = em.createQuery(sb.toString(), type);
    	
    	if(null != search) {
    		for(Entry<String, Object> entry : search.params().entrySet()) {
    			q.setParameter(entry.getKey(), entry.getValue());
    		}
    	}
    	
    	q.setFirstResult(start);
    	q.setMaxResults(limit);
    	
    	return q.getResultList();
    }
    
    public long findCount(Searchable search) {
    	StringBuffer sb = new StringBuffer(String.format("select count(t) from %s t ", type.getSimpleName()));
    	
    	if(null != search) {
    		
    		if(search instanceof Joinable) {
    			Joinable j = (Joinable) search;
    			sb.append(j.join());
    		}
    		
    		sb.append(search.where());
    	}
    	
    	TypedQuery<Long> q = em.createQuery(sb.toString(), Long.class);
    	
    	if(null != search) {
    		for(Entry<String, Object> entry : search.params().entrySet()) {
    			q.setParameter(entry.getKey(), entry.getValue());
    		}
    	}
    	
    	return q.getSingleResult();
    }
}