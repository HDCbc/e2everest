package com.jujaga.e2e.model.export.body;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.marc.everest.datatypes.ANY;
import org.marc.everest.datatypes.ED;
import org.marc.everest.datatypes.II;
import org.marc.everest.datatypes.NullFlavor;
import org.marc.everest.datatypes.PQ;
import org.marc.everest.datatypes.ST;
import org.marc.everest.datatypes.TS;
import org.marc.everest.datatypes.generic.CD;
import org.marc.everest.datatypes.generic.IVL;
import org.marc.everest.datatypes.generic.SET;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.Author;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.Component4;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.Observation;
import org.marc.everest.rmim.uv.cdar2.vocabulary.ActClassObservation;
import org.marc.everest.rmim.uv.cdar2.vocabulary.ActRelationshipHasComponent;
import org.marc.everest.rmim.uv.cdar2.vocabulary.ActStatus;
import org.marc.everest.rmim.uv.cdar2.vocabulary.x_ActMoodDocumentObservation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jujaga.e2e.constant.Constants;
import com.jujaga.e2e.constant.Mappings;
import com.jujaga.e2e.constant.BodyConstants.ClinicallyMeasuredObservations;
import com.jujaga.e2e.util.EverestUtils;
import com.jujaga.emr.dao.MeasurementDao;
import com.jujaga.emr.model.Measurement;

public class ClinicallyMeasuredObservationsModelTest {
	private static ApplicationContext context;
	private static MeasurementDao dao;
	private static Measurement measurement;
	private static ClinicallyMeasuredObservationsModel cmoModel;

	private static Measurement nullMeasurement;
	private static ClinicallyMeasuredObservationsModel nullCmoModel;

	@BeforeClass
	public static void beforeClass() {
		Logger.getRootLogger().setLevel(Level.FATAL);
		context = new ClassPathXmlApplicationContext(Constants.Runtime.SPRING_APPLICATION_CONTEXT);
		dao = context.getBean(MeasurementDao.class);
	}

	@Before
	public void before() {
		measurement = dao.find(Constants.Runtime.VALID_MEASUREMENT);
		cmoModel = new ClinicallyMeasuredObservationsModel(measurement);
		nullMeasurement = new Measurement();
		nullCmoModel = new ClinicallyMeasuredObservationsModel(nullMeasurement);
	}

	@Test
	public void clinicallyMeasuredObservationsModelNullTest() {
		assertNotNull(new ClinicallyMeasuredObservationsModel(null));
	}

	@Test
	public void textSummaryTest() {
		String text = cmoModel.getTextSummary();
		assertNotNull(text);
	}

	@Test
	public void textSummaryNullTest() {
		String text = nullCmoModel.getTextSummary();
		assertNotNull(text);
	}

	@Test
	public void idTest() {
		SET<II> ids = cmoModel.getIds();
		assertNotNull(ids);

		II id = ids.get(0);
		assertNotNull(id);
		assertEquals(Constants.EMR.EMR_OID, id.getRoot());
		assertEquals(Constants.EMR.EMR_VERSION, id.getAssigningAuthorityName());
		assertFalse(EverestUtils.isNullorEmptyorWhitespace(id.getExtension()));
		assertTrue(id.getExtension().contains(Constants.IdPrefixes.ClinicalMeasuredObservations.toString()));
		assertTrue(id.getExtension().contains(Constants.Runtime.VALID_MEASUREMENT.toString()));
	}

	@Test
	public void idNullTest() {
		SET<II> ids = nullCmoModel.getIds();
		assertNotNull(ids);
	}

	@Test
	public void codeTest() {
		CD<String> code = cmoModel.getCode();
		assertNotNull(code);
		assertTrue(code.isNull());
		assertEquals(NullFlavor.NoInformation, code.getNullFlavor().getCode());
	}

	@Test
	public void codeNullTest() {
		CD<String> code = nullCmoModel.getCode();
		assertNotNull(code);
		assertTrue(code.isNull());
		assertEquals(NullFlavor.NoInformation, code.getNullFlavor().getCode());
	}

	@Test
	public void statusCodeTest() {
		ActStatus status = cmoModel.getStatusCode();
		assertNotNull(status);
		assertEquals(ActStatus.Completed, status);
	}

	@Test
	public void statusCodeNullTest() {
		ActStatus status = nullCmoModel.getStatusCode();
		assertNotNull(status);
		assertEquals(ActStatus.Completed, status);
	}

	@Test
	public void authorTest() {
		ArrayList<Author> authors = cmoModel.getAuthor();
		assertNotNull(authors);
		assertEquals(1, authors.size());
	}

	@Test
	public void authorNullTest() {
		ArrayList<Author> authors = nullCmoModel.getAuthor();
		assertNotNull(authors);
		assertEquals(1, authors.size());
	}

	@Test
	public void componentTest() {
		assertNotNull(cmoModel.new ComponentObservation().getComponent(measurement));
		componentStructureTestHelper(cmoModel);
	}

	@Test
	public void componentBPTest() {
		measurement.setType(ClinicallyMeasuredObservations.BLOOD_PRESSURE_CODE);
		measurement.setDataField("130/85");

		assertNotNull(cmoModel.new ComponentObservation().getComponent(measurement));
		componentStructureTestHelper(cmoModel);

		assertEquals(2, cmoModel.getComponents().size());
	}

