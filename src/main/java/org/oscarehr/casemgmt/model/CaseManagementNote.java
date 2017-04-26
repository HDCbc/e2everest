package org.oscarehr.casemgmt.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: CaseManagementNote
 *
 */
@Entity
@Table(name="casemgmt_note")
public class CaseManagementNote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="note_id")
	private Integer id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date update_date;
	@Temporal(TemporalType.TIMESTAMP)
	private Date observation_date;
	@Column(columnDefinition = "int")
	private String demographic_no;
	@Column(name="provider_no")
	private String providerNo;
	@Column(length=8192, columnDefinition="mediumtext")
	private String note;
	private Boolean signed = false;
	@Column(name="include_issue_innote")
	private Boolean includeissue = true;
	private String signing_provider_no;
	private String encounter_type;
	private String billing_code;
	private String program_no;
	private String reporter_caisi_role;
	private String reporter_program_team;
	@Column(length=8192, columnDefinition="mediumtext")
	private String history;
	private String password;
	@Column(columnDefinition="char")
	private Boolean locked;
	private Boolean archived;
	private Integer position = 0;
	@Column(columnDefinition="char")
	private String uuid;
	private Integer appointmentNo;
	private Integer hourOfEncounterTime;
	private Integer minuteOfEncounterTime;
	private Integer hourOfEncTransportationTime;
	private Integer minuteOfEncTransportationTime;

	public CaseManagementNote() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public Date getObservation_date() {
		return observation_date;
	}

	public void setObservation_date(Date observation_date) {
		this.observation_date = observation_date;
	}

	public String getDemographic_no() {
		return demographic_no;
	}

	public void setDemographic_no(String demographic_no) {
		this.demographic_no = demographic_no;
	}

	public String getProviderNo() {
		return providerNo;
	}

	public void setProviderNo(String providerNo) {
		this.providerNo = providerNo;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Boolean getSigned() {
		return signed;
	}

	public void setSigned(Boolean signed) {
		this.signed = signed;
	}

	public Boolean getIncludeissue() {
		return includeissue;
	}

	public void setIncludeissue(Boolean includeissue) {
		this.includeissue = includeissue;
	}

	public String getSigning_provider_no() {
		return signing_provider_no;
	}

	public void setSigning_provider_no(String signing_provider_no) {
		this.signing_provider_no = signing_provider_no;
	}

	public String getEncounter_type() {
		return encounter_type;
	}

	public void setEncounter_type(String encounter_type) {
		this.encounter_type = encounter_type;
	}

	public String getBilling_code() {
		return billing_code;
	}

	public void setBilling_code(String billing_code) {
		this.billing_code = billing_code;
	}

	public String getProgram_no() {
		return program_no;
	}

	public void setProgram_no(String program_no) {
		this.program_no = program_no;
	}

	public String getReporter_caisi_role() {
		return reporter_caisi_role;
	}

	public void setReporter_caisi_role(String reporter_caisi_role) {
		this.reporter_caisi_role = reporter_caisi_role;
	}

	public String getReporter_program_team() {
		return reporter_program_team;
	}

	public void setReporter_program_team(String reporter_program_team) {
		this.reporter_program_team = reporter_program_team;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Boolean isArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getAppointmentNo() {
		return appointmentNo;
	}

	public void setAppointmentNo(Integer appointmentNo) {
		this.appointmentNo = appointmentNo;
	}

	public Integer getHourOfEncounterTime() {
		return hourOfEncounterTime;
	}

	public void setHourOfEncounterTime(Integer hourOfEncounterTime) {
		this.hourOfEncounterTime = hourOfEncounterTime;
	}

	public Integer getMinuteOfEncounterTime() {
		return minuteOfEncounterTime;
	}

	public void setMinuteOfEncounterTime(Integer minuteOfEncounterTime) {
		this.minuteOfEncounterTime = minuteOfEncounterTime;
	}

	public Integer getHourOfEncTransportationTime() {
		return hourOfEncTransportationTime;
	}

	public void setHourOfEncTransportationTime(Integer hourOfEncTransportationTime) {
		this.hourOfEncTransportationTime = hourOfEncTransportationTime;
	}

	public Integer getMinuteOfEncTransportationTime() {
		return minuteOfEncTransportationTime;
	}

	public void setMinuteOfEncTransportationTime(
			Integer minuteOfEncTransportationTime) {
		this.minuteOfEncTransportationTime = minuteOfEncTransportationTime;
	}
}
