package org.oscarehr.e2e.model.export.body;

import java.util.ArrayList;

import org.marc.everest.datatypes.ED;
import org.marc.everest.datatypes.II;
import org.marc.everest.datatypes.NullFlavor;
import org.marc.everest.datatypes.TS;
import org.marc.everest.datatypes.generic.CD;
import org.marc.everest.datatypes.generic.IVL;
import org.marc.everest.datatypes.generic.SET;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.Author;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.EntryRelationship;
import org.marc.everest.rmim.uv.cdar2.vocabulary.ActStatus;
import org.oscarehr.common.model.Dxresearch;
import org.oscarehr.e2e.constant.Constants;
// import org.oscarehr.e2e.model.export.template.AuthorParticipationModel;
import org.oscarehr.e2e.model.export.template.observation.DateObservationModel;
import org.oscarehr.e2e.model.export.template.observation.SecondaryCodeICD9ObservationModel;
import org.oscarehr.e2e.util.EverestUtils;

public class ProblemsModel {
	private Dxresearch problem;

	private SET<II> ids;
	private CD<String> code;
	private ED text;
	private ActStatus statusCode;
	private IVL<TS> effectiveTime;
	private CD<String> value;
	private ArrayList<Author> authors;
	private EntryRelationship secondaryCodeICD9;
	private EntryRelationship diagnosisDate;

	public ProblemsModel(Dxresearch problem) {
		if(problem == null) {
			this.problem = new Dxresearch();
		} else {
			this.problem = problem;
		}

		setIds();
		setCode();
		setText();
		setStatusCode();
		setEffectiveTime();
		setValue();
		setAuthor();
		setSecondaryCodeICD9();
		setDiagnosisDate();
	}

	public String getTextSummary() {
		StringBuilder sb = new StringBuilder();
		String description = EverestUtils.getICD9Description(problem.getDxresearchCode());

		if(!EverestUtils.isNullorEmptyorWhitespace(problem.getDxresearchCode())) {
			sb.append("ICD9: " + problem.getDxresearchCode());
		}
		if(!EverestUtils.isNullorEmptyorWhitespace(description)) {
			sb.append(" - " + description);
		}

		return sb.toString();
	}

	public SET<II> getIds() {
		return ids;
	}

	private void setIds() {
		this.ids = EverestUtils.buildUniqueId(Constants.IdPrefixes.ProblemList, problem.getDxresearchNo());
	}

	public CD<String> getCode() {
		return code;
	}

	private void setCode() {
		this.code = new CD<String>();
		this.code.setNullFlavor(NullFlavor.NoInformation);
	}

	public ED getText() {
		return text;
	}

	private void setText() {
		String description = EverestUtils.getICD9Description(problem.getDxresearchCode());
		if(!EverestUtils.isNullorEmptyorWhitespace(description)) {
			this.text = new ED(description);
		} else {
			this.text = null;
		}
	}

	public ActStatus getStatusCode() {
		return statusCode;
	}

	private void setStatusCode() {
		if(problem.getStatus() != null && problem.getStatus().equals('A')) {
			this.statusCode = ActStatus.Active;
		} else {
			this.statusCode = ActStatus.Completed;
		}
	}

	public IVL<TS> getEffectiveTime() {
		return effectiveTime;
	}

	private void setEffectiveTime() {
		IVL<TS> ivl = null;
		TS startTime = EverestUtils.buildTSFromDate(problem.getStartDate());
		if(startTime != null) {
			ivl = new IVL<TS>(startTime, null);
		}

		this.effectiveTime = ivl;
	}

	public CD<String> getValue() {
		return value;
	}

	private void setValue() {
		this.value = new CD<String>();
		this.value.setNullFlavor(NullFlavor.Unknown);
	}

	public ArrayList<Author> getAuthor() {
		return authors;
	}

	private void setAuthor() {
		authors = new ArrayList<Author>();
		// authors.add(new AuthorParticipationModel(problem.getProviderNo()).getAuthor(problem.getUpdateDate()));
	}

	public EntryRelationship getSecondaryCodeICD9() {
		return secondaryCodeICD9;
	}

	private void setSecondaryCodeICD9() {
		this.secondaryCodeICD9 = new SecondaryCodeICD9ObservationModel().getEntryRelationship(problem.getDxresearchCode());
	}

	public EntryRelationship getDiagnosisDate() {
		return diagnosisDate;
	}

	private void setDiagnosisDate() {
		this.diagnosisDate = new DateObservationModel().getEntryRelationship(problem.getUpdateDate());
	}
}
