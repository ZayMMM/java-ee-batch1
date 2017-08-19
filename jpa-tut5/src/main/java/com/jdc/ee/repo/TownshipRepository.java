package com.jdc.ee.repo;

import javax.persistence.EntityManager;

import com.jdc.ee.entity.Township;

public class TownshipRepository extends AbstractRepository<Township> {

	public TownshipRepository(EntityManager em) {
		super(em, Township.class);
	}

}
