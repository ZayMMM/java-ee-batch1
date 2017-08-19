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
import com.jdc.ee.repo.TownshipRepository;

public class TownshipRepoTest {
	
	private static EntityManagerFactory EMF;
	private EntityManager em;
	private TownshipRepository repo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		EMF = Persistence.createEntityManagerFactory("jpa-tut5");
		
		EntityManager em = EMF.createEntityManager();
		State state = new State();
		state.setName("Yangon");
		
		em.getTransaction().begin();
		em.persist(state);
		em.getTransaction().commit();
		em.close();
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

	@Test
	public void test1() {
		Township kamayut = new Township();
		kamayut.setName("Kamayut");
		State yangon = em.find(State.class, 1);
		kamayut.setState(yangon);
		
		repo.create(kamayut);
		
		assertEquals(1, kamayut.getId());
	}

	@Test
	public void test2() {
		Township t = repo.find(1);
		
		assertEquals("Kamayut", t.getName());
		assertNotNull(t.getState());
		assertEquals("Yangon", t.getState().getName());
	}

	@Test
	public void test3() {
		Township t = repo.find(1);
		
		t.setName("Tarmwe");
		
		repo.update(t);
		
		assertEquals("Tarmwe", repo.find(1).getName());
	}
	
	@Test
	public void test4() {
		Township t = repo.find(1);
		
		repo.delete(t);
		
		assertNull(repo.find(1));
	}
	
}
