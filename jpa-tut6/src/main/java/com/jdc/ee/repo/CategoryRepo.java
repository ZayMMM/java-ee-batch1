package com.jdc.ee.repo;

import javax.persistence.EntityManager;

import com.jdc.ee.entity.Category;
import com.jdc.jpa.repo.AbstractRepository;

public class CategoryRepo extends AbstractRepository<Category> {

	public CategoryRepo(EntityManager em) {
		super(em, Category.class);
	}

}
