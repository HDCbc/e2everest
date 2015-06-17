package com.jujaga.e2e.model.export.body;

import java.util.ArrayList;
import java.util.Arrays;

import org.marc.everest.datatypes.BL;
import org.marc.everest.datatypes.ENXP;
import org.marc.everest.datatypes.II;
import org.marc.everest.datatypes.NullFlavor;
import org.marc.everest.datatypes.PN;
import org.marc.everest.datatypes.TS;
import org.marc.everest.datatypes.generic.CD;
import org.marc.everest.datatypes.generic.CE;
import org.marc.everest.datatypes.generic.IVL;
import org.marc.everest.datatypes.generic.SET;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.EntryRelationship;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.Observation;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.Participant2;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.ParticipantRole;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.PlayingEntity;
import org.marc.everest.rmim.uv.cdar2.vocabulary.ActStatus;
import org.marc.everest.rmim.uv.cdar2.vocabulary.ContextControl;
import org.marc.everest.rmim.uv.cdar2.vocabulary.EntityClassRoot;
import org.marc.everest.rmim.uv.cdar2.vocabulary.ParticipationType;
import org.marc.everest.rmim.uv.cdar2.vocabulary.x_ActMoodDocumentObservation;
import org.marc.everest.rmim.uv.cdar2.vocabulary.x_ActRelationshipEntryRelationship;

import com.jujaga.e2e.constant.Constants;
import com.jujaga.e2e.constant.Mappings;
import com.jujaga.e2e.model.export.template.observation.LifestageObservationModel;
import com.jujaga.e2e.util.EverestUtils;
import com.jujaga.emr.model.Allergy;

public class AllergiesModel {
	private Allergy allergy;

	private SET<II> ids;
	private CD<String> code;
	private ActStatus statusCode;
	private IVL<TS> effectiveTime;
	private EntryRelationship allergyObservation;

	public AllergiesModel(Allergy allergy) {
		if(allergy == null) {
			this.allergy = new Allergy();
		} else {
			this.allergy = allergy;
		}

		setIds();
		setCode();
		setStatusCode();
		setEffectiveTime();
		setAllergyObservation();
	}

	public String getTextSummary() {
		StringBuilder sb = new StringBuilder();

		if(!EverestUtils.isNullorEmptyorWhitespace(allergy.getDescription())) {
			sb.append(allergy.getDescription());
		}
		if(allergy.getEntryDate() != null) {
			sb.append(" " + allergy.getEntryDate().toString());
		}

		return sb.toString();
	}

	public SET<II> getIds() {
		return ids;
	}

	private void setIds() {
		this.ids = EverestUtils.buildUniqueId(Constants.IdPrefixes.Allergies, allergy.getId());
	}

	public CD<String> getCode() {
		return code;
	}

	private void setCode() {
		CD<String> code = new CD<String>("48765-2", Constants.CodeSystems.LOINC_OID,
				Constants.CodeSystems.LOINC_NAME, Constants.CodeSystems.LOINC_VERSION);
		this.code = code;
	}

	public ActStatus getStatusCode() {
		return statusCode;
	}

	private void setStatusCode() {
		if(allergy.getArchived()) {
			this.statusCode = ActStatus.Completed;
		} else {
			this.statusCode = ActStatus.Active;
		}
	}

	public IVL<TS> getEffectiveTime() {
		return effectiveTime;
	}

	private void setEffectiveTime() {
		IVL<TS> ivl = new IVL<TS>();
		TS startTime = EverestUtils.buildTSFromDate(allergy.getEntryDate());
		if(startTime != null) {
			ivl.setLow(startTime);
		} else {
			ivl.setNullFlavor(NullFlavor.Unknown);
		}

		this.effectiveTime = ivl;
	}

	public EntryRelationship getAllergyObservation() {
		return allergyObservation;
	}

	private void setAllergyObservation() {
		EntryRelationship entryRelationship = new EntryRelationship(x_ActRelationshipEntryRelationship.HasComponent, new BL(true));
		Observation observation = new Observation(x_ActMoodDocumentObservation.Eventoccurrence);
		ArrayList<EntryRelationship> entryRelationships = new ArrayList<EntryRelationship>();

		observation.setCode(getAdverseEventCode());
		observation.setEffectiveTime(getOnsetDate());
		observation.setParticipant(getAllergen());

		entryRelationships.add(getAllergenGroup());
		entryRelationships.add(getLifestage());

		observation.setEntryRelationship(entryRelationships);
		entryRelationship.setClinicalStatement(observation);

		this.allergyObservation = entryRelationship;
	}

	protected CD<String> getAdverseEventCode() {
		CD<String> code = new CD<String>();
		if(Mappings.reactionTypeCode.containsKey(allergy.getTypeCode())) {
			code.setCodeEx(Mappings.reactionTypeCode.get(allergy.getTypeCode()));
			code.setCodeSystem(Constants.CodeSystems.ACT_CODE_CODESYSTEM_OID);
			code.setCodeSystemName(Constants.CodeSystems.ACT_CODE_CODESYSTEM_NAME);
		} else {
			code.setNullFlavor(NullFlavor.Unknown);
		}

		return code;
	}

	protected IVL<TS> getOnsetDate() {
		IVL<TS> ivl = new IVL<TS>();
		TS startTime = EverestUtils.buildTSFromDate(allergy.getStartDate());
		if(startTime != null) {
			ivl.setLow(startTime);
		} else {
			ivl.setNullFlavor(NullFlavor.Unknown);
		}

		return ivl;
	}

	protected ArrayList<Participant2> getAllergen() {
		Participant2 participant = new Participant2(ParticipationType.Consumable, ContextControl.OverridingPropagating);
		ParticipantRole participantRole = new ParticipantRole(new CD<String>(Constants.RoleClass.MANU.toString()));
		PlayingEntity playingEntity = new PlayingEntity(EntityClassRoot.ManufacturedMaterial);

		CE<String> code = new CE<String>();
		if(!EverestUtils.isNullorEmptyorWhitespace(allergy.getRegionalIdentifier())) {
			code.setCodeEx(allergy.getRegionalIdentifier());
			code.setCodeSystem(Constants.CodeSystems.DIN_OID);
			code.setCodeSystemName(Constants.CodeSystems.DIN_NAME);
		} else {
			code.setNullFlavor(NullFlavor.NoInformation);
		}

		SET<PN> names = new SET<PN>(new PN(null, Arrays.asList(new ENXP(allergy.getDescription()))));

		playingEntity.setCode(code);
		playingEntity.setName(names);

		participantRole.setPlayingEntityChoice(playingEntity);
		participant.setParticipantRole(participantRole);

		return new ArrayList<Participant2>(Arrays.asList(participant));
	}

	protected EntryRelationship getAllergenGroup() {
		EntryRelationship entryRelationship = new EntryRelationship(x_ActRelationshipEntryRelationship.HasComponent, new BL(true));
		Observation observation = new Observation(x_ActMoodDocumentObservation.Eventoccurrence);

		CD<String> code = new CD<String>("ALRGRP", Constants.CodeSystems.ACT_CODE_CODESYSTEM_OID);
		code.setCodeSystemName(Constants.CodeSystems.ACT_CODE_CODESYSTEM_NAME);

		CD<String> value = new CD<String>();
		value.setNullFlavor(NullFlavor.NoInformation);

		observation.setCode(code);
		observation.setValue(value);

		entryRelationship.setClinicalStatement(observation);
		return entryRelationship;
	}

	protected EntryRelationship getLifestage() {
		return new LifestageObservationModel().getEntryRelationship(allergy.getLifeStage());
	}
}
