package com.jdc.ee.repo;

import javax.persistence.EntityManager;

import com.jdc.ee.entity.Address;

public class AddressRepository extends AbstractRepository<Address> {

	public AddressRepository(EntityManager em) {
		super(Address.class, em);
	}

	@Override
	public void create(Address t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Address find(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Address t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Address t) {
		// TODO Auto-generated method stub
		
	}

}