	@Test
	public void componentNullTest() {
		assertNotNull(nullCmoModel.new ComponentObservation().getComponent(null));
		componentStructureTestHelper(nullCmoModel);
	}

	private Observation componentStructureTestHelper(ClinicallyMeasuredObservationsModel model) {
		ArrayList<Component4> components = model.getComponents();
		assertNotNull(components);
		assertTrue(components.size() > 0);

		Component4 component = components.get(0);
		assertNotNull(component);
		assertEquals(ActRelationshipHasComponent.HasComponent, component.getTypeCode().getCode());
		assertTrue(component.getContextConductionInd().toBoolean());

		Observation observation = component.getClinicalStatementIfObservation();
		assertNotNull(observation);
		assertEquals(ActClassObservation.OBS, observation.getClassCode().getCode());
		assertEquals(x_ActMoodDocumentObservation.Eventoccurrence, observation.getMoodCode().getCode());

		assertNotNull(observation.getId());
		assertNotNull(observation.getCode());

		return observation;
	}

	private Observation componentObservationHelper(ClinicallyMeasuredObservationsModel model) {
		ArrayList<Component4> components = model.getComponents();
		Component4 component = components.get(0);
		return component.getClinicalStatementIfObservation();
	}

	@Test
	public void componentIdTest() {
		SET<II> ids = componentObservationHelper(cmoModel).getId();
		assertNotNull(ids);

		II id = ids.get(0);
		assertNotNull(id);
		assertEquals(Constants.EMR.EMR_OID, id.getRoot());
		assertEquals(Constants.EMR.EMR_VERSION, id.getAssigningAuthorityName());
		assertFalse(EverestUtils.isNullorEmptyorWhitespace(id.getExtension()));
		assertTrue(id.getExtension().contains(Constants.IdPrefixes.ClinicalMeasuredObservations.toString()));
		assertTrue(id.getExtension().contains(Constants.Runtime.VALID_MEASUREMENT.toString()));
	}

	@Test
	public void componentIdNullTest() {
		SET<II> ids = componentObservationHelper(nullCmoModel).getId();
		assertNotNull(ids);
	}

	@Test
	public void componentCodeTest() {
		CD<String> code = componentObservationHelper(cmoModel).getCode();
		assertNotNull(code);
		assertEquals(Mappings.measurementCodeMap.get(measurement.getType()), code.getCode());
		assertEquals(Constants.CodeSystems.LOINC_OID, code.getCodeSystem());
		assertEquals(Constants.CodeSystems.LOINC_NAME, code.getCodeSystemName());
		assertEquals(Constants.CodeSystems.LOINC_VERSION, code.getCodeSystemVersion());
	}

	@Test
	public void componentCodeNullTest() {
		CD<String> code = componentObservationHelper(nullCmoModel).getCode();
		assertNotNull(code);
		assertTrue(code.isNull());
		assertEquals(NullFlavor.Unknown, code.getNullFlavor().getCode());
	}

	@Test
	public void componentTextTest() {
		ED text = componentObservationHelper(cmoModel).getText();
		assertNotNull(text);
		assertFalse(new String(text.getData()).isEmpty());
	}

	@Test
	public void componentTextNullTest() {
		ED text = componentObservationHelper(nullCmoModel).getText();
		assertNull(text);
	}

	@Test
	public void componentEffectiveTimeTest() {
		IVL<TS> ivl = componentObservationHelper(cmoModel).getEffectiveTime();
		assertNotNull(ivl);
		assertEquals(EverestUtils.buildTSFromDate(measurement.getDateObserved(), TS.SECONDNOTIMEZONE), ivl.getLow());
	}

	@Test
	public void componentEffectiveTimeNullTest() {
		IVL<TS> ivl = componentObservationHelper(nullCmoModel).getEffectiveTime();
		assertNull(ivl);
	}

	@Test
	public void componentValuePQTest() {
		String dataField = measurement.getDataField();
		String unit = Mappings.measurementUnitMap.get(measurement.getType());

		ANY value = componentObservationHelper(cmoModel).getValue();
		assertNotNull(value);
		assertEquals(PQ.class, value.getDataType());

		PQ pq = (PQ) value;
		assertEquals(new BigDecimal(dataField), pq.getValue());
		assertEquals(unit.replaceAll("\\s","_"), pq.getUnit());
	}

	@Test
	public void componentValueSTValueUnitTest() {
		measurement.setDataField("test");
		String dataField = measurement.getDataField();
		String unit = Mappings.measurementUnitMap.get(measurement.getType());

		ANY value = componentObservationHelper(cmoModel).getValue();
		assertNotNull(value);
		assertEquals(ST.class, value.getDataType());

		ST st = (ST) value;
		assertEquals(dataField.concat(" ").concat(unit), st.getValue());
	}

	@Test
	public void componentValueSTValueOnlyTest() {
		measurement.setType(null);
		String dataField = measurement.getDataField();

		ANY value = componentObservationHelper(cmoModel).getValue();
		assertNotNull(value);
		assertEquals(ST.class, value.getDataType());

		ST st = (ST) value;
		assertEquals(dataField, st.getValue());
	}

	@Test
	public void componentValueNullTest() {
		ANY value = componentObservationHelper(nullCmoModel).getValue();
		assertNull(value);
	}
}
