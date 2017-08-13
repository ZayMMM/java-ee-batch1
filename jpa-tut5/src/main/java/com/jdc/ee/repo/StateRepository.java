package com.jdc.ee.repo;

import javax.persistence.EntityManager;

import com.jdc.ee.entity.State;

public class StateRepository extends AbstractRepository<State> {

	public StateRepository(EntityManager em) {
		super(State.class, em);
	}

	@Override
	public void create(State t) {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
	}

	@Override
	public State find(Object id) {
		return em.find(type, id);
	}

	@Override
	public void update(State t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
	}

	@Override
	public void delete(State t) {
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
	}
}