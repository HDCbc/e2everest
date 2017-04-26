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
 * Entity implementation class for Entity: CaseManagementNoteExt
 *
 */
@Entity
@Table(name="casemgmt_note_ext")
public class CaseManagementNoteExt implements Serializable {
	private static final long serialVersionUID = 1L;

	public static String STARTDATE = "Start Date";
	public static String RESOLUTIONDATE = "Resolution Date";
	public static String PROCEDUREDATE = "Procedure Date";
	public static String AGEATONSET = "Age at Onset";
	public static String TREATMENT = "Treatment";
	public static String PROBLEMSTATUS = "Problem Status";
	public static String EXPOSUREDETAIL = "Exposure Details";
	public static String RELATIONSHIP = "Relationship";
	public static String LIFESTAGE = "Life Stage";
	public static String HIDECPP = "Hide Cpp";
	public static String PROBLEMDESC = "Problem Description";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition="int")
	private Long id;
	@Column(name="note_id", columnDefinition="int")
	private Long noteId;
	@Column(name="key_val")
	private String keyVal;
	@Column(columnDefinition="text")
	private String value;
	@Column(name="date_value")
	@Temporal(TemporalType.DATE)
	private Date dateValue;

	public CaseManagementNoteExt() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNoteId() {
		return noteId;
	}

	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}

	public String getKeyVal() {
		return keyVal;
	}

	public void setKeyVal(String keyVal) {
		this.keyVal = keyVal;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getDateValue() {
		return dateValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}
}
