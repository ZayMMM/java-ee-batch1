package com.jdc.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jdc.ee.entity.Member;
import com.jdc.ee.jpa.repo.Searchable;
import com.jdc.ee.repo.MemberRepo;
import com.jdc.ee.repo.SearchMemberBalance;

public class MemberRepoTest {

	private static EntityManagerFactory EMF;
	private EntityManager em;
	private MemberRepo repo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		EMF = Persistence.createEntityManagerFactory("jpa-tut6");
		
		BalanceRepoTest.initData(EMF);
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
		List<Member> list = repo.find(null);
		assertNotNull(list);
		assertEquals(4, list.size());
	}

	@Test
	public void test2() {
		assertEquals(4, repo.findCount(null));
	}
	
	@Test
	public void test3() {
		List<Member> list = repo.find(new Searchable() {
			
			@Override
			public String where() {
				return "where t.name = :name";
			}
			
			@Override
			public Map<String, Object> params() {
				Map<String, Object> params = new HashMap<>();
				params.put("name", "Thidar");
				return params;
			}
		});
		assertEquals(1, list.size());
		assertEquals(3, list.get(0).getId());
	}
	
	@Test
	public void test4() {
		List<Member> list = repo.find(new SearchMemberBalance());
		assertEquals(2, list.size());
	}
	
}
