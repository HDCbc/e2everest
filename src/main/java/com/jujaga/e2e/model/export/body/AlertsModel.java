package com.jujaga.e2e.model.export.body;

import org.marc.everest.datatypes.ED;
import org.marc.everest.datatypes.II;
import org.marc.everest.datatypes.NullFlavor;
import org.marc.everest.datatypes.TS;
import org.marc.everest.datatypes.generic.CD;
import org.marc.everest.datatypes.generic.CE;
import org.marc.everest.datatypes.generic.IVL;
import org.marc.everest.datatypes.generic.SET;
import org.marc.everest.rmim.uv.cdar2.vocabulary.ActStatus;
import org.marc.everest.rmim.uv.cdar2.vocabulary.x_BasicConfidentialityKind;

import com.jujaga.e2e.constant.Constants;
import com.jujaga.e2e.util.EverestUtils;
import com.jujaga.emr.model.CaseManagementNote;

public class AlertsModel {
	private CaseManagementNote alert;

	private SET<II> ids;
	private CD<String> code;
	private ED text;
	private ActStatus statusCode;
	private IVL<TS> effectiveTime;
	private CE<x_BasicConfidentialityKind> confidentiality;

	public AlertsModel(CaseManagementNote alert) {
		if(alert == null) {
			this.alert = new CaseManagementNote();
		} else {
			this.alert = alert;
		}

		setIds();
		setCode();
		setText();
		setStatusCode();
		setEffectiveTime();
		setConfidentiality();
	}

	public String getTextSummary() {
		StringBuilder sb = new StringBuilder();

		if(alert.getObservation_date() != null) {
			sb.append(alert.getObservation_date());
		}
		if(!EverestUtils.isNullorEmptyorWhitespace(alert.getNote())) {
			sb.append(" ".concat(alert.getNote().replaceAll("\\\\n", "\n")));
		}

		return sb.toString();
	}

	public SET<II> getIds() {
		return ids;
	}

	private void setIds() {
		this.ids = EverestUtils.buildUniqueId(Constants.IdPrefixes.Alerts, alert.getId());
	}

	public CD<String> getCode() {
		return code;
	}

	public void setCode() {
		CD<String> code = new CD<String>();
		code.setNullFlavor(NullFlavor.NoInformation);
		this.code = code;
	}

	public ED getText() {
		return text;
	}

	private void setText() {
		ED text = new ED();
		if(!EverestUtils.isNullorEmptyorWhitespace(alert.getNote())) {
			text.setData(alert.getNote());
		} else {
			text.setNullFlavor(NullFlavor.NoInformation);
		}
		this.text = text;
	}

	public ActStatus getStatusCode() {
		return statusCode;
	}

	private void setStatusCode() {
		if(alert.isArchived() != null && !alert.isArchived()) {
			this.statusCode = ActStatus.Active;
		} else {
			this.statusCode = ActStatus.Completed;
		}
	}

	public IVL<TS> getEffectiveTime() {
		return effectiveTime;
	}

	private void setEffectiveTime() {
		IVL<TS> ivl = new IVL<TS>();
		TS startTime = EverestUtils.buildTSFromDate(alert.getObservation_date());
		if(startTime != null) {
			ivl.setLow(startTime);
		} else {
			ivl.setNullFlavor(NullFlavor.NoInformation);
		}

		this.effectiveTime = ivl;
	}

	public CE<x_BasicConfidentialityKind> getConfidentiality() {
		return confidentiality;
	}

	private void setConfidentiality() {
		this.confidentiality = new CE<x_BasicConfidentialityKind>(x_BasicConfidentialityKind.Normal);
	}
}
