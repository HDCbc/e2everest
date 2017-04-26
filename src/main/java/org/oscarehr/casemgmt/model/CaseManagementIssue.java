package org.oscarehr.casemgmt.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: CaseManagementIssue
 *
 */
@Entity
@Table(name="casemgmt_issue")
public class CaseManagementIssue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String demographic_no;
	private Integer issue_id;
	private Boolean acute;
	private Boolean certain;
	private Boolean major;
	private Boolean resolved;
	private Integer program_id = null;
	private String type;
	@Temporal(TemporalType.TIMESTAMP)
	private Date update_date = new Date();

	public CaseManagementIssue() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDemographic_no() {
		return demographic_no;
	}

	public void setDemographic_no(String demographic_no) {
		this.demographic_no = demographic_no;
	}

	public Integer getIssue_id() {
		return issue_id;
	}

	public void setIssue_id(Integer issue_id) {
		this.issue_id = issue_id;
	}

	public Boolean getAcute() {
		return acute;
	}

	public void setAcute(Boolean acute) {
		this.acute = acute;
	}

	public Boolean getCertain() {
		return certain;
	}

	public void setCertain(Boolean certain) {
		this.certain = certain;
	}

	public Boolean getMajor() {
		return major;
	}

	public void setMajor(Boolean major) {
		this.major = major;
	}

	public Boolean getResolved() {
		return resolved;
	}

	public void setResolved(Boolean resolved) {
		this.resolved = resolved;
	}

	public Integer getProgram_id() {
		return program_id;
	}

	public void setProgram_id(Integer program_id) {
		this.program_id = program_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
}
