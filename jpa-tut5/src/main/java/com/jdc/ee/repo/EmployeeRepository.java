package com.jdc.ee.repo;

import javax.persistence.EntityManager;

import com.jdc.ee.entity.Employee;

public class EmployeeRepository extends AbstractRepository<Employee> {

	public EmployeeRepository(EntityManager em) {
		super(Employee.class, em);
	}

	@Override
	public void create(Employee t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee find(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Employee t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Employee t) {
		// TODO Auto-generated method stub
		
	}

}