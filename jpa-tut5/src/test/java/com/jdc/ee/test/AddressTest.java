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

public class AddressTest {

	static EntityManagerFactory emf;
	EntityManager em;
		
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("jpa-tut5");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		emf.close();
	}
	
	@Before
	public void before() {
		em = emf.createEntityManager();
	}
	
	@After
	public void after() {
		em.close();
	}

	@Test
	public void test1() {
		
		State state = new State();
		state.setName("Nay Pyi Daw");
		
		em.getTransaction().begin();
		
		em.persist(state);
		
		em.getTransaction().commit();
		
		assertEquals(3, state.getId());
		
	}
	
	@Test
	public void test2() {
		State s1 = em.find(State.class, 1);
		
		Township tsh = new Township();
		tsh.setName("Mayangone");
		
		tsh.setState(s1);
		
		em.getTransaction().begin();
		
		em.persist(tsh);
		
		em.getTransaction().commit();
		
	}

	@Test
	public void test3() {
		State s1 = em.getReference(State.class, 1);
		Township tsh = new Township();
		tsh.setName("Yankin");
		
		tsh.setState(s1);
		
		em.getTransaction().begin();
		
		em.persist(tsh);
		
		em.getTransaction().commit();

	}
	
	@Test
	public void test4() {
		
		State pegu = new State();
		pegu.setName("Pegu");
		
		Township tsh = new Township();
		tsh.setName("Pegu");
		
		tsh.setState(pegu);
		
		em.getTransaction().begin();
		em.persist(tsh);
		em.getTransaction().commit();
	}
	
	@Test
	public void test5() {
		Township tsh = em.find(Township.class, 1);
		em.getTransaction().begin();
		em.remove(tsh);
		em.getTransaction().commit();
		
		assertNull(em.find(Township.class, 1));
	}
	
	@Test
	public void test6() {
		State s = em.find(State.class, 4);
		
		em.getTransaction().begin();
		em.detach(s);
		em.getTransaction().commit();
		em.clear();
		
		s.setName("Bago");
		
		em.getTransaction().begin();
		em.merge(s);
		em.getTransaction().commit();
	}
}
