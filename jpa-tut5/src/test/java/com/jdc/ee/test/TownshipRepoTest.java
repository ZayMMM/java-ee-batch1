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

import com.jdc.ee.entity.State;
import com.jdc.ee.entity.Township;
import com.jdc.ee.repo.StateRepository;
import com.jdc.ee.repo.TownshipRepository;

public class TownshipRepoTest {

	private static EntityManagerFactory EMF;
	private EntityManager em;
	private TownshipRepository repo;

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
		repo = new TownshipRepository(em);
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
		Township tsh = new Township();
		tsh.setName("Mayangon");
		
		StateRepository stateRepo = new StateRepository(em);
		State yangon = stateRepo.find(1);
		
		tsh.setState(yangon);
		
		repo.create(tsh);
		
		assertEquals(4, tsh.getId());
	}

	/**
	 * Test Case for Find Method
	 */
	@Test
	public void test2() {
		Township tsh = repo.find(4);
		
		assertEquals("Mayangon", tsh.getName());
		assertEquals("Yangon", tsh.getState().getName());
	}

	/**
	 * Test Case for Update Method
	 */
	@Test
	public void test3() {
		Township tsh = repo.find(4);
		tsh.setName("Dala");
		
		repo.update(tsh);
		
		assertEquals("Dala", repo.find(4).getName());
	}

	/**
	 * Test Case for Delete Method
	 */
	@Test
	public void test4() {
		Township tsh = repo.find(4);
		
		repo.delete(tsh);
		
		assertNull(repo.find(4));
	}
}
