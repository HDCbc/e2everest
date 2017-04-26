package org.oscarehr.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Clinic
 *
 */
@Entity
@Table(name="clinic")
public class Clinic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="clinic_no")
	private Integer id;
	@Column(name="clinic_name")
	private String clinicName;
	@Column(name="clinic_address")
	private String clinicAddress;
	@Column(name="clinic_city")
	private String clinicCity;
	@Column(name="clinic_postal")
	private String clinicPostal;
	@Column(name="clinic_phone")
	private String clinicPhone;
	@Column(name="clinic_fax")
	private String clinicFax;
	@Column(name="clinic_location_code")
	private String clinicLocationCode;
	@Column(columnDefinition="char")
	private String status;
	@Column(name="clinic_province")
	private String clinicProvince;
	@Column(name="clinic_delim_phone", columnDefinition="text")
	private String clinicDelimPhone;
	@Column(name="clinic_delim_fax", columnDefinition="text")
	private String clinicDelimFax;

	public Clinic() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public String getClinicAddress() {
		return clinicAddress;
	}

	public void setClinicAddress(String clinicAddress) {
		this.clinicAddress = clinicAddress;
	}

	public String getClinicCity() {
		return clinicCity;
	}

	public void setClinicCity(String clinicCity) {
		this.clinicCity = clinicCity;
	}

	public String getClinicPostal() {
		return clinicPostal;
	}

	public void setClinicPostal(String clinicPostal) {
		this.clinicPostal = clinicPostal;
	}

	public String getClinicPhone() {
		return clinicPhone;
	}

	public void setClinicPhone(String clinicPhone) {
		this.clinicPhone = clinicPhone;
	}

	public String getClinicFax() {
		return clinicFax;
	}

	public void setClinicFax(String clinicFax) {
		this.clinicFax = clinicFax;
	}

	public String getClinicLocationCode() {
		return clinicLocationCode;
	}

	public void setClinicLocationCode(String clinicLocationCode) {
		this.clinicLocationCode = clinicLocationCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getClinicProvince() {
		return clinicProvince;
	}

	public void setClinicProvince(String clinicProvince) {
		this.clinicProvince = clinicProvince;
	}

	public String getClinicDelimPhone() {
		return clinicDelimPhone;
	}

	public void setClinicDelimPhone(String clinicDelimPhone) {
		this.clinicDelimPhone = clinicDelimPhone;
	}

	public String getClinicDelimFax() {
		return clinicDelimFax;
	}

	public void setClinicDelimFax(String clinicDelimFax) {
		this.clinicDelimFax = clinicDelimFax;
	}
}
