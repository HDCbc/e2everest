package com.jujaga.e2e.model.export.body.template;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.marc.everest.datatypes.NullFlavor;
import org.marc.everest.datatypes.SetOperator;
import org.marc.everest.datatypes.TS;
import org.marc.everest.datatypes.generic.CD;
import org.marc.everest.datatypes.generic.IVL;
import org.marc.everest.datatypes.interfaces.ISetComponent;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.EntryRelationship;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.SubstanceAdministration;
import org.marc.everest.rmim.uv.cdar2.vocabulary.x_ActRelationshipEntryRelationship;
import org.marc.everest.rmim.uv.cdar2.vocabulary.x_DocumentSubstanceMood;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jujaga.e2e.constant.BodyConstants.Medications;
import com.jujaga.e2e.constant.Constants;
import com.jujaga.e2e.model.export.body.BodyUtils;
import com.jujaga.e2e.model.export.template.MedicationPrescriptionEventModel;
import com.jujaga.emr.dao.DrugDao;
import com.jujaga.emr.model.Drug;

public class MedicationPrescriptionEventModelTest {
	private static ApplicationContext context;
	private static DrugDao dao;
	private static Drug drug;
	private static Drug nullDrug;
	private static MedicationPrescriptionEventModel mpeModel;

	@BeforeClass
	public static void beforeClass() {
		context = new ClassPathXmlApplicationContext(Constants.Runtime.SPRING_APPLICATION_CONTEXT);
		dao = context.getBean(DrugDao.class);
		mpeModel = new MedicationPrescriptionEventModel();
	}

	@Before
	public void before() {
		drug = dao.findByDemographicId(Constants.Runtime.VALID_DEMOGRAPHIC).get(0);
		nullDrug = new Drug();
	}

	@Test
	public void medicationPrescriptionEventStructureTest() {
		EntryRelationship entryRelationship = mpeModel.getEntryRelationship(drug);
		assertNotNull(entryRelationship);
		assertEquals(x_ActRelationshipEntryRelationship.HasComponent, entryRelationship.getTypeCode().getCode());
		assertTrue(entryRelationship.getContextConductionInd().toBoolean());
		assertEquals(Medications.MEDICATION_PRESCRIPTION_EVENT_TEMPLATE_ID, entryRelationship.getTemplateId().get(0).getRoot());

		SubstanceAdministration substanceAdministration = entryRelationship.getClinicalStatementIfSubstanceAdministration();
		assertNotNull(substanceAdministration);
		assertEquals(x_DocumentSubstanceMood.RQO, substanceAdministration.getMoodCode().getCode());
		assertEquals(BodyUtils.buildUniqueId(Constants.IdPrefixes.MedicationPrescriptions, drug.getId()), substanceAdministration.getId());

		CD<String> code = substanceAdministration.getCode();
		assertNotNull(code);
		assertEquals(Constants.SubstanceAdministrationType.DRUG.toString(), code.getCode());
		assertEquals(Constants.CodeSystems.ACT_CODE_CODESYSTEM_OID, code.getCodeSystem());
		assertEquals(Constants.CodeSystems.ACT_CODE_CODESYSTEM_NAME, code.getCodeSystemName());
	}

	@Test
	public void effectiveTimeTest() {
		SubstanceAdministration substanceAdministration = substanceAdministrationHelper(drug);
		ArrayList<ISetComponent<TS>> effectiveTime = substanceAdministration.getEffectiveTime();
		assertNotNull(effectiveTime);
		assertEquals(1, effectiveTime.size());

		IVL<TS> ivl = (IVL<TS>) effectiveTime.get(0);
		assertNotNull(ivl);
		assertEquals(SetOperator.Inclusive, ivl.getOperator());
		assertEquals(BodyUtils.buildTSFromDate(drug.getRxDate()), ivl.getLow());
		assertEquals(BodyUtils.buildTSFromDate(drug.getEndDate()), ivl.getHigh());
	}

	@Test
	public void nullEffectiveTimeTest() {
		nullDrug.setRxDate(null);

		SubstanceAdministration substanceAdministration = substanceAdministrationHelper(nullDrug);
		ArrayList<ISetComponent<TS>> effectiveTime = substanceAdministration.getEffectiveTime();
		assertNotNull(effectiveTime);
		assertEquals(1, effectiveTime.size());

		IVL<TS> ivl = (IVL<TS>) effectiveTime.get(0);
		assertNotNull(ivl);
		assertEquals(SetOperator.Inclusive, ivl.getOperator());
		assertTrue(ivl.getLow().isNull());
		assertEquals(NullFlavor.Unknown, ivl.getLow().getNullFlavor().getCode());
		assertNull(ivl.getHigh());
	}

	private SubstanceAdministration substanceAdministrationHelper(Drug drug) {
		EntryRelationship entryRelationship = mpeModel.getEntryRelationship(drug);
		return entryRelationship.getClinicalStatementIfSubstanceAdministration();
	}
}
