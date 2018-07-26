package com.systems.wissen.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the certification database table.
 * 
 */
@Entity
@NamedQuery(name="Certification.findAll", query="SELECT c FROM Certification c")
public class Certification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="certification_id")
	private int certificationId;

	@Column(name="certification_name")
	private String certificationName;

	@Column(name="completion_year")
	private int completionYear;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="emp_id")
	@JsonIgnore
	private Employee employee;

	public int getCertificationId() {
		return this.certificationId;
	}

	public void setCertificationId(int certificationId) {
		this.certificationId = certificationId;
	}

	public String getCertificationName() {
		return this.certificationName;
	}

	public void setCertificationName(String certificationName) {
		this.certificationName = certificationName;
	}

	public int getCompletionYear() {
		return this.completionYear;
	}

	public void setCompletionYear(int completionYear) {
		this.completionYear = completionYear;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}