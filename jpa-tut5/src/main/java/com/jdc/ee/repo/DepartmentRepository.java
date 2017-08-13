package com.jdc.ee.repo;

import javax.persistence.EntityManager;

import com.jdc.ee.entity.Department;

public class DepartmentRepository extends AbstractRepository<Department> {

	public DepartmentRepository(EntityManager em) {
		super(Department.class, em);
	}

	@Override
	public void create(Department t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Department find(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Department t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Department t) {
		// TODO Auto-generated method stub
		
	}


}