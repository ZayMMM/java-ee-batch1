package com.jdc.ee.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jdc.ee.entity.Address;
import com.jdc.ee.entity.Department;
import com.jdc.ee.entity.State;
import com.jdc.ee.entity.Township;
import com.jdc.ee.repo.DepartmentRepository;

public class DepartmentRepoTest {

	private static EntityManagerFactory EMF;
	private EntityManager em;
	private DepartmentRepository repo;

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

		Address add = new Address();
		add.setBuilding("No 52B/5F Between San Yeik Nyein 3 & 4 Street");
		add.setStreet("Insein Road");
		add.setTownship(tsh);
		
		em.persist(add);
		
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
		repo = new DepartmentRepository(em);
	}

	@After
	public void tearDown() throws Exception {
		em.close();
	}
	
	@Test
	public void test1() {
		Department d = new Department();
		d.setName("IT Department");
		d.setPhone("0987667888");
		d.setAddress(em.find(Address.class, 1L));
		
		repo.create(d);
		
		assertEquals(1, d.getId());
	}

	@Test
	public void test2() {
		Department d = repo.find(1);
		assertNotNull(d);
		assertEquals("IT Department", d.getName());
		assertEquals("0987667888", d.getPhone());
		
		assertNotNull(d.getAddress());
		assertEquals("Kamayut", d.getAddress().getTownship().getName());
		assertEquals("Yangon", d.getAddress().getTownship().getState().getName());
	}
	
	@Test
	public void test3() {
		Department d = repo.find(1);
		d.setName("Marketing");
		repo.update(d);
		
		assertEquals("Marketing", repo.find(1).getName());
	}
	
	@Test
	public void test4() {
		repo.delete(repo.find(1));
		assertNull(repo.find(1));
	}
}
