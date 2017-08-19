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

import com.jdc.ee.entity.Address;
import com.jdc.ee.entity.State;
import com.jdc.ee.entity.Township;
import com.jdc.ee.repo.AddressRepository;

public class AddressRepoTest {
	
	private static EntityManagerFactory EMF;
	private EntityManager em;
	private AddressRepository repo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		EMF = Persistence.createEntityManagerFactory("jpa-tut5");
		
		EntityManager em = EMF.createEntityManager();
		
		em.getTransaction().begin();
		
		State state = new State();
		state.setName("Yangon");
		em.persist(state);
		
		Township tsh = new Township();
		tsh.setName("Kamayut");
		tsh.setState(state);
		
		em.persist(tsh);
		
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
		repo = new AddressRepository(em);
	}

	@After
	public void tearDown() throws Exception {
		em.close();
	}

	@Test
	public void test1() {
		
		Address add = new Address();
		add.setBuilding("No 52B/5F Between San Yeik Nyein 3 & 4 Street");
		add.setStreet("Insein Road");
		add.setTownship(em.find(Township.class, 1));
		
		repo.create(add);
		
		assertEquals(1L, add.getId());
		
	}

	@Test
	public void test2() {
		Address add = repo.find(1L);
		
		assertEquals("No 52B/5F Between San Yeik Nyein 3 & 4 Street", add.getBuilding());
		assertEquals("Insein Road", add.getStreet());
		assertEquals("Kamayut", add.getTownship().getName());
		assertEquals("Yangon", add.getTownship().getState().getName());
	}

	@Test
	public void test3() {
		
		Address add = repo.find(1L);
		add.setBuilding("Building No1");
		
		repo.update(add);
		
		assertEquals("Building No1", repo.find(1L).getBuilding());
	}

	@Test
	public void test4() {
		repo.delete(repo.find(1L));
		assertNull(repo.find(1L));
	}
}
