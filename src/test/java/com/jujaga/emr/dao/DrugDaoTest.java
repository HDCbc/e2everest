package com.jujaga.emr.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jujaga.e2e.constant.Constants;
import com.jujaga.emr.model.Drug;
import com.jujaga.emr.util.EntityModelUtils;

public class DrugDaoTest {
	private static ApplicationContext context;
	private static DrugDao drugDao;

	@BeforeClass
	public static void beforeClass() {
		context = new ClassPathXmlApplicationContext(Constants.Runtime.SPRING_APPLICATION_CONTEXT);
		drugDao = context.getBean(DrugDao.class);
	}

	@Test
	public void drugTest() {
		EntityModelUtils.invokeMethodsForModelClass(new Drug());
		Drug entity = (Drug) EntityModelUtils.generateTestDataForModelClass(new Drug());
		drugDao.persist(entity);
		assertNotNull(entity.getId());
	}

	@Test
	public void findByDemographicIdTest() {
		List<Drug> drugs = drugDao.findByDemographicId(Constants.Runtime.VALID_DEMOGRAPHIC);
		assertNotNull(drugs);
		assertEquals(2, drugs.size());

		List<Drug> drugs2 = drugDao.findByDemographicId(Constants.Runtime.VALID_DEMOGRAPHIC, false);
		assertNotNull(drugs2);
		assertEquals(2, drugs2.size());
	}
}
