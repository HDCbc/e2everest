package com.jujaga.e2e.populator.body;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.ClinicalStatement;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.Observation;
import org.marc.everest.rmim.uv.cdar2.vocabulary.x_ActMoodDocumentObservation;

import com.jujaga.e2e.constant.BodyConstants.Labs;

public class LabsPopulatorTest extends AbstractBodyPopulatorTest {
	@BeforeClass
	public static void beforeClass() {
		setupClass(Labs.getConstants());
	}

	@Test
	public void problemsComponentSectionTest() {
		componentSectionTest();
	}

	@Test
	public void problemsEntryCountTest() {
		entryCountTest(0);
	}

	@Ignore
	@Test
	public void problemsEntryStructureTest() {
		entryStructureTest();
	}

	@Ignore
	@Test
	public void problemsClinicalStatementTest() {
		ClinicalStatement clinicalStatement = component.getSection().getEntry().get(0).getClinicalStatement();
		assertNotNull(clinicalStatement);
		assertTrue(clinicalStatement.isPOCD_MT000040UVObservation());

		Observation observation = (Observation) clinicalStatement;
		assertEquals(x_ActMoodDocumentObservation.Eventoccurrence, observation.getMoodCode().getCode());
		assertNotNull(observation.getId());
		assertNotNull(observation.getCode());
		assertNotNull(observation.getStatusCode());
	}
}