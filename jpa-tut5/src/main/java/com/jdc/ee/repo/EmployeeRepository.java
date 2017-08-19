package com.jdc.ee.repo;

import javax.persistence.EntityManager;

import com.jdc.ee.entity.Employee;

public class EmployeeRepository extends AbstractRepository<Employee> {

	public EmployeeRepository(EntityManager em) {
		super(em, Employee.class);
	}

}
