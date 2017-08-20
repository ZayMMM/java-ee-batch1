package com.jdc.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jdc.ee.entity.Member;
import com.jdc.ee.repo.MemberRepo;

public class MemberRepoTest {

	private static EntityManagerFactory EMF;
	private EntityManager em;
	private MemberRepo repo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		EMF = Persistence.createEntityManagerFactory("jpa-tut6");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EMF.close();
	}

	@Before
	public void setUp() throws Exception {
		em = EMF.createEntityManager();
		repo = new MemberRepo(em);
	}

	@After
	public void tearDown() throws Exception {
		em.close();
	}

	@Test
	public void test1() {
		List<Member> list = repo.getAll();
		assertNotNull(list);
		assertEquals(4, list.size());
	}

	@Test
	public void test2() {
		assertEquals(4, repo.getAllCount());
	}
	
	@Test
	public void test3() {
		List<Member> list = repo.findByName("Thidar");
		assertEquals(1, list.size());
		assertEquals(3, list.get(0).getId());
	}
	
}
