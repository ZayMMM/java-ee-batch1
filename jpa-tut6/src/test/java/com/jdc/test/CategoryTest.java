package com.jdc.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jdc.ee.entity.Category;
import com.jdc.ee.repo.CategoryRepo;

public class CategoryTest {
	
	private static EntityManagerFactory EMF;
	private EntityManager em;
	private CategoryRepo repo;

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
		repo = new CategoryRepo(em);
	}

	@After
	public void tearDown() throws Exception {
		em.close();
	}

	@Test
	public void test1() {
		List<Category> list = repo.getAll();
		assertNotNull(list);
		assertEquals(9, list.size());
	}
	
	@Test
	public void test2() {
		assertEquals(9, repo.getAllCount());
	}
	
	@Test
	public void test3() {
		List<Category> list = repo.findByName("Gas Fees");
		assertEquals(1, list.size());
		assertEquals(3, list.get(0).getId());
	}

}
