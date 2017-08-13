package com.jdc.ee.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jdc.ee.repo.StateRepository;

public class StateRepoTest {
	
	private static EntityManagerFactory EMF;
	private EntityManager em;
	private StateRepository repo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		EMF = Persistence.createEntityManagerFactory("jpa-tut5");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EMF.close();
	}

	@Before
	public void setUp() throws Exception {
		em = EMF.createEntityManager();
		repo = new StateRepository(em);
	}

	@After
	public void tearDown() throws Exception {
		em.close();
	}
	
	
	/**
	 * Test Case for Create Method
	 */
	@Test
	public void test1() {
		fail("Not yet implemented");
	}

	/**
	 * Test Case for Find Method
	 */
	@Test
	public void test2() {
		fail("Not yet implemented");
	}

	/**
	 * Test Case for Update Method
	 */
	@Test
	public void test3() {
		fail("Not yet implemented");
	}

	/**
	 * Test Case for Delete Method
	 */
	@Test
	public void test4() {
		fail("Not yet implemented");
	}
}
