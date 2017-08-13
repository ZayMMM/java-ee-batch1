package com.jdc.ee.repo;

import javax.persistence.EntityManager;

import com.jdc.ee.entity.Address;

public class AddressRepository extends AbstractRepository<Address> {

	public AddressRepository(EntityManager em) {
		super(Address.class, em);
	}

}