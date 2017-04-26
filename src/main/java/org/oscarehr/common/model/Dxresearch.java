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
 * Entity implementation class for Entity: Dxresearch
 *
 */
@Entity
@Table(name="dxresearch")
public class Dxresearch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="dxresearch_no")
	private Integer dxresearchNo;
	@Column(name="demographic_no")
	private Integer demographicNo;
	@Column(name="start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Column(name="update_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	private Character status;
	@Column(name="dxresearch_code")
	private String dxresearchCode;
	@Column(name="coding_system")
	private String codingSystem;
	@Column(columnDefinition="bit")
	private Byte association;
	// private String providerNo;

	public Dxresearch() {
		super();
	}

	public Integer getDxresearchNo() {
		return dxresearchNo;
	}

	public void setDxresearchNo(Integer dxresearchNo) {
		this.dxresearchNo = dxresearchNo;
	}

	public Integer getDemographicNo() {
		return demographicNo;
	}

	public void setDemographicNo(Integer demographicNo) {
		this.demographicNo = demographicNo;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public String getDxresearchCode() {
		return dxresearchCode;
	}

	public void setDxresearchCode(String dxresearchCode) {
		this.dxresearchCode = dxresearchCode;
	}

	public String getCodingSystem() {
		return codingSystem;
	}

	public void setCodingSystem(String codingSystem) {
		this.codingSystem = codingSystem;
	}

	public Byte getAssociation() {
		return association;
	}

	public void setAssociation(Byte association) {
		this.association = association;
	}

	// public String getProviderNo() {
	// 	return providerNo;
	// }

	// public void setProviderNo(String providerNo) {
	// 	this.providerNo = providerNo;
	// }
}
