package com.jujaga.e2e.model.export.header;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.marc.everest.datatypes.ENXP;
import org.marc.everest.datatypes.EntityNamePartType;
import org.marc.everest.datatypes.EntityNameUse;
import org.marc.everest.datatypes.II;
import org.marc.everest.datatypes.NullFlavor;
import org.marc.everest.datatypes.PN;
import org.marc.everest.datatypes.TEL;
import org.marc.everest.datatypes.generic.SET;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.AuthoringDevice;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jujaga.e2e.constant.Constants;
import com.jujaga.e2e.util.EverestUtils;
import com.jujaga.emr.dao.ProviderDao;
import com.jujaga.emr.model.Provider;

public class AuthorModelTest {
	private static ApplicationContext context;
	private static ProviderDao dao;
	private static Provider provider;
	private static AuthorModel authorModel;

	private static Provider nullProvider;
	private static AuthorModel nullAuthorModel;

	private static final String test = "test";

	@BeforeClass
	public static void beforeClass() {
		context = new ClassPathXmlApplicationContext(Constants.Runtime.SPRING_APPLICATION_CONTEXT);
		dao = context.getBean(ProviderDao.class);
		provider = dao.find(Constants.Runtime.VALID_PROVIDER);
		authorModel = new AuthorModel(provider);

		nullProvider = new Provider();
		nullAuthorModel = new AuthorModel(nullProvider);
	}

	@Test
	public void idTest() {
		SET<II> ids = authorModel.getIds();
		assertNotNull(ids);

		II id = ids.get(0);
		assertNotNull(id);
		assertEquals(Constants.DocumentHeader.BC_MINISTRY_OF_HEALTH_PRACTITIONER_ID_OID, id.getRoot());
		assertEquals(Constants.DocumentHeader.BC_MINISTRY_OF_HEALTH_PRACTITIONER_NAME, id.getAssigningAuthorityName());
		assertFalse(EverestUtils.isNullorEmptyorWhitespace(id.getExtension()));
		assertEquals(provider.getProviderNo().toString(), id.getExtension());
	}

	@Test
	public void idSecondaryTest() {
		Provider provider1 = new Provider();
		provider1.setOhipNo(test);
		AuthorModel model1 = new AuthorModel(provider1);

		SET<II> ids1 = model1.getIds();
		assertNotNull(ids1);

		II id1 = ids1.get(0);
		assertNotNull(id1);
		assertFalse(EverestUtils.isNullorEmptyorWhitespace(id1.getExtension()));
		assertEquals(provider1.getOhipNo().toString(), id1.getExtension());

		Provider provider2 = new Provider();
		provider2.setProviderNo(1);
		AuthorModel model2 = new AuthorModel(provider2);

		SET<II> ids2 = model2.getIds();
		assertNotNull(ids2);

		II id2 = ids2.get(0);
		assertNotNull(id2);
		assertFalse(EverestUtils.isNullorEmptyorWhitespace(id2.getExtension()));
		assertEquals(provider2.getProviderNo().toString(), id2.getExtension());
	}

	@Test
	public void idNullTest() {
		SET<II> ids = nullAuthorModel.getIds();
		assertNotNull(ids);

		II id = ids.get(0);
		assertNotNull(id);
		assertTrue(id.isNull());
		assertEquals(NullFlavor.NoInformation, id.getNullFlavor().getCode());
	}

	@Test
	public void telecomFullTest() {
		SET<TEL> telecoms = authorModel.getTelecoms();
		assertNotNull(telecoms);
		assertEquals(3, telecoms.size());

		TEL tel0 = telecoms.get(0);
		assertNotNull(tel0);
		assertTrue(TEL.isValidPhoneFlavor(tel0));
		assertEquals("tel:" + provider.getPhone().replaceAll("-", ""), tel0.getValue());

		TEL tel1 = telecoms.get(1);
		assertNotNull(tel1);
		assertTrue(TEL.isValidPhoneFlavor(tel1));
		assertEquals("tel:" + provider.getWorkPhone().replaceAll("-", ""), tel1.getValue());

		TEL tel2 = telecoms.get(2);
		assertNotNull(tel2);
		assertTrue(TEL.isValidEMailFlavor(tel2));
		assertEquals("mailto:" + provider.getEmail(), tel2.getValue());
	}

	@Test
	public void telecomNullTest() {
		SET<TEL> telecoms = nullAuthorModel.getTelecoms();
		assertNull(telecoms);
	}

	@Test
	public void personFullTest() {
		Person person = authorModel.getPerson();
		assertNotNull(person);

		SET<PN> names = person.getName();
		assertNotNull(names);
		assertEquals(1, names.size());

		PN name = names.get(0);
		assertNotNull(name);
		assertEquals(EntityNameUse.OfficialRecord, name.getUse().get(0).getCode());

		List<ENXP> nameParts = name.getParts();
		assertNotNull(nameParts);
		assertEquals(2, nameParts.size());
		assertTrue(nameParts.contains(new ENXP(provider.getFirstName(), EntityNamePartType.Given)));
		assertTrue(nameParts.contains(new ENXP(provider.getLastName(), EntityNamePartType.Family)));
	}

	@Test
	public void personNullTest() {
		Person person = nullAuthorModel.getPerson();
		assertNull(person);
	}

	@Test
	public void deviceTest() {
		AuthoringDevice device = authorModel.getDevice();
		assertNotNull(device);	
		assertEquals(Constants.EMR.EMR_VERSION, device.getSoftwareName().getValue());
	}
}
