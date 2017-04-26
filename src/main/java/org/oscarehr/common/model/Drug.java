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
 * Entity implementation class for Entity: Drug
 *
 */
@Entity
@Table(name="drugs")
public class Drug implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "drugid")
	private Integer id = null;
	@Column(name = "provider_no")
	private String providerNo = null;
	@Column(name = "demographic_no")
	private Integer demographicId = null;
	@Column(name = "rx_date")
	@Temporal(TemporalType.DATE)
	private Date rxDate = null;
	@Column(name = "end_date")
	@Temporal(TemporalType.DATE)
	private Date endDate = null;
	@Column(name = "written_date")
	@Temporal(TemporalType.DATE)
	private Date writtenDate = null;
	@Column(name = "pickup_datetime", columnDefinition="datetime")
	@Temporal(TemporalType.DATE)
	private Date pickupDateTime;
	@Column(name = "BN")
	private String brandName = null;
	@Column(name = "GCN_SEQNO", columnDefinition="decimal")
	private Integer gcnSeqNo = 0;
	private String customName = null;
	@Column(name = "takemin")
	private Float takeMin = (float) 0;
	@Column(name = "takemax")
	private Float takeMax = (float) 0;
	@Column(name = "freqcode")
	private String freqCode = null;
	private String duration = null;
	@Column(name = "durunit", columnDefinition="char")
	private String durUnit = null;
	private String quantity = null;
	@Column(name = "`repeat`", columnDefinition="tinyint")
	private Integer repeat = 0;
	@Column(name = "last_refill_date")
	@Temporal(TemporalType.DATE)
	private Date lastRefillDate = null;
	@Column(name = "nosubs")
	private Boolean noSubs;
	private Boolean prn;
	@Column(columnDefinition="text")
	private String special = null;
	@Column(columnDefinition="text")
	private String special_instruction = null;
	private Boolean archived;
	@Column(name = "GN")
	private String genericName = null;
	@Column(name = "ATC")
	private String atc = null;
	@Column(name = "script_no")
	private Integer scriptNo = 0;
	@Column(name = "regional_identifier")
	private String regionalIdentifier = null;
	private String unit = null;
	private String method = null;
	private String route = null;
	@Column(name = "drug_form")
	private String drugForm = null;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();
	@Column(columnDefinition="text")
	private String dosage = null;
	@Column(name = "custom_instructions")
	private Boolean customInstructions;
	private String unitName = null;
	@Column(name = "custom_note")
	private Boolean customNote = false;
	@Column(name = "long_term")
	private Boolean longTerm = false;
	@Column(name = "non_authoritative")
	private Boolean nonAuthoritative = false;
	@Column(name = "past_med")
	private Boolean pastMed;
	@Column(name = "patient_compliance")
	private Boolean patientCompliance = null;
	@Column(name = "outside_provider_name")
	private String outsideProviderName = null;
	@Column(name = "outside_provider_ohip")
	private String outsideProviderOhip = null;
	@Column(name = "archived_reason")
	private String archivedReason;
	@Column(name = "archived_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date archivedDate;
	@Column(name = "hide_from_drug_profile")
	private Boolean hideFromDrugProfile;
	private String eTreatmentType = null;
	private String rxStatus = null;
	@Column(name = "dispense_interval")
	private Integer dispenseInterval;
	@Column(name = "refill_duration")
	private Integer refillDuration;
	@Column(name = "refill_quantity")
	private Integer refillQuantity;
	@Column(name = "hide_cpp")
	private Boolean hideFromCpp;
	@Column(name = "position")
	private Integer position;
	private String comment;
	@Column(name = "start_date_unknown")
	private Boolean startDateUnknown;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdateDate;

	public Drug() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProviderNo() {
		return providerNo;
	}

	public void setProviderNo(String providerNo) {
		this.providerNo = providerNo;
	}

	public Integer getDemographicId() {
		return demographicId;
	}

	public void setDemographicId(Integer demographicId) {
		this.demographicId = demographicId;
	}

	public Date getRxDate() {
		return rxDate;
	}

	public void setRxDate(Date rxDate) {
		this.rxDate = rxDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getWrittenDate() {
		return writtenDate;
	}

	public void setWrittenDate(Date writtenDate) {
		this.writtenDate = writtenDate;
	}

	public Date getPickupDateTime() {
		return pickupDateTime;
	}

	public void setPickupDateTime(Date pickupDateTime) {
		this.pickupDateTime = pickupDateTime;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getGcnSeqNo() {
		return gcnSeqNo;
	}

	public void setGcnSeqNo(Integer gcnSeqNo) {
		this.gcnSeqNo = gcnSeqNo;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public Float getTakeMin() {
		return takeMin;
	}

	public void setTakeMin(Float takeMin) {
		this.takeMin = takeMin;
	}

	public Float getTakeMax() {
		return takeMax;
	}

	public void setTakeMax(Float takeMax) {
		this.takeMax = takeMax;
	}

	public String getFreqCode() {
		return freqCode;
	}

	public void setFreqCode(String freqCode) {
		this.freqCode = freqCode;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDurUnit() {
		return durUnit;
	}

	public void setDurUnit(String durUnit) {
		this.durUnit = durUnit;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Integer getRepeat() {
		return repeat;
	}

	public void setRepeat(Integer repeat) {
		this.repeat = repeat;
	}

	public Date getLastRefillDate() {
		return lastRefillDate;
	}

	public void setLastRefillDate(Date lastRefillDate) {
		this.lastRefillDate = lastRefillDate;
	}

	public Boolean getNoSubs() {
		return noSubs;
	}

	public void setNoSubs(Boolean noSubs) {
		this.noSubs = noSubs;
	}

	public Boolean getPrn() {
		return prn;
	}

	public void setPrn(Boolean prn) {
		this.prn = prn;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public String getSpecial_instruction() {
		return special_instruction;
	}

	public void setSpecial_instruction(String special_instruction) {
		this.special_instruction = special_instruction;
	}

	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}

	public String getGenericName() {
		return genericName;
	}

	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}

	public String getAtc() {
		return atc;
	}

	public void setAtc(String atc) {
		this.atc = atc;
	}

	public Integer getScriptNo() {
		return scriptNo;
	}

	public void setScriptNo(Integer scriptNo) {
		this.scriptNo = scriptNo;
	}

	public String getRegionalIdentifier() {
		return regionalIdentifier;
	}

	public void setRegionalIdentifier(String regionalIdentifier) {
		this.regionalIdentifier = regionalIdentifier;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getDrugForm() {
		return drugForm;
	}

	public void setDrugForm(String drugForm) {
		this.drugForm = drugForm;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public Boolean getCustomInstructions() {
		return customInstructions;
	}

	public void setCustomInstructions(Boolean customInstructions) {
		this.customInstructions = customInstructions;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Boolean getCustomNote() {
		return customNote;
	}

	public void setCustomNote(Boolean customNote) {
		this.customNote = customNote;
	}

	public Boolean getLongTerm() {
		return longTerm;
	}

	public void setLongTerm(Boolean longTerm) {
		this.longTerm = longTerm;
	}

	public Boolean getNonAuthoritative() {
		return nonAuthoritative;
	}

	public void setNonAuthoritative(Boolean nonAuthoritative) {
		this.nonAuthoritative = nonAuthoritative;
	}

	public Boolean getPastMed() {
		return pastMed;
	}

	public void setPastMed(Boolean pastMed) {
		this.pastMed = pastMed;
	}

	public Boolean getPatientCompliance() {
		return patientCompliance;
	}

	public void setPatientCompliance(Boolean patientCompliance) {
		this.patientCompliance = patientCompliance;
	}

	public String getOutsideProviderName() {
		return outsideProviderName;
	}

	public void setOutsideProviderName(String outsideProviderName) {
		this.outsideProviderName = outsideProviderName;
	}

	public String getOutsideProviderOhip() {
		return outsideProviderOhip;
	}

	public void setOutsideProviderOhip(String outsideProviderOhip) {
		this.outsideProviderOhip = outsideProviderOhip;
	}

	public String getArchivedReason() {
		return archivedReason;
	}

	public void setArchivedReason(String archivedReason) {
		this.archivedReason = archivedReason;
	}

	public Date getArchivedDate() {
		return archivedDate;
	}

	public void setArchivedDate(Date archivedDate) {
		this.archivedDate = archivedDate;
	}

	public Boolean getHideFromDrugProfile() {
		return hideFromDrugProfile;
	}

	public void setHideFromDrugProfile(Boolean hideFromDrugProfile) {
		this.hideFromDrugProfile = hideFromDrugProfile;
	}

	public String geteTreatmentType() {
		return eTreatmentType;
	}

	public void seteTreatmentType(String eTreatmentType) {
		this.eTreatmentType = eTreatmentType;
	}

	public String getRxStatus() {
		return rxStatus;
	}

	public void setRxStatus(String rxStatus) {
		this.rxStatus = rxStatus;
	}

	public Integer getDispenseInterval() {
		return dispenseInterval;
	}

	public void setDispenseInterval(Integer dispenseInterval) {
		this.dispenseInterval = dispenseInterval;
	}

	public Integer getRefillDuration() {
		return refillDuration;
	}

	public void setRefillDuration(Integer refillDuration) {
		this.refillDuration = refillDuration;
	}

	public Integer getRefillQuantity() {
		return refillQuantity;
	}

	public void setRefillQuantity(Integer refillQuantity) {
		this.refillQuantity = refillQuantity;
	}

	public Boolean getHideFromCpp() {
		return hideFromCpp;
	}

	public void setHideFromCpp(Boolean hideFromCpp) {
		this.hideFromCpp = hideFromCpp;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Boolean getStartDateUnknown() {
		return startDateUnknown;
	}

	public void setStartDateUnknown(Boolean startDateUnknown) {
		this.startDateUnknown = startDateUnknown;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
}
