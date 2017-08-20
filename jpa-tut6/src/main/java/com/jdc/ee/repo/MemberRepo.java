package com.jdc.ee.repo;

import javax.persistence.EntityManager;

import com.jdc.ee.entity.Member;
import com.jdc.ee.jpa.repo.AbstractRepository;

public class MemberRepo extends AbstractRepository<Member> {

	public MemberRepo(EntityManager em) {
		super(em, Member.class);
	}

}
