package com.jujaga.e2e.populator.header;

import java.util.ArrayList;
import java.util.Arrays;

import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.Patient;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.PatientRole;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.RecordTarget;
import org.marc.everest.rmim.uv.cdar2.vocabulary.ContextControl;

import com.jujaga.e2e.model.export.header.RecordTargetModel;
import com.jujaga.e2e.populator.Populator;

public class RecordTargetPopulator extends Populator {
	private final RecordTargetModel recordTargetModel;

	public RecordTargetPopulator(Integer demographicNo) {
		recordTargetModel = new RecordTargetModel(demographicNo);
	}

	@Override
	public void populate() {
		RecordTarget recordTarget = new RecordTarget();
		PatientRole patientRole = new PatientRole();
		Patient patient = new Patient();

		recordTarget.setContextControlCode(ContextControl.OverridingPropagating);
		recordTarget.setPatientRole(patientRole);

		patientRole.setId(recordTargetModel.getIds());
		patientRole.setAddr(recordTargetModel.getAddresses());
		patientRole.setTelecom(recordTargetModel.getTelecoms());
		patientRole.setPatient(patient);

		patient.setName(recordTargetModel.getNames());
		patient.setAdministrativeGenderCode(recordTargetModel.getGender());
		patient.setBirthTime(recordTargetModel.getBirthDate());
		patient.setLanguageCommunication(recordTargetModel.getLanguages());

		clinicalDocument.setRecordTarget(new ArrayList<RecordTarget>(Arrays.asList(recordTarget)));
	}
}
