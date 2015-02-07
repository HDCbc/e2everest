package com.jujaga.emr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jujaga.e2e.constant.Constants;
import com.jujaga.emr.dao.DemographicDao;
import com.jujaga.emr.model.Demographic;

public class SimpleDaoTest {
	private static ApplicationContext context;
	private static DemographicDao dao;

	@BeforeClass
	public static void beforeClass() {
		context = new ClassPathXmlApplicationContext(Constants.Runtime.SPRING_APPLICATION_CONTEXT);
		dao = context.getBean(DemographicDao.class);
	}

	@Test
	public void persistTest() {
		String test = "test";

		Demographic demographic = new Demographic();
		demographic.setFirstName(test);
		dao.persist(demographic);

		Demographic demoTest = dao.find(demographic.getDemographicNo());
		assertEquals(test, demoTest.getFirstName());
	}

	@Test
	public void updateTest() {
		String test = "test";

		Demographic demographic = new Demographic();
		demographic.setFirstName("oldtest");
		dao.persist(demographic);

		Demographic demoTest = dao.find(demographic.getDemographicNo());
		demoTest.setFirstName(test);
		dao.merge(demoTest);

		Demographic demoResult = dao.find(demoTest.getDemographicNo());
		assertEquals(test, demoResult.getFirstName());
	}

	@Test
	public void findByIdTest(){
		Integer demographicNo = Constants.Runtime.VALID_DEMOGRAPHIC;

		Demographic demoTest = dao.find(demographicNo);
		assertEquals(demographicNo, demoTest.getDemographicNo());
	}

	@Test
	public void testRemove(){Demographic demographic = new Demographic();
	demographic.setFirstName("oldtest");
	dao.persist(demographic);

	Demographic demographic2 = dao.find(demographic.getDemographicNo());
	dao.remove(demographic2);

	Demographic demoTest = dao.find(demographic2.getDemographicNo());
	assertNull(demoTest);
	}
}
