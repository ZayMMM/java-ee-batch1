package com.jdc.ee.repo;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.jdc.ee.entity.Balance;

public class BalanceRepo extends AbstractRepository<Balance> {

	public BalanceRepo(EntityManager em) {
		super(em, Balance.class);
	}
	
	public List<Balance> findByFromDate(Date date) {
		return null;
	}

}
