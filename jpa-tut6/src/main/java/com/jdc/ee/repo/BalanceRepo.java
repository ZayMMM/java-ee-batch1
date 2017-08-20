package com.jdc.ee.repo;

import javax.persistence.EntityManager;

import com.jdc.ee.entity.Balance;
import com.jdc.ee.jpa.repo.AbstractRepository;

public class BalanceRepo extends AbstractRepository<Balance> {

	public BalanceRepo(EntityManager em) {
		super(em, Balance.class);
	}
	
}
