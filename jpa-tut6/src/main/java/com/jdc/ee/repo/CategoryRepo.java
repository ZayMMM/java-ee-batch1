package com.jdc.ee.repo;

import javax.persistence.EntityManager;

import com.jdc.ee.entity.Category;

public class CategoryRepo extends AbstractRepository<Category> {

	public CategoryRepo(EntityManager em) {
		super(em, Category.class);
	}

}
