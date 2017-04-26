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
 * Entity implementation class for Entity: Demographic
 *
 */
@Entity
@Table(name="demographic")
public class Demographic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "demographic_no")
	private Integer demographicNo;
	private String title;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "first_name")
	private String firstName;
	private String address;
	private String city;
	private String province;
	private String postal;
	private String phone;
	private String phone2;
	private String email;

	private String myOscarUserName;

	@Column(name = "year_of_birth", columnDefinition = "char")
	private String yearOfBirth;
	@Column(name = "month_of_birth", columnDefinition = "char")
	private String monthOfBirth;
	@Column(name = "date_of_birth", columnDefinition = "char")
	private String dateOfBirth;
	private String hin;
	@Column(columnDefinition="char")
	private String ver;

	@Column(name="roster_status")
	private String rosterStatus;
	@Temporal(TemporalType.DATE)
	@Column(name="roster_date")
	private Date rosterDate;
	@Temporal(TemporalType.DATE)
	@Column(name="roster_termination_date")
	private Date rosterTerminationDate;
	@Column(name="roster_termination_reason")
	private String rosterTerminationReason;

	@Column(name="patient_status")
	private String patientStatus;
	@Temporal(TemporalType.DATE)
	@Column(name="patient_status_date")
	private Date patientStatusDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "date_joined")
	private Date dateJoined;
	@Column(name = "chart_no")
	private String chartNo;
	@Column(name="official_lang")
	private String officialLanguage;
	@Column(name="spoken_lang")
	private String spokenLanguage;
	@Column(name="provider_no")
	private String providerNo;

	@Column(columnDefinition="char")
	private String sex;
	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "eff_date")
	private Date effDate;
	@Column(name = "pcn_indicator")
	private String pcnIndicator;
	@Column(name = "hc_type")
	private String hcType;
	@Temporal(TemporalType.DATE)
	@Column(name = "hc_renew_date")
	private Date hcRenewDate;

	@Column(name = "family_doctor")
	private String familyDoctor;
	private String alias;
	private String previousAddress;
	private String children;
	private String sourceOfIncome;
	private String citizenship;
	private String sin;
	@Column(name = "country_of_origin", columnDefinition="char")
	private String countryOfOrigin;
	private String newsletter;
	private String anonymous = null;

	private String lastUpdateUser = null;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdateDate = null;

	public Demographic() {
		super();
	}

	public Integer getDemographicNo() {
		return demographicNo;
	}

	public void setDemographicNo(Integer demographicNo) {
		this.demographicNo = demographicNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMyOscarUserName() {
		return myOscarUserName;
	}

	public void setMyOscarUserName(String myOscarUserName) {
		this.myOscarUserName = myOscarUserName;
	}

	public String getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(String yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public String getMonthOfBirth() {
		return monthOfBirth;
	}

	public void setMonthOfBirth(String monthOfBirth) {
		this.monthOfBirth = monthOfBirth;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getHin() {
		return hin;
	}

	public void setHin(String hin) {
		this.hin = hin;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public String getRosterStatus() {
		return rosterStatus;
	}

	public void setRosterStatus(String rosterStatus) {
		this.rosterStatus = rosterStatus;
	}

	public Date getRosterDate() {
		return rosterDate;
	}

	public void setRosterDate(Date rosterDate) {
		this.rosterDate = rosterDate;
	}

	public Date getRosterTerminationDate() {
		return rosterTerminationDate;
	}

	public void setRosterTerminationDate(Date rosterTerminationDate) {
		this.rosterTerminationDate = rosterTerminationDate;
	}

	public String getRosterTerminationReason() {
		return rosterTerminationReason;
	}

	public void setRosterTerminationReason(String rosterTerminationReason) {
		this.rosterTerminationReason = rosterTerminationReason;
	}

	public String getPatientStatus() {
		return patientStatus;
	}

	public void setPatientStatus(String patientStatus) {
		this.patientStatus = patientStatus;
	}

	public Date getPatientStatusDate() {
		return patientStatusDate;
	}

	public void setPatientStatusDate(Date patientStatusDate) {
		this.patientStatusDate = patientStatusDate;
	}

	public Date getDateJoined() {
		return dateJoined;
	}

	public void setDateJoined(Date dateJoined) {
		this.dateJoined = dateJoined;
	}

	public String getChartNo() {
		return chartNo;
	}

	public void setChartNo(String chartNo) {
		this.chartNo = chartNo;
	}

	public String getOfficialLanguage() {
		return officialLanguage;
	}

	public void setOfficialLanguage(String officialLanguage) {
		this.officialLanguage = officialLanguage;
	}

	public String getSpokenLanguage() {
		return spokenLanguage;
	}

	public void setSpokenLanguage(String spokenLanguage) {
		this.spokenLanguage = spokenLanguage;
	}

	public String getProviderNo() {
		return providerNo;
	}

	public void setProviderNo(String providerNo) {
		this.providerNo = providerNo;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEffDate() {
		return effDate;
	}

	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	public String getPcnIndicator() {
		return pcnIndicator;
	}

	public void setPcnIndicator(String pcnIndicator) {
		this.pcnIndicator = pcnIndicator;
	}

	public String getHcType() {
		return hcType;
	}

	public void setHcType(String hcType) {
		this.hcType = hcType;
	}

	public Date getHcRenewDate() {
		return hcRenewDate;
	}

	public void setHcRenewDate(Date hcRenewDate) {
		this.hcRenewDate = hcRenewDate;
	}

	public String getFamilyDoctor() {
		return familyDoctor;
	}

	public void setFamilyDoctor(String familyDoctor) {
		this.familyDoctor = familyDoctor;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getPreviousAddress() {
		return previousAddress;
	}

	public void setPreviousAddress(String previousAddress) {
		this.previousAddress = previousAddress;
	}

	public String getChildren() {
		return children;
	}

	public void setChildren(String children) {
		this.children = children;
	}

	public String getSourceOfIncome() {
		return sourceOfIncome;
	}

	public void setSourceOfIncome(String sourceOfIncome) {
		this.sourceOfIncome = sourceOfIncome;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public String getSin() {
		return sin;
	}

	public void setSin(String sin) {
		this.sin = sin;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public String getNewsletter() {
		return newsletter;
	}

	public void setNewsletter(String newsletter) {
		this.newsletter = newsletter;
	}

	public String getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(String anonymous) {
		this.anonymous = anonymous;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
}
