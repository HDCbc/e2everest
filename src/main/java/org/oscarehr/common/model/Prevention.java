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
 * Entity implementation class for Entity: Prevention
 *
 */
@Entity
@Table(name="preventions")
public class Prevention implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id = null;
	@Column(name = "demographic_no")
	private Integer demographicId = null;
	@Column(name = "creation_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate = new Date();
	@Column(name = "prevention_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date preventionDate = null;
	@Column(name = "provider_no")
	private String providerNo = null;
	private String provider_name = null;
	@Column(name = "prevention_type")
	private String preventionType = null;
	private Character deleted = '0';
	private Character refused = '0';
	@Column(name = "next_date")
	@Temporal(TemporalType.DATE)
	private Date nextDate = null;
	private Character never = '0';
	@Column(name = "creator", columnDefinition="int")
	private String creatorProviderNo = null;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdateDate = null;

	public Prevention() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDemographicId() {
		return demographicId;
	}

	public void setDemographicId(Integer demographicId) {
		this.demographicId = demographicId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getPreventionDate() {
		return preventionDate;
	}

	public void setPreventionDate(Date preventionDate) {
		this.preventionDate = preventionDate;
	}

	public String getProviderNo() {
		return providerNo;
	}

	public void setProviderNo(String providerNo) {
		this.providerNo = providerNo;
	}

	public String getProvider_name() {
		return provider_name;
	}

	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
	}

	public String getPreventionType() {
		return preventionType;
	}

	public void setPreventionType(String preventionType) {
		this.preventionType = preventionType;
	}

	public Character getDeleted() {
		return deleted;
	}

	public void setDeleted(Character deleted) {
		this.deleted = deleted;
	}

	public Character getRefused() {
		return refused;
	}

	public void setRefused(Character refused) {
		this.refused = refused;
	}

	public Date getNextDate() {
		return nextDate;
	}

	public void setNextDate(Date nextDate) {
		this.nextDate = nextDate;
	}

	public Character getNever() {
		return never;
	}

	public void setNever(Character never) {
		this.never = never;
	}

	public String getCreatorProviderNo() {
		return creatorProviderNo;
	}

	public void setCreatorProviderNo(String creatorProviderNo) {
		this.creatorProviderNo = creatorProviderNo;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
}
