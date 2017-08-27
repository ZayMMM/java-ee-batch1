package com.jdc.jsf.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jdc.jpa.repo.ee.AbstractRepository;
import com.jdc.jsf.entity.Friend;

@LocalBean
@Stateless
public class FriendService extends AbstractRepository<Friend> {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager entityManager() {
		return em;
	}

	@Override
	protected Class<Friend> type() {
		return Friend.class;
	}
}
