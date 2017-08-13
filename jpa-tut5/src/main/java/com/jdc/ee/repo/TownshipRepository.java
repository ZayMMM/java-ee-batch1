package com.jdc.ee.repo;

import javax.persistence.EntityManager;

import com.jdc.ee.entity.Township;

public class TownshipRepository extends AbstractRepository<Township> {

	public TownshipRepository(EntityManager em) {
		super(Township.class, em);
	}

	@Override
	public void create(Township t) {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
	}

	@Override
	public Township find(Object id) {
		return em.find(type, id);
	}

	@Override
	public void update(Township t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
	}

	@Override
	public void delete(Township t) {
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
	}


}