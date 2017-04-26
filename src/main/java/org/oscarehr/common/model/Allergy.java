package org.oscarehr.common.model;

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
 * Entity implementation class for Entity: Allergy
 *
 */
@Entity
@Table(name="allergies")
public class Allergy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "allergyid")
	private Integer id;
	@Column(name = "demographic_no")
	private Integer demographicNo;
	@Column(name = "entry_date")
	@Temporal(TemporalType.DATE)
	private Date entryDate;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "HICL_SEQNO")
	private Integer hiclSeqno;
	@Column(name = "HIC_SEQNO")
	private Integer hicSeqno;
	@Column(name = "AGCSP")
	private Integer agcsp;
	@Column(name = "AGCCS")
	private Integer agccs;
	@Column(name = "TYPECODE", columnDefinition = "tinyint")
	private Integer typeCode;
	@Column(name = "reaction", columnDefinition = "text")
	private String reaction;
	@Column(name = "drugref_id")
	private String drugrefId;
	@Column(name = "archived", columnDefinition = "char")
	private Boolean archived = false;
	@Column(name = "start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Column(name = "age_of_onset", columnDefinition = "char")
	private String ageOfOnset;
	@Column(name = "severity_of_reaction", columnDefinition = "char")
	private String severityOfReaction;
	@Column(name = "onset_of_reaction", columnDefinition = "char")
	private String onsetOfReaction;
	@Column(name = "regional_identifier")
	private String regionalIdentifier;
	@Column(name = "life_stage", columnDefinition = "char")
	private String lifeStage;
	private Integer position = 0;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdateDate;
	private String providerNo;

	public Allergy() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDemographicNo() {
		return demographicNo;
	}

	public void setDemographicNo(Integer demographicNo) {
		this.demographicNo = demographicNo;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getHiclSeqno() {
		return hiclSeqno;
	}

	public void setHiclSeqno(Integer hiclSeqno) {
		this.hiclSeqno = hiclSeqno;
	}

	public Integer getHicSeqno() {
		return hicSeqno;
	}

	public void setHicSeqno(Integer hicSeqno) {
		this.hicSeqno = hicSeqno;
	}

	public Integer getAgcsp() {
		return agcsp;
	}

	public void setAgcsp(Integer agcsp) {
		this.agcsp = agcsp;
	}

	public Integer getAgccs() {
		return agccs;
	}

	public void setAgccs(Integer agccs) {
		this.agccs = agccs;
	}

	public Integer getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(Integer typeCode) {
		this.typeCode = typeCode;
	}

	public String getReaction() {
		return reaction;
	}

	public void setReaction(String reaction) {
		this.reaction = reaction;
	}

	public String getDrugrefId() {
		return drugrefId;
	}

	public void setDrugrefId(String drugrefId) {
		this.drugrefId = drugrefId;
	}

	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getAgeOfOnset() {
		return ageOfOnset;
	}

	public void setAgeOfOnset(String ageOfOnset) {
		this.ageOfOnset = ageOfOnset;
	}

	public String getSeverityOfReaction() {
		return severityOfReaction;
	}

	public void setSeverityOfReaction(String severityOfReaction) {
		this.severityOfReaction = severityOfReaction;
	}

	public String getOnsetOfReaction() {
		return onsetOfReaction;
	}

	public void setOnsetOfReaction(String onsetOfReaction) {
		this.onsetOfReaction = onsetOfReaction;
	}

	public String getRegionalIdentifier() {
		return regionalIdentifier;
	}

	public void setRegionalIdentifier(String regionalIdentifier) {
		this.regionalIdentifier = regionalIdentifier;
	}

	public String getLifeStage() {
		return lifeStage;
	}

	public void setLifeStage(String lifeStage) {
		this.lifeStage = lifeStage;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getProviderNo() {
		return providerNo;
	}

	public void setProviderNo(String providerNo) {
		this.providerNo = providerNo;
	}
}
