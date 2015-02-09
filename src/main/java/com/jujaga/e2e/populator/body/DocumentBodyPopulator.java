package com.jujaga.e2e.populator.body;

import java.util.ArrayList;

import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.Component2;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.Component3;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.StructuredBody;
import org.marc.everest.rmim.uv.cdar2.vocabulary.ActRelationshipHasComponent;
import org.marc.everest.rmim.uv.cdar2.vocabulary.x_BasicConfidentialityKind;

import com.jujaga.e2e.populator.AbstractPopulator;
import com.jujaga.emr.PatientExport;

public class DocumentBodyPopulator extends AbstractPopulator {
	public DocumentBodyPopulator(PatientExport patientExport) {
		patientExport.isLoaded();
	}

	@Override
	public void populate() {
		Component2 bodyComponent = new Component2();
		StructuredBody body = new StructuredBody();
		ArrayList<Component3> sectionComponents = new ArrayList<Component3>();

		bodyComponent.setTypeCode(ActRelationshipHasComponent.HasComponent);
		bodyComponent.setContextConductionInd(true);

		body.setConfidentialityCode(x_BasicConfidentialityKind.Normal);
		body.setComponent(sectionComponents);

		clinicalDocument.setComponent(bodyComponent);
	}
}