package org.oscarehr.common.model;

import java.io.Serializable;
// import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
// import javax.persistence.Temporal;
// import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: PatientLabRouting
 *
 */
@Entity
@Table(name="patientLabRouting")
public class PatientLabRouting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "lab_no")
	private Integer labNo;
	@Column(name = "lab_type", columnDefinition="char")
	private String labType;
	@Column(name = "demographic_no")
	private Integer demographicNo;
	// @Temporal(TemporalType.TIMESTAMP)
	// private Date dateModified = new Date();
	// @Temporal(TemporalType.TIMESTAMP)
	// private Date created = new Date();

	public PatientLabRouting() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLabNo() {
		return labNo;
	}

	public void setLabNo(Integer labNo) {
		this.labNo = labNo;
	}

	public String getLabType() {
		return labType;
	}

	public void setLabType(String labType) {
		this.labType = labType;
	}

	public Integer getDemographicNo() {
		return demographicNo;
	}

	public void setDemographicNo(Integer demographicNo) {
		this.demographicNo = demographicNo;
	}

	// public Date getDateModified() {
	// 	return dateModified;
	// }
	//
	// public void setDateModified(Date dateModified) {
	// 	this.dateModified = dateModified;
	// }

	// public Date getCreated() {
	// 	return created;
	// }
	//
	// public void setCreated(Date created) {
	// 	this.created = created;
	// }
}
