package com.jdc.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import com.jdc.ee.entity.Balance;
import com.jdc.ee.entity.Balance.Type;
import com.jdc.ee.jpa.repo.Searchable;
import com.jdc.ee.entity.Category;
import com.jdc.ee.entity.Member;
import com.jdc.ee.repo.BalanceRepo;
import com.jdc.ee.repo.SearchBalance;

public class BalanceRepoTest {

	private static EntityManagerFactory EMF;
	private EntityManager em;
	private BalanceRepo repo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		EMF = Persistence.createEntityManagerFactory("jpa-tut6");
		
		EntityManager em = EMF.createEntityManager();
		em.getTransaction().begin();
		
		Member mem1 = em.find(Member.class, 1);
		Member mem2 = em.find(Member.class, 2);
		Member mem3 = em.find(Member.class, 3);
		
		Category cat1 = em.find(Category.class, 1);
		Category cat2 = em.find(Category.class, 2);
		Category cat3 = em.find(Category.class, 3);
		
		createBalance(em, mem1, cat1, Type.Income, 1000, "2017-08-03");
		createBalance(em, mem1, cat2, Type.Income, 2000, "2017-08-13");
		createBalance(em, mem1, cat3, Type.Income, 3000, "2017-08-23");
		
		createBalance(em, mem2, cat1, Type.Expenses, 1000, "2017-08-03");
		createBalance(em, mem2, cat2, Type.Expenses, 2000, "2017-08-13");
		createBalance(em, mem2, cat3, Type.Expenses, 3000, "2017-08-23");

		createBalance(em, mem3, cat1, Type.Expenses, 1000, "2017-08-03");
		createBalance(em, mem3, cat2, Type.Expenses, 2000, "2017-08-13");
		createBalance(em, mem3, cat3, Type.Income, 3000, "2017-08-23");

		em.getTransaction().commit();
		em.close();
	}
	
	private static void createBalance(EntityManager em, Member mem, Category cat, Type type, int ammount, String date) {
		try {
			Balance b = new Balance();
			b.setMember(mem);
			b.setCategory(cat);
			b.setAmount(ammount);
			b.setDetails("Test Balance");
			b.setType(type);
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			b.setDate(df.parse(date));
			
			em.persist(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EMF.close();
	}

	@Before
	public void setUp() throws Exception {
		em = EMF.createEntityManager();
		repo = new BalanceRepo(em);
	}

	@After
	public void tearDown() throws Exception {
		em.close();
	}

	@Test
	public void test1() {
		List<Balance> list = repo.find(null);
		assertNotNull(list);
		assertEquals(9, list.size());
	}
	
	@Test
	public void test2() {
		assertEquals(9, repo.findCount(null));
	}
	
	@Test
	public void test3() {
		List<Balance> list = repo.find(new Searchable() {
			
			@Override
			public String where() {
				return "where t.member.name = :name";
			}
			
			@Override
			public Map<String, Object> params() {
				Map<String, Object> params = new HashMap<>();
				params.put("name", "Thidar");
				return params;
			}
		});
		assertNotNull(list);
		assertEquals(3, list.size());
	}

	@Test
	public void test4() {
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 7, 13);
		
		List<Balance> list = repo.find(new SearchBalance(cal.getTime(), null, null, null));
		assertNotNull(list);
		assertEquals(6, list.size());
	}
	
	@Test
	public void test5() {
		
		SearchBalance search = new SearchBalance(null, null, null, "T");
		
		List<Balance> list = repo.find(search);
		
		assertEquals(3, list.size());
		
		assertEquals(4, repo.find(new SearchBalance(null, null, Type.Income, null)).size());
		
	}
	
	
}
