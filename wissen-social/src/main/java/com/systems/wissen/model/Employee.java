package com.systems.wissen.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="emp_id")
	private String empId;

	@Column(name="application_status")
	private int applicationStatus;

	private String bio;

	@Column(name="bio_pic")
	private String bioPic;

	@Column(name="contact_number_personal")
	private long contactNumberPersonal;

	@Column(name="contact_number_work")
	private long contactNumberWork;

	@Column(name="current_position")
	private String currentPosition;

	@Temporal(TemporalType.DATE)
	@Column(name="date_of_birth")
	private Date dateOfBirth;

	@Column(name="email_id")
	private String emailId;

	@Column(name="first_name")
	private String firstName;

	private String gender;

	@Column(name="institute_name")
	private String instituteName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="marital_status")
	private String maritalStatus;

	@Column(name="qualification_degree")
	private String qualificationDegree;

	private String resume;

	//bi-directional many-to-one association to Address
	@OneToMany(mappedBy="employee", cascade = CascadeType.ALL)
	private List<Address> addresses;

	//bi-directional many-to-one association to Certification
	@OneToMany(mappedBy="employee",cascade= CascadeType.ALL)
	private List<Certification> certifications;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="manager_id")
	@JsonBackReference
	private Employee employee;
	
	@Transient
	private String managerId;
	
	public String getManagerId() {
		return managerId;
	}

	public void setManagerId() {
		if(employee != null)
		this.managerId = this.employee.empId;
		else 
			this.managerId="";
	}

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="employee")
	@JsonIgnore
	private List<Employee> employees;

	//bi-directional many-to-one association to Skill
	@OneToMany(mappedBy="employee",cascade= CascadeType.ALL)
	private List<Skill> skills;

	public String getEmpId() {
		return this.empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public int getApplicationStatus() {
		return this.applicationStatus;
	}

	public void setApplicationStatus(int applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getBio() {
		return this.bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getBioPic() {
		return this.bioPic;
	}

	public void setBioPic(String bioPic) {
		this.bioPic = bioPic;
	}

	public long getContactNumberPersonal() {
		return this.contactNumberPersonal;
	}

	public void setContactNumberPersonal(long contactNumberPersonal) {
		this.contactNumberPersonal = contactNumberPersonal;
	}

	public long getContactNumberWork() {
		return this.contactNumberWork;
	}

	public void setContactNumberWork(long contactNumberWork) {
		this.contactNumberWork = contactNumberWork;
	}

	public String getCurrentPosition() {
		return this.currentPosition;
	}

	public void setCurrentPosition(String currentPosition) {
		this.currentPosition = currentPosition;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getInstituteName() {
		return this.instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getQualificationDegree() {
		return this.qualificationDegree;
	}

	public void setQualificationDegree(String qualificationDegree) {
		this.qualificationDegree = qualificationDegree;
	}

	public String getResume() {
		return this.resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public List<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Address addAddress(Address address) {
		getAddresses().add(address);
		address.setEmployee(this);

		return address;
	}

	public Address removeAddress(Address address) {
		getAddresses().remove(address);
		address.setEmployee(null);
		return address;
	}

	public List<Certification> getCertifications() {
		return this.certifications;
	}

	public void setCertifications(List<Certification> certifications) {
		this.certifications = certifications;
	}

	public Certification addCertification(Certification certification) {
		getCertifications().add(certification);
		certification.setEmployee(this);

		return certification;
	}

	public Certification removeCertification(Certification certification) {
		getCertifications().remove(certification);
		certification.setEmployee(null);

		return certification;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setEmployee(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setEmployee(null);

		return employee;
	}

	public List<Skill> getSkills() {
		return this.skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public Skill addSkill(Skill skill) {
		getSkills().add(skill);
		skill.setEmployee(this);

		return skill;
	}

	public Skill removeSkill(Skill skill) {
		getSkills().remove(skill);
		skill.setEmployee(null);

		return skill;
	}

}