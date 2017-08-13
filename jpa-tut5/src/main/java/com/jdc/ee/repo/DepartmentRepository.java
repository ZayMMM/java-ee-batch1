package com.jdc.ee.repo;

import javax.persistence.EntityManager;

import com.jdc.ee.entity.Department;

public class DepartmentRepository extends AbstractRepository<Department> {

	public DepartmentRepository(EntityManager em) {
		super(Department.class, em);
	}

}