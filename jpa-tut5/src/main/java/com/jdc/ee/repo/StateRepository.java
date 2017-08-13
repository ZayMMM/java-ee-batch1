package com.jdc.ee.repo;

import javax.persistence.EntityManager;

import com.jdc.ee.entity.State;

public class StateRepository extends AbstractRepository<State> {

	public StateRepository(EntityManager em) {
		super(State.class, em);
	}
}