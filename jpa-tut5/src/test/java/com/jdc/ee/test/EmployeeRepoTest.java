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
import com.jdc.ee.entity.Employee;
import com.jdc.ee.entity.Employee.Gender;
import com.jdc.ee.entity.State;
import com.jdc.ee.entity.Township;
import com.jdc.ee.repo.EmployeeRepository;

public class EmployeeRepoTest {

	private static EntityManagerFactory EMF;
	private EntityManager em;
	private EmployeeRepository repo;

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

		Department d = new Department();
		d.setName("IT Department");
		d.setPhone("0987667888");
		d.setAddress(add);
		
		em.persist(d);
		
		Address add2 = new Address();
		add2.setBuilding("Sinyay Twin");
		add2.setStreet("Insein Road");
		add2.setTownship(tsh);
		em.persist(add2);
		
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
		repo = new EmployeeRepository(em);
	}

	@After
	public void tearDown() throws Exception {
		em.close();
	}
	
	@Test
	public void test1() {
		
		Employee emp = new Employee();
		emp.setName("Su Su");
		emp.setCode("OPM1001");
		emp.setDepartment(em.find(Department.class, 1));
		emp.setGender(Gender.Female);
		emp.setAddress(em.find(Address.class, 2L));
		
		repo.create(emp);
		
		assertNotNull(repo.find("OPM1001"));
	}
	
	@Test
	public void test2() {
		Employee emp = repo.find("OPM1001");
		assertEquals("Su Su", emp.getName());
		assertEquals("IT Department", emp.getDepartment().getName());
		assertEquals(Gender.Female, emp.getGender());
		assertEquals("Kamayut", emp.getAddress().getTownship().getName());
		assertEquals("Yangon", emp.getAddress().getTownship().getState().getName());
	}
	
	@Test
	public void test3() {
		Employee emp = repo.find("OPM1001");
		emp.setName("Su Su Lwin");
		repo.update(emp);
		
		assertEquals("Su Su Lwin", repo.find("OPM1001").getName());
	}
	
	@Test
	public void test4() {
		repo.delete(repo.find("OPM1001"));
		assertNull(repo.find("OPM1001"));
	}

}
