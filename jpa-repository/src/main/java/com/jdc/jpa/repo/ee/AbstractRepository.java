package com.jdc.jpa.repo.ee;

import java.util.List;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.jdc.jpa.repo.Joinable;
import com.jdc.jpa.repo.Searchable;
import com.jdc.jpa.repo.Sortable;

public abstract class AbstractRepository<T> {

    protected abstract EntityManager entityManager();
    protected abstract Class<T> type();

    public void create(T t) {
		entityManager().persist(t);
    }

    public T find(Object id) {
    	return entityManager().find(type(), id);
    }

    public void update(T t) {
    	entityManager().merge(t);
    }

    public void delete(Object id) {
		String sql = String.format("delete from %s t where t.id = :id", type().getSimpleName());
		Query query = entityManager().createQuery(sql);
		query.setParameter("id", id);
		query.executeUpdate();
    }
    
    public List<T> find(Searchable search) {
    	TypedQuery<T> q = getQueryFromSearch(search);
    	return q.getResultList();
    }
    
    public List<T> find(Searchable search, int start, int limit) {
    	   	
    	TypedQuery<T> q = getQueryFromSearch(search);
    	
    	q.setFirstResult(start);
    	q.setMaxResults(limit);
    	
    	return q.getResultList();
    }
    
    public long findCount(Searchable search) {
    	StringBuffer sb = new StringBuffer(String.format("select count(t) from %s t ", type().getSimpleName()));
    	
    	if(null != search) {
    		
    		if(search instanceof Joinable) {
    			Joinable j = (Joinable) search;
    			sb.append(j.join());
    		}
    		
    		sb.append(search.where());
    	}
    	
    	TypedQuery<Long> q = entityManager().createQuery(sb.toString(), Long.class);
    	
    	if(null != search) {
    		for(Entry<String, Object> entry : search.params().entrySet()) {
    			q.setParameter(entry.getKey(), entry.getValue());
    		}
    	}
    	
    	return q.getSingleResult();
    }
    
    private TypedQuery<T> getQueryFromSearch(Searchable search) {
    	StringBuffer sb = new StringBuffer(String.format("select t from %s t ", type().getSimpleName()));
    	
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

    	TypedQuery<T> query = entityManager().createQuery(sb.toString(), type());

    	if(null != search) {
    		for(Entry<String, Object> entry : search.params().entrySet()) {
    			query.setParameter(entry.getKey(), entry.getValue());
    		}
    	}

    	return query;
    }
}