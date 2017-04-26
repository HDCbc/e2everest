package org.oscarehr.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: MeasurementsExt
 *
 */
@Entity
@Table(name="measurementsExt")
public class MeasurementsExt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id = null;
	@Column(name="measurement_id")
	private Integer measurementId = null;
	private String keyVal = null;
	@Column(columnDefinition="text")
	private String val = null;

	public MeasurementsExt() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMeasurementId() {
		return measurementId;
	}

	public void setMeasurementId(Integer measurementId) {
		this.measurementId = measurementId;
	}

	public String getKeyVal() {
		return keyVal;
	}

	public void setKeyVal(String keyVal) {
		this.keyVal = keyVal;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}
}
