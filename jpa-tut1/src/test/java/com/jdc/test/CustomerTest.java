package com.jdc.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jdc.ees.jpa1.Customer;

public class CustomerTest {
	
	static EntityManagerFactory EMF;
	EntityManager em;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		EMF = Persistence.createEntityManagerFactory("mydb");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EMF.close();
	}

	@Before
	public void setUp() throws Exception {
		em = EMF.createEntityManager();
	}

	@Test
	public void test() {
		Customer c = new Customer();
		c.setName("Aung Aung");
		c.setEmail("aung@gmail.com");
		c.setPhone("0982727272");

		assertEquals(0, c.getId());
		
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		
		assertEquals(1, c.getId());
	}

}
