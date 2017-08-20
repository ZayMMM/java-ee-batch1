package com.jdc.ee.repo;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.jdc.ee.entity.Balance;

public class BalanceRepo extends AbstractRepository<Balance> {

	public BalanceRepo(EntityManager em) {
		super(em, Balance.class);
	}
	
	public List<Balance> findByFromDate(Date date) {
		
		TypedQuery<Balance> query = em.createNamedQuery("Balance.findByFromDate", Balance.class);
		query.setParameter("date", date);
		return query.getResultList();
	}

}